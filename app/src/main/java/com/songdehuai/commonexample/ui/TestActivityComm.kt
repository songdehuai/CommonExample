package com.songdehuai.commonexample.ui

import android.os.Bundle

import com.songdehuai.commonexample.R
import com.songdehuai.commonlib.base.CommBaseActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivityComm : CommBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test, "测试")

        send_btn.setOnClickListener {
            startMultiImagePicker()
        }

    }


}
