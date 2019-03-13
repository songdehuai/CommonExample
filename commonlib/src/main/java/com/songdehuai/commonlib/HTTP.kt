package com.songdehuai.commonlib

import com.songdehuai.commonlib.http.HttpExcutor
import com.songdehuai.commonlib.http.imp.HttpExcutorImp

object HTTP {

    private var httpExecutor: HttpExcutor = HttpExcutorImp()

    fun post(uri: String, params: HashMap<String, Any>): String {
        return httpExecutor.post(uri, params)
    }

    fun post(uri: String): String {
        return httpExecutor.post(uri)
    }
}
