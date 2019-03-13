package com.songdehuai.commonlib.http

interface HttpExcutor {

    fun post(uri: String): String

    fun post(uri: String, params: HashMap<String, Any>): String
}