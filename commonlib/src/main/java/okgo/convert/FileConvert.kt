package okgo.convert

import android.os.Environment
import android.text.TextUtils

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

import okgo.callback.Callback
import okgo.model.Progress
import okgo.utils.HttpUtils
import okgo.utils.IOUtils
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
class FileConvert(
    private var folder: String?                  //目标文件存储的文件夹路径
    , private var fileName: String?                //目标文件存储的文件名
) : Converter<File> {
    private var callback: Callback<File>? = null        //下载回调

    @JvmOverloads
    constructor(fileName: String? = null) : this(
        Environment.getExternalStorageDirectory().toString() + DM_TARGET_FOLDER,
        fileName
    ) {
    }

    fun setCallback(callback: Callback<File>) {
        this.callback = callback
    }

    @Throws(Throwable::class)
    override fun convertResponse(response: Response): File? {
        val url = response.request.url.toString()
        if (TextUtils.isEmpty(folder))
            folder = Environment.getExternalStorageDirectory().toString() + DM_TARGET_FOLDER
        if (TextUtils.isEmpty(fileName))
            fileName = HttpUtils.getNetFileName(response, url)

        val dir = File(folder)
        IOUtils.createFolder(dir)
        val file = File(dir, fileName)
        IOUtils.delFileOrFolder(file)

        var bodyStream: InputStream? = null
        val buffer = ByteArray(8192)
        var fileOutputStream: FileOutputStream? = null
        try {
            val body = response.body ?: return null

            bodyStream = body.byteStream()
            val progress = Progress()
            progress.totalSize = body.contentLength()
            progress.fileName = fileName
            progress.filePath = file.absolutePath
            progress.status = Progress.LOADING
            progress.url = url
            progress.tag = url

            var len: Int
            fileOutputStream = FileOutputStream(file)
            while ((len = bodyStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len)

                if (callback == null) continue
                Progress.changeProgress(progress, len.toLong()) { progress -> onProgress(progress) }
            }
            fileOutputStream.flush()
            return file
        } finally {
            IOUtils.closeQuietly(bodyStream)
            IOUtils.closeQuietly(fileOutputStream)
        }
    }

    private fun onProgress(progress: Progress) {
        HttpUtils.runOnUiThread {
            callback!!.downloadProgress(progress)   //进度回调的方法
        }
    }

    companion object {

        val DM_TARGET_FOLDER = File.separator + "download" + File.separator //下载目标文件夹
    }
}
