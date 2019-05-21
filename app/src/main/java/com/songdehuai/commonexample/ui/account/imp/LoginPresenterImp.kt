package com.songdehuai.commonexample.ui.account.imp

import com.songdehuai.commonexample.ui.account.entity.LoginParams
import com.songdehuai.commonexample.ui.account.presenter.LoginPresenter
import com.songdehuai.commonexample.ui.account.view.LoginView
import com.songdehuai.commonlib.net.Result
import com.songdehuai.commonlib.net.ResultCallBack
import okgo.model.Response

class LoginPresenterImp : LoginPresenter {

    private var loginView: LoginView

    constructor(loginView: LoginView) {
        this.loginView = loginView
    }


    override fun detach() {

    }

    override fun login(loginParams: LoginParams) {
        loginParams.postJson(
            "http://hxkj.f3322.net/freight/login",
            object : ResultCallBack<Result<Any>>() {
                override fun onSuccess(response: Response<Result<Any>>?) {
                    super.onSuccess(response)
                    if ("200" == response?.body()?.code) {
                        loginView.loginSuccess()
                    } else {
                        response?.body()?.message?.let { loginView.loginError(it) }
                    }
                }

                override fun onError(response: Response<Result<Any>>?) {
                    super.onError(response)
                    response?.body()?.message.let {
                        it?.let { it1 -> loginView.loginError(it1) }
                    }
                }
            })
    }
}
