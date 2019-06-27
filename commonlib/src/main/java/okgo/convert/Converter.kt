package okgo.convert

import okhttp3.Response

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2016/9/11
 * 描    述：网络数据的转换接口
 * 修订历史：
 * ================================================
 */
interface Converter<T> {

    /**
     * 拿到响应后，将数据转换成需要的格式，子线程中执行，可以是耗时操作
     *
     * @param response 需要转换的对象
     * @return 转换后的结果
     * @throws Exception 转换过程发生的异常
     */
    @Throws(Throwable::class)
    fun convertResponse(response: Response): T
}
