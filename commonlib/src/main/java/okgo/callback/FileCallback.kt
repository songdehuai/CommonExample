
package okgo.callback

import java.io.File

import okgo.convert.FileConvert
import okhttp3.Response

/**
 * ================================================
 * 作    者：
 * 版    本：1.0
 * 创建日期：2016/1/12
 * 描    述：文件的回调下载进度监听
 * 修订历史：
 * ================================================
 */
abstract class FileCallback(destFileDir: String?, destFileName: String?) : AbsCallback<File>() {
    //文件转换类
    private val convert: FileConvert = FileConvert(destFileDir, destFileName)

    @JvmOverloads
    constructor(destFileName: String? = null) : this(null, destFileName) {
    }

    init {
        convert.setCallback(this)
    }

    @Throws(Throwable::class)
    override fun convertResponse(response: Response): File? {
        val file = convert.convertResponse(response)
        response.close()
        return file
    }
}
