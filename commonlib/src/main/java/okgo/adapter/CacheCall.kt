package okgo.adapter

import okgo.cache.CacheMode
import okgo.cache.policy.CachePolicy
import okgo.cache.policy.DefaultCachePolicy
import okgo.cache.policy.FirstCacheRequestPolicy
import okgo.cache.policy.NoCachePolicy
import okgo.cache.policy.NoneCacheRequestPolicy
import okgo.cache.policy.RequestFailedCachePolicy
import okgo.callback.Callback
import okgo.model.Response
import okgo.request.base.Request
import okgo.utils.HttpUtils

/**
 * ================================================
 * 作    者：songdehuai
 * 版    本：1.0
 * 创建日期：2019/6/27
 * 描    述：带缓存的请求
 * 修订历史：
 * ================================================
 */
class CacheCall<T>(private val request: Request<T, out Request<*, *>>) : Call<T> {

    private var policy: CachePolicy<T>? = null

    init {
        this.policy = preparePolicy()
    }

    override fun execute(): Response<T> {
        val cacheEntity = policy!!.prepareCache()
        return policy!!.requestSync(cacheEntity)
    }

    override fun execute(callback: Callback<T>) {
        HttpUtils.checkNotNull(callback, "callback == null")

        val cacheEntity = policy!!.prepareCache()
        policy!!.requestAsync(cacheEntity, callback)
    }

    private fun preparePolicy(): CachePolicy<T>? {
        when (request.cacheMode) {
            CacheMode.DEFAULT -> policy = DefaultCachePolicy(request)
            CacheMode.NO_CACHE -> policy = NoCachePolicy(request)
            CacheMode.IF_NONE_CACHE_REQUEST -> policy = NoneCacheRequestPolicy(request)
            CacheMode.FIRST_CACHE_THEN_REQUEST -> policy = FirstCacheRequestPolicy(request)
            CacheMode.REQUEST_FAILED_READ_CACHE -> policy = RequestFailedCachePolicy(request)
        }
        if (request.cachePolicy != null) {
            policy = request.cachePolicy
        }
        HttpUtils.checkNotNull(policy, "policy == null")
        return policy
    }

    override fun isExecuted(): Boolean {
        return policy!!.isExecuted
    }

    override fun cancel() {
        policy!!.cancel()
    }

    override fun isCanceled(): Boolean {
        return policy!!.isCanceled
    }

    override fun clone(): Call<T> {
        return CacheCall(request)
    }

    override fun getRequest(): Request<*, *> {
        return request
    }
}
