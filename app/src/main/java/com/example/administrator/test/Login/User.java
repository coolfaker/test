package com.example.administrator.test.Login;

/**
 * 作者：王文强 on 2016/11/6.
 * 邮箱：799828516@qq.com
 */
public class User {

    private String phoneNumber;
    private String password;

    public User(String password, String phoneNumber) {
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
