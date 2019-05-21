package com.songdehuai.commonexample.ui.account.view

import com.songdehuai.commonlib.base.IDataView

interface LoginView : IDataView {

    fun loginSuccess()

    fun loginError(str: String)
}
