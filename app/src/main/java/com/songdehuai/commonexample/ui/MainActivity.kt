package com.songdehuai.commonexample.ui

import android.os.Bundle
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.account.entity.LoginParams
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.net.ResultCallBack
import kotlinx.android.synthetic.main.activity_main.*
import okgo.model.Response

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
            val loginParams = LoginParams()
            loginParams.loginName = "17649851614"
            loginParams.loginPwd = "111111"
            loginParams.postJson(
                "http://192.168.2.102/freight/login",
                object : ResultCallBack<com.songdehuai.commonlib.net.Result<String>>() {
                    override fun onSuccess(response: Response<com.songdehuai.commonlib.net.Result<String>>?) {
                        log(response?.body().toString())
                    }
                })
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
