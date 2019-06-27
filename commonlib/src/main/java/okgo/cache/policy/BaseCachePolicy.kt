package okgo.cache.policy

import android.graphics.Bitmap

import java.io.IOException
import java.net.SocketTimeoutException

import okgo.OkGo
import okgo.cache.CacheEntity
import okgo.cache.CacheMode
import okgo.callback.Callback
import okgo.db.CacheManager
import okgo.exception.HttpException
import okgo.model.Response
import okgo.request.base.Request
import okgo.utils.HeaderParser
import okgo.utils.HttpUtils
import okhttp3.Call
import okhttp3.Headers

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2017/5/25
 * 描    述：
 * 修订历史：
 * ================================================
 */
abstract class BaseCachePolicy<T>(protected var request: Request<T, out Request<*, *>>) :
    CachePolicy<T> {
    @Volatile
    protected var canceled: Boolean = false
    @Volatile
    protected var currentRetryCount = 0
    override var isExecuted: Boolean = false
        protected set
    protected var rawCall: Call? = null
    protected var mCallback: Callback<T>? = null
    protected var cacheEntity: CacheEntity<T>? = null

    override val isCanceled: Boolean
        get() {
            if (canceled) return true
            synchronized(this) {
                return rawCall != null && rawCall!!.isCanceled()
            }
        }

    override fun onAnalysisResponse(call: Call, response: okhttp3.Response): Boolean {
        return false
    }

    override fun prepareCache(): CacheEntity<T> {
        //check the config of cache
        if (request.cacheKey == null) {
            request.cacheKey(
                HttpUtils.createUrlFromParams(
                    request.baseUrl,
                    request.params.urlParamsMap
                )
            )
        }
        if (request.cacheMode == null) {
            request.cacheMode(CacheMode.NO_CACHE)
        }

        val cacheMode = request.cacheMode
        if (cacheMode !== CacheMode.NO_CACHE) {

            cacheEntity = CacheManager.getInstance().get(request.cacheKey) as CacheEntity<T>
            HeaderParser.addCacheHeaders(request, cacheEntity, cacheMode)
            if (cacheEntity != null && cacheEntity!!.checkExpire(
                    cacheMode,
                    request.cacheTime,
                    System.currentTimeMillis()
                )
            ) {
                cacheEntity!!.isExpire = true
            }
        }

        if (cacheEntity == null || cacheEntity!!.isExpire || cacheEntity!!.data == null || cacheEntity!!.responseHeaders == null) {
            cacheEntity = null
        }
        return cacheEntity!!
    }

    @Synchronized
    @Throws(Throwable::class)
    override fun prepareRawCall(): Call {
        if (isExecuted) throw HttpException.COMMON("Already executed!")
        isExecuted = true
        rawCall = request.rawCall
        if (canceled) rawCall!!.cancel()
        return rawCall!!
    }

    protected fun requestNetworkSync(): Response<T> {
        try {
            val response = rawCall!!.execute()
            val responseCode = response.code

            //network error
            if (responseCode == 404 || responseCode >= 500) {
                return Response.error(false, rawCall, response, HttpException.NET_ERROR())
            }

            val body = request.converter.convertResponse(response)
            //save cache when request is successful
            saveCache(response.headers, body)
            return Response.success(false, body, rawCall, response)
        } catch (throwable: Throwable) {
            if (throwable is SocketTimeoutException && currentRetryCount < request.retryCount) {
                currentRetryCount++
                rawCall = request.rawCall
                if (canceled) {
                    rawCall!!.cancel()
                } else {
                    requestNetworkSync()
                }
            }
            return Response.error(false, rawCall, null, throwable)
        }

    }

    protected fun requestNetworkAsync() {
        rawCall!!.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                if (e is SocketTimeoutException && currentRetryCount < request.retryCount) {
                    //retry when timeout
                    currentRetryCount++
                    rawCall = request.rawCall
                    if (canceled) {
                        rawCall!!.cancel()
                    } else {
                        rawCall!!.enqueue(this)
                    }
                } else {
                    if (!call.isCanceled()) {
                        val error = Response.error<T>(false, call, null, e)
                        onError(error)
                    }
                }
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val responseCode = response.code

                //network error
                if (responseCode == 404 || responseCode >= 500) {
                    val error = Response.error<T>(false, call, response, HttpException.NET_ERROR())
                    onError(error)
                    return
                }

                if (onAnalysisResponse(call, response)) return

                try {
                    val body = request.converter.convertResponse(response)
                    //save cache when request is successful
                    saveCache(response.headers, body)
                    val success = Response.success(false, body, call, response)
                    onSuccess(success)
                } catch (throwable: Throwable) {
                    val error = Response.error<T>(false, call, response, throwable)
                    onError(error)
                }

            }
        })
    }

    /**
     * 请求成功后根据缓存模式，更新缓存数据
     *
     * @param headers 响应头
     * @param data    响应数据
     */
    private fun saveCache(headers: Headers, data: T) {
        if (request.cacheMode === CacheMode.NO_CACHE) return     //不需要缓存,直接返回
        if (data is Bitmap) return              //Bitmap没有实现Serializable,不能缓存

        val cache =
            HeaderParser.createCacheEntity(headers, data, request.cacheMode, request.cacheKey)
        if (cache == null) {
            //服务器不需要缓存，移除本地缓存
            CacheManager.getInstance().remove(request.cacheKey)
        } else {
            //缓存命中，更新缓存
            CacheManager.getInstance().replace(request.cacheKey, cache)
        }
    }

    protected fun runOnUiThread(run: Runnable) {
        OkGo.instance.delivery.post(run)
    }

    override fun cancel() {
        canceled = true
        if (rawCall != null) {
            rawCall!!.cancel()
        }
    }
}
