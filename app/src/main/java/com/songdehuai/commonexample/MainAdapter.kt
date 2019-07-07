package com.songdehuai.commonexample


import com.songdehuai.commonlib.utils.kadapter.KTAdapterFactory.KAdapter

val TestAdapter = KAdapter<String>(R.layout.item_image) {

    bindData { type, vh, data ->

    }
}