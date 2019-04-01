package com.songdehuai.commonlib.net

import com.alibaba.fastjson.JSONObject

/**
 * 数据泛型
 *
 * @param <T>
</T> */
class Result<T> {

    var code: String? = null
    var message: String? = null
    var t: T? = null

    override fun toString(): String {
        return JSONObject.toJSONString(this)
    }
}
