package com.example.administrator.test;

import android.app.Application;

import cn.bmob.sms.BmobSMS;

/**
 * 作者：王文强 on 2016/11/6.
 * 邮箱：799828516@qq.com
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        BmobSMS.initialize(getApplicationContext(),"43e8ac17ebc98192981c7bac7a8ced67");
    }
}
