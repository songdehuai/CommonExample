package okgo.callback

import okgo.convert.StringConvert
import okhttp3.Response

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2019/6/27
 * 描    述：返回字符串类型的数据
 * 修订历史：
 * ================================================
 */
abstract class StringCallback : AbsCallback<String>() {

    private val convert: StringConvert = StringConvert()

    @Throws(Throwable::class)
    override fun convertResponse(response: Response): String? {
        val s = convert.convertResponse(response)
        response.close()
        return s
    }
}
