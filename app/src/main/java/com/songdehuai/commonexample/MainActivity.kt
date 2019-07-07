package com.songdehuai.commonexample

import android.os.Bundle
import com.google.gson.Gson
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.net.*
import com.tencent.mmkv.MMKV
import dhttp.core.EHttp
import kotlinx.android.synthetic.main.activity_main.*
import okgo.model.Response
import java.net.URL

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MMKV.initialize(this)

        initViews()
    }

    val mUrl = "http://94.191.9.222:8080/pet/test/getAllUrl"

    fun initViews() {

        judge_btn.setOnClickListener {
          
        }
    }


}
