package com.example.administrator.test.Login;

import com.example.administrator.test.Net.NetApi;
import com.example.administrator.test.Net.NetRequest;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：王文强 on 2016/11/6.
 * 邮箱：799828516@qq.com
 */
public class LoginPresent extends MvpNullObjectBasePresenter<LoginView>{


    private NetApi netApi;

    public void login(User user){

        netApi = NetRequest.getInstance().getNetApi();
        Call<LoginResult> login = netApi.login(user);
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()&&response!=null){
                    LoginResult loginResult = response.body();
                    if (loginResult==null){
                        getView().showMessage("未能返回数据");
                        return;
                    }
                    int code=loginResult.getCode();
                    String message=loginResult.getMsg();
                    String key=loginResult.getKey();
                    if (code==401){
                        getView().showMessage(message);
                        return;
                    }
                    if (code==402){
                        getView().showMessage(message);
                        return;
                    }
                    if (code==404){
                        getView().showMessage(message);
                        return;
                    }
                    if (code==405){
                        getView().showMessage(message);
                        return;
                    }
                    if (code==200){
                        getView().showMessage(key);
                        getView().intentView();
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                getView().showMessage(t.getMessage());

            }
        });


    }
}
