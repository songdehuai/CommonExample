
package okgo.cache.policy

import okgo.cache.CacheEntity
import okgo.callback.Callback
import okgo.model.Response
import okgo.request.base.Request

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2017/5/25
 * 描    述：
 * 修订历史：
 * ================================================
 */
class NoCachePolicy<T>(request: Request<T, out Request<*, *>>) : BaseCachePolicy<T>(request) {

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

    override fun requestSync(cacheEntity: CacheEntity<T>): Response<T> {
        try {
            prepareRawCall()
        } catch (throwable: Throwable) {
            return Response.error(false, rawCall, null, throwable)
        }

        return requestNetworkSync()
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
