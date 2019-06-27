package okgo.convert

import okhttp3.Response
import okhttp3.ResponseBody

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：16/9/11
 * 描    述：字符串的转换器
 * 修订历史：
 * ================================================
 */
class StringConvert : Converter<String> {

    @Throws(Throwable::class)
    override fun convertResponse(response: Response): String? {
        val body = response.body ?: return null
        return body.string()
    }
}
