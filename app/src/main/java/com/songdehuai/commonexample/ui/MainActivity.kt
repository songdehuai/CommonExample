package com.songdehuai.commonexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lzy.okgo.model.Response
import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.account.entity.LoginEntity
import com.songdehuai.commonexample.ui.account.entity.LoginParams
import com.songdehuai.commonlib.net.Result
import com.songdehuai.commonlib.net.ResultCallBack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        test_btn.setOnClickListener {
            for (i in 0..100) {
                val loginParams = LoginParams()
                loginParams.builder<LoginParams> {
                    it.loginName = "17649851614"
                    it.loginPwd = "111111"
                    it.appType = "1"
                    it.postJson(
                        "http://192.168.2.102/freight/login",
                        object : ResultCallBack<String>() {
                            override fun onSuccess(response: Response<String>?) {
                                log(response!!.body() + "  " + i)
                            }
                        })
                }
            }

        }
    }

    private fun log(str: String) {
        log_et.append("$str\n")
    }
}
