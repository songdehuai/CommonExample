package com.songdehuai.commonlib.net


import android.os.Handler
import android.os.Looper
import com.alibaba.fastjson.JSONObject

import java.lang.reflect.ParameterizedType

import okgo.callback.AbsCallback
import okhttp3.Response

abstract class ResultCallBack<T>() : AbsCallback<T>() {

    @Throws(Throwable::class)
    override fun convertResponse(response: Response): T? {
        var data: T? = null
        val rootType = javaClass.genericSuperclass
        val type = (rootType as ParameterizedType).actualTypeArguments[0]
        data = JSONObject.parseObject<T>(response.body()!!.string(), type)
        return data
    }

}
