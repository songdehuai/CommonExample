package com.songdehuai.commonexample.ui

import android.os.Bundle

import com.songdehuai.commonexample.R
import com.songdehuai.commonlib.base.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test, "测试")

        send_btn.setOnClickListener {

        }

    }


}
