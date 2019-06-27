package okgo.adapter

/**
 * ================================================
 * 作    者：songdehuai
 * 版    本：1.0
 * 创建日期：2019/6/27
 * 描    述：返回值的适配器
 * 修订历史：
 * ================================================
 */
interface CallAdapter<T, R> {

    /** call执行的代理方法  */
    fun adapt(call: Call<T>, param: AdapterParam): R
}
