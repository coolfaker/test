package com.example.administrator.test.Register.Checked;

import com.example.administrator.test.Net.NetApi;
import com.example.administrator.test.Net.NetRequest;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：王文强 on 2016/11/6.
 * 邮箱：799828516@qq.com
 *
 */
public class RegisterPresenter extends MvpNullObjectBasePresenter<CheckedView>{


    private NetApi netApi;

    public void checked(String phoneNumber){
        netApi = NetRequest.getInstance().getNetApi();
        Call<CheckPhone> checkPhoneCall = netApi.checkPhone(phoneNumber);
        checkPhoneCall.enqueue(new Callback<CheckPhone>() {
            @Override
            public void onResponse(Call<CheckPhone> call, Response<CheckPhone> response) {
                if (response.isSuccessful()&&response!=null){
                    CheckPhone checkPhone = response.body();
                    if (checkPhone==null){
                        getView().showMessage("未知错误");
                        return;
                    }
                    boolean a=checkPhone.isCode();
                    String  b=checkPhone.isMsg();
                    if (!a){
                        getView().showMessage("该手机已经注册");
                        return;
                    }
                    if (a){
                        getView().showMessage("允许注册");
                    }
                    return;
                }
            }

            @Override
            public void onFailure(Call<CheckPhone> call, Throwable t) {


            }
        });

    }
}
