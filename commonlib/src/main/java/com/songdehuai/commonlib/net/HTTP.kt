package com.songdehuai.commonlib.net

import com.google.gson.Gson
import com.songdehuai.commonlib.expansion.logE
import okgo.OkGo
import okgo.callback.AbsCallback
import okgo.callback.StringCallback
import okgo.model.Response
import okgo.request.PostRequest
import okgo.request.base.Request
import java.lang.reflect.ParameterizedType
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import okhttp3.OkHttpClient


object HTTP {

    fun <T> post(url: String, resultCallBack: ResultCallBack<T>.() -> Unit) {
        OkGo.post<T>(url).execute(ResultCallBack<T>().also { resultCallBack })
    }

    fun <T> test(url: String, resultCallBack: ResultCallBack<T>.() -> Unit) {

    }

    open class ResultCallBack<T> : AbsCallback<T>() {

        override fun convertResponse(response: okhttp3.Response): T? {
            var data: T? = null
            val rootType = javaClass.genericSuperclass
            val type = (rootType as ParameterizedType).actualTypeArguments[0]
            response.body()?.run {
                data = Gson().fromJson(string(), type)
            }
            return data
        }

        internal var onStart: ((Request<T, out Request<*, *>>) -> Unit)? = null
        internal var onSuccess: ((T) -> Unit)? = null
        internal var onCacheSuccess: ((Response<T>) -> Unit)? = null
        internal var onError: ((String) -> Unit)? = null
        internal var onFinish: (() -> Unit)? = null

        override fun onSuccess(response: Response<T>?) {
            super.onSuccess(response)
            response?.let { onSuccess?.invoke(it.body()) }
        }

        override fun onError(response: Response<T>?) {
            super.onError(response)
            response?.let {
                it.exception.message?.let { ex ->
                    onError?.invoke(ex)
                    ex.logE()
                }
            }
        }

        fun success(result: (T) -> Unit) {
            onSuccess = result
        }

        fun error(message: (String) -> Unit) {
            onError = message
        }
    }
}