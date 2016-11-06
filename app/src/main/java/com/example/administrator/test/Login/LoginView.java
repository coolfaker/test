package com.example.administrator.test.Login;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * 作者：王文强 on 2016/11/6.
 * 邮箱：799828516@qq.com
 */
public interface LoginView extends MvpView {
    public void showMessage(String Msg);
    public void intentView();

}
