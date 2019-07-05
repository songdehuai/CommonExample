package com.songdehuai.commonexample


import com.google.gson.annotations.SerializedName

data class TestData(
    @SerializedName("className")
    val className: String,
    @SerializedName("method")
    val method: String,
    @SerializedName("url")
    val url: String
)