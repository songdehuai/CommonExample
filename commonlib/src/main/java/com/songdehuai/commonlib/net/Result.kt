package com.songdehuai.commonlib.net


import com.google.gson.annotations.SerializedName

class Result<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: T
)