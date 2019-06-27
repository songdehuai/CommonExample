
package okgo.utils

import android.os.Build
import android.text.TextUtils

import java.io.File
import java.io.UnsupportedEncodingException
import java.net.URLConnection
import java.net.URLDecoder
import java.net.URLEncoder

import okgo.OkGo
import okgo.model.HttpHeaders
import okgo.model.HttpParams
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

/**
 * ================================================
 * 作    者：songdehuai
 * 版    本：1.0
 * 创建日期：2019/6/27
 * 描    述：
 * 修订历史：
 * ================================================
 */
object HttpUtils {

    /**
     * 获取设备信息
     *
     * @return
     */
    val deviceInfo: String
        get() {
            return "MODEL:${Build.MODEL} DEVICE:${Build.DEVICE} ANDROID_SDK_VERSION${Build.VERSION.SDK_INT} BUILDER_TYPE${Build.TYPE}"
        }

    /**
     * 将传递进来的参数拼接成 url
     */
    fun createUrlFromParams(url: String, params: Map<String, List<String>>): String {
        try {
            val sb = StringBuilder()
            sb.append(url)
            if (url.indexOf('&') > 0 || url.indexOf('?') > 0)
                sb.append("&")
            else
                sb.append("?")
            for ((key, urlValues) in params) {
                for (value in urlValues) {
                    //对参数进行 utf-8 编码,防止头信息传中文
                    val urlValue = URLEncoder.encode(value, "UTF-8")
                    sb.append(key).append("=").append(urlValue).append("&")
                }
            }
            sb.deleteCharAt(sb.length - 1)
            return sb.toString()
        } catch (e: UnsupportedEncodingException) {
            OkLogger.printStackTrace(e)
        }

        return url
    }

    /**
     * 通用的拼接请求头
     */
    fun appendHeaders(builder: Request.Builder, headers: HttpHeaders): Request.Builder {
        if (headers.headersMap.isEmpty()) return builder
        val headerBuilder = Headers.Builder()
        try {
            for ((key, value) in headers.headersMap) {
                //todo  对头信息进行 utf-8 编码,防止头信息传中文,这里暂时不编码,可能出现未知问题,如有需要自行编码
                //                String headerValue = URLEncoder.encode(entry.getValue(), "UTF-8");
                headerBuilder.add(key, value)
            }
        } catch (e: Exception) {
            OkLogger.printStackTrace(e)
        }

        builder.headers(headerBuilder.build())
        return builder
    }

    /**
     * 生成类似表单的请求体
     */
    fun generateMultipartRequestBody(params: HttpParams, isMultipart: Boolean): RequestBody {
        if (params.fileParamsMap.isEmpty() && !isMultipart) {
            //表单提交，没有文件
            val bodyBuilder = FormBody.Builder()
            for (key in params.urlParamsMap.keys) {
                val urlValues = params.urlParamsMap[key]
                for (value in urlValues!!) {
                    bodyBuilder.addEncoded(key, value)
                }
            }
            return bodyBuilder.build()
        } else {
            //表单提交，有文件
            val multipartBodybuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
            //拼接键值对
            if (params.urlParamsMap.isNotEmpty()) {
                for ((key, urlValues) in params.urlParamsMap) {
                    for (value in urlValues) {
                        multipartBodybuilder.addFormDataPart(key, value)
                    }
                }
            }
            //拼接文件
            for ((key, fileValues) in params.fileParamsMap) {
                for (fileWrapper in fileValues) {
                    val fileBody = RequestBody.create(fileWrapper.contentType, fileWrapper.file)
                    multipartBodybuilder.addFormDataPart(key, fileWrapper.fileName, fileBody)
                }
            }
            return multipartBodybuilder.build()
        }
    }

    /**
     * 根据响应头或者url获取文件名
     */
    fun getNetFileName(response: Response, url: String): String? {
        var fileName = getHeaderFileName(response)
        if (TextUtils.isEmpty(fileName)) fileName = getUrlFileName(url)
        if (TextUtils.isEmpty(fileName)) fileName = "unKnownFile_" + System.currentTimeMillis()
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            OkLogger.printStackTrace(e)
        }

        return fileName
    }

    /**
     * 解析文件头
     * Content-Disposition:attachment;filename=FileName.txt
     * Content-Disposition: attachment; filename*="UTF-8''%E6%9B%BF%E6%8D%A2%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.pdf"
     */
    private fun getHeaderFileName(response: Response): String? {
        var dispositionHeader = response.header(HttpHeaders.HEAD_KEY_CONTENT_DISPOSITION)
        if (dispositionHeader != null) {
            //文件名可能包含双引号，需要去除
            dispositionHeader = dispositionHeader.replace("\"".toRegex(), "")
            var split = "filename="
            var indexOf = dispositionHeader.indexOf(split)
            if (indexOf != -1) {
                return dispositionHeader.substring(indexOf + split.length, dispositionHeader.length)
            }
            split = "filename*="
            indexOf = dispositionHeader.indexOf(split)
            if (indexOf != -1) {
                var fileName =
                    dispositionHeader.substring(indexOf + split.length, dispositionHeader.length)
                val encode = "UTF-8''"
                if (fileName.startsWith(encode)) {
                    fileName = fileName.substring(encode.length, fileName.length)
                }
                return fileName
            }
        }
        return null
    }

    /**
     * 通过 ‘？’ 和 ‘/’ 判断文件名
     * http://mavin-manzhan.oss-cn-hangzhou.aliyuncs.com/1486631099150286149.jpg?x-oss-process=image/watermark,image_d2F0ZXJtYXJrXzIwMF81MC5wbmc
     */
    private fun getUrlFileName(url: String): String? {
        var filename: String? = null
        val strings = url.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (string in strings) {
            if (string.contains("?")) {
                val endIndex = string.indexOf("?")
                if (endIndex != -1) {
                    filename = string.substring(0, endIndex)
                    return filename
                }
            }
        }
        if (strings.isNotEmpty()) {
            filename = strings[strings.size - 1]
        }
        return filename
    }

    /**
     * 根据路径删除文件
     */
    fun deleteFile(path: String): Boolean {
        if (TextUtils.isEmpty(path)) return true
        val file = File(path)
        if (!file.exists()) return true
        if (file.isFile) {
            val delete = file.delete()
            OkLogger.e("deleteFile:$delete path:$path")
            return delete
        }
        return false
    }

    /**
     * 根据文件名获取MIME类型
     */
    fun guessMimeType(fileName: String): MediaType? {
        var fileName = fileName
        val fileNameMap = URLConnection.getFileNameMap()
        fileName = fileName.replace("#", "")   //解决文件名中含有#号异常的问题
        val contentType =
            fileNameMap.getContentTypeFor(fileName) ?: return HttpParams.MEDIA_TYPE_STREAM
        return contentType.toMediaTypeOrNull()
    }

    fun <T> checkNotNull(obje: T?, message: String): T {
        if (obje == null) {
            throw NullPointerException(message)
        }
        return obje
    }

    fun runOnUiThread(runnable: Runnable) {
        OkGo.instance.delivery.post(runnable)
    }
}
