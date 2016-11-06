package com.example.administrator.test.Net;

import com.example.administrator.test.Login.User;
import com.example.administrator.test.Login.LoginResult;
import com.example.administrator.test.Register.Checked.CheckPhone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 作者：王文强 on 2016/11/6.
 * 邮箱：799828516@qq.com
 */
public interface NetApi {

   @POST("guolian/index.php?m=Engineer&c=Register&a=index")
    Call<LoginResult> login(@Body User user);

    @GET("guolian/index.php?m=Engineer&c=Register&a=exists&")
    Call<CheckPhone>  checkPhone(@Query("phone") String number );

}
