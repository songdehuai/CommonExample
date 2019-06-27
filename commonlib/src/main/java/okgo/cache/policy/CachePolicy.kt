
package okgo.cache.policy

import okgo.cache.CacheEntity
import okgo.callback.Callback
import okgo.model.Response

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2017/5/25
 * 描    述：
 * 修订历史：
 * ================================================
 */
interface CachePolicy<T> {

    /**
     * 当前请求是否已经执行
     *
     * @return true，已经执行， false，没有执行
     */
    val isExecuted: Boolean

    /**
     * 是否已经取消
     *
     * @return true，已经取消，false，没有取消
     */
    val isCanceled: Boolean

    /**
     * 获取数据成功的回调
     *
     * @param success 获取的数据，可是是缓存或者网络
     */
    fun onSuccess(success: Response<T>)

    /**
     * 获取数据失败的回调
     *
     * @param error 失败的信息，可是是缓存或者网络
     */
    fun onError(error: Response<T>)

    /**
     * 控制是否执行后续的回调动作
     *
     * @param call     请求的对象
     * @param response 响应的对象
     * @return true，不执行回调， false 执行回调
     */
    fun onAnalysisResponse(call: okhttp3.Call, response: okhttp3.Response): Boolean

    /**
     * 构建缓存
     *
     * @return 获取的缓存
     */
    fun prepareCache(): CacheEntity<T>

    /**
     * 构建请求对象
     *
     * @return 准备请求的对象
     */
    @Throws(Throwable::class)
    fun prepareRawCall(): okhttp3.Call

    /**
     * 同步请求获取数据
     *
     * @param cacheEntity 本地的缓存
     * @return 从缓存或本地获取的数据
     */
    fun requestSync(cacheEntity: CacheEntity<T>): Response<T>

    /**
     * 异步请求获取数据
     *
     * @param cacheEntity 本地的缓存
     * @param callback    异步回调
     */
    fun requestAsync(cacheEntity: CacheEntity<T>, callback: Callback<T>)

    /**
     * 取消请求
     */
    fun cancel()
}
