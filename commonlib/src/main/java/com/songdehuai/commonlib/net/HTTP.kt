package com.songdehuai.commonlib.net

import okgo.OkGo
import okgo.callback.StringCallback
import okgo.model.Response

object HTTP {


    fun <T> post(url: String): POST<T> {
        return POST<T>()
    }

    class POST<T> {

        lateinit var mResultDSLCallBack: ResultDSLCallBack<T>

        fun execute(resultDSLCallBack: ResultDSLCallBack<T>.() -> Unit) {
            mResultDSLCallBack = ResultDSLCallBack<T>().also(resultDSLCallBack)
            OkGo.post<String>("http://94.191.9.222:8080/pet/test/getAllUrl")
                    .execute(object : StringCallback() {
                        override fun onSuccess(response: Response<String>?) {
                            super.onSuccess(response)
                            response?.body()?.let {
                                mResultDSLCallBack.onSuccess?.invoke(it as T)
                            }
                        }
                    })

        }
    }

}