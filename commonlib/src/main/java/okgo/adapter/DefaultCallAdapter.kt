package okgo.adapter

/**
 * ================================================
 * 作    者：songdehuai
 * 版    本：1.0
 * 创建日期：2019/6/27
 * 描    述：默认的工厂处理,不对返回值做任何操作
 * 修订历史：
 * ================================================
 */
class DefaultCallAdapter<T> : CallAdapter<T, Call<T>> {

    override fun adapt(call: Call<T>, param: AdapterParam): Call<T> {
        return call
    }
}
