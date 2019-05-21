package com.songdehuai.commonexample.ui.account

import android.os.Bundle

import com.songdehuai.commonexample.R
import com.songdehuai.commonexample.ui.account.presenter.LoginPresenter
import com.songdehuai.commonexample.ui.account.view.LoginView
import com.songdehuai.commonlib.base.BaseActivity

import com.songdehuai.commonexample.ui.account.entity.LoginParams
import com.songdehuai.commonexample.ui.account.imp.LoginPresenterImp
import com.songdehuai.commonlib.net.ResultCallBack
import kotlinx.android.synthetic.main.activity_login.*
import okgo.model.Response

class LoginActivity : BaseActivity(), LoginView {

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login, "login")
        presenter = LoginPresenterImp(this)

        login_btn.setOnClickListener {
            val params = LoginParams()
            params.appType = "1"
            params.loginName = "17649851614"
            params.loginPwd = "111111"
            params.deviceType = "1"
            params.userType = "2"
            params.postJson("http://hxkj.f3322.net/freight/login",object:ResultCallBack<com.songdehuai.commonlib.net.Result<Any>>(){
                override fun onSuccess(response: Response<com.songdehuai.commonlib.net.Result<Any>>?) {
                    super.onSuccess(response)
                    if ("200" == response?.body()?.code) {
                        loginSuccess()
                    }
                }
            })
        }

    }


    override fun loginSuccess() {
        showDialog("登录成功")
    }

    override fun loginError(str: String) {
        showDialog(str)
    }
}
