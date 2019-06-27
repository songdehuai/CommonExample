package okgo.adapter

import okgo.callback.Callback
import okgo.model.Response
import okgo.request.base.Request

/**
 * ================================================
 * 作    者：songdehuai
 * 版    本：1.0
 * 创建日期：2016/9/11
 * 描    述：请求的包装类
 * 修订历史：
 * ================================================
 */
interface Call<T> {

    /**
     * 是否已经执行
     */
    val isExecuted: Boolean

    /**
     * 是否取消
     */
    val isCanceled: Boolean

    val request: Request<*, *>
    /**
     * 同步执行
     */
    @Throws(Exception::class)
    fun execute(): Response<T>

    /**
     * 异步回调执行
     */
    fun execute(callback: Callback<T>)

    /**
     * 取消
     */
    fun cancel()

    fun clone(): Call<T>
}
