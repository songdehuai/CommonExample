package com.songdehuai.commonexample.ui.account.presenter;

import com.songdehuai.commonexample.ui.account.entity.LoginParams;
import com.songdehuai.commonlib.base.IBasePresenter;

public interface LoginPresenter extends IBasePresenter {

    void login(LoginParams loginParams);
}
