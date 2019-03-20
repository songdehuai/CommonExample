package com.songdehuai.commonexample.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.lzy.okgo.model.Response
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.account.entity.LoginParams
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.net.Result
import com.songdehuai.commonlib.net.ResultCallBack
import com.songdehuai.commonlib.utils.FreeSync
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, "你好", "确定")
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener {
            showImagePickerDialog()
        }
        status_tn.setOnClickListener {
            startCamera()
        }
    }

    override fun onGetImage(filePath: String?) {
        super.onGetImage(filePath)

    }

    override fun onPublish() {
        super.onPublish()
        showDialog("你好你好")
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
