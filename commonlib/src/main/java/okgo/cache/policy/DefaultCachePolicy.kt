
package okgo.cache.policy

import okgo.cache.CacheEntity
import okgo.callback.Callback
import okgo.exception.CacheException
import okgo.model.Response
import okgo.request.base.Request
import okhttp3.Call

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2017/5/25
 * 描    述：
 * 修订历史：
 * ================================================
 */
class DefaultCachePolicy<T>(request: Request<T, out Request<*, *>>) : BaseCachePolicy<T>(request) {

    override fun onSuccess(success: Response<T>) {
        runOnUiThread {
            mCallback!!.onSuccess(success)
            mCallback!!.onFinish()
        }
    }

    override fun onError(error: Response<T>) {
        runOnUiThread {
            mCallback!!.onError(error)
            mCallback!!.onFinish()
        }
    }

    override fun onAnalysisResponse(call: Call, response: okhttp3.Response): Boolean {
        if (response.code != 304) return false

        if (cacheEntity == null) {
            val error = Response.error<T>(
                true,
                call,
                response,
                CacheException.NON_AND_304(request.cacheKey)
            )
            runOnUiThread {
                mCallback!!.onError(error)
                mCallback!!.onFinish()
            }
        } else {
            val success = Response.success(true, cacheEntity!!.data, call, response)
            runOnUiThread {
                mCallback!!.onCacheSuccess(success)
                mCallback!!.onFinish()
            }
        }
        return true
    }

    override fun requestSync(cacheEntity: CacheEntity<T>?): Response<T> {
        try {
            prepareRawCall()
        } catch (throwable: Throwable) {
            return Response.error(false, rawCall, null, throwable)
        }

        var response = requestNetworkSync()
        //HTTP cache protocol
        if (response.isSuccessful && response.code() == 304) {
            if (cacheEntity == null) {
                response = Response.error(
                    true,
                    rawCall,
                    response.rawResponse,
                    CacheException.NON_AND_304(request.cacheKey)
                )
            } else {
                response = Response.success(true, cacheEntity.data, rawCall, response.rawResponse)
            }
        }
        return response
    }

    override fun requestAsync(cacheEntity: CacheEntity<T>, callback: Callback<T>) {
        mCallback = callback
        runOnUiThread(Runnable {
            mCallback!!.onStart(request)

            try {
                prepareRawCall()
            } catch (throwable: Throwable) {
                val error = Response.error<T>(false, rawCall, null, throwable)
                mCallback!!.onError(error)
                return@Runnable
            }

            requestNetworkAsync()
        })
    }
}
