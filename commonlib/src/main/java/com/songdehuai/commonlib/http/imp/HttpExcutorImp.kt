package com.songdehuai.commonlib.http.imp

import com.songdehuai.commonlib.http.HttpExcutor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.xutils.common.util.LogUtil
import org.xutils.http.HttpMethod
import org.xutils.http.RequestParams
import org.xutils.x
import java.util.*

class HttpExcutorImp : HttpExcutor {

    override fun post(uri: String): String {

        val task = GlobalScope.launch {
            val params = RequestParams(uri)
            x.http().requestSync(HttpMethod.GET, params, String::class.java)
        }
        return ""
    }


    override fun post(uri: String, params: HashMap<String, Any>): String {
        return ""
    }

}