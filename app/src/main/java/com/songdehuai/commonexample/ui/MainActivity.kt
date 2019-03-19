package com.songdehuai.commonexample.ui

import android.os.Bundle
import com.lzy.okgo.model.Response
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.account.entity.LoginParams
import com.songdehuai.commonlib.base.BaseActivity
import com.songdehuai.commonlib.net.Result
import com.songdehuai.commonlib.net.ResultCallBack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main,"你好")
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener {
            val loginParams = LoginParams()
            loginParams.userType = "1"
            loginParams.loginName = "17649851614"
            loginParams.loginPwd = "111111"
            loginParams.appType = "1"
            loginParams.userType = "2"
            loginParams.postJson(
                "http://192.168.2.102/freight/login",
                object : ResultCallBack<Result<*>>() {
                    override fun onSuccess(response: Response<Result<*>>?) {
                        log(response?.body().toString())
                    }
                })
        }
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
