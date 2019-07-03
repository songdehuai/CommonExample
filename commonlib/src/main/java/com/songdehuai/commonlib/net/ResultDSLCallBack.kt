package com.songdehuai.commonlib.net

import okgo.request.base.Request

class ResultDSLCallBack<T> {

    internal var onStart: ((Request<T, out Request<*, *>>) -> Unit)? = null
    internal var onSuccess: ((T) -> Unit)? = null
    internal var onCacheSuccess: ((T) -> Unit)? = null
    internal var onError: ((T) -> Unit)? = null
    internal var onFinish: (() -> Unit)? = null

    fun onStart(request: (Request<T, out Request<*, *>>) -> Unit) {

    }

    fun onCacheSuccess(result: (T) -> Unit) {

    }

    fun onSuccess(result: (T) -> Unit) {
        onSuccess = result
    }

    fun onError(result: (T) -> Unit) {

    }

    fun onFinish() {

    }

}