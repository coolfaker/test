package com.example.administrator.test.Net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：王文强 on 2016/11/6.
 * 邮箱：799828516@qq.com
 */
public class NetRequest {
    private static NetRequest netRequest;
    private NetApi netApi;
    private final Retrofit retrofit;


    public static NetRequest getInstance() {
        if (netRequest==null){
            netRequest=new NetRequest();
        }
        return netRequest;
    }

   private NetRequest() {

       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

       OkHttpClient okHttpClient = new OkHttpClient.Builder()
               .addInterceptor(interceptor)
               .build();

       //Retrofit 强大的功能：Gson转换器----将我们的数据请求的结果进行json转换，转换为我们需要的类型,例如类或者集合
       retrofit = new Retrofit.Builder()
               .baseUrl("http://caipu.qigle.com/")
               .client(okHttpClient)

               //Retrofit 强大的功能：Gson转换器----将我们的数据请求的结果进行json转换，转换为我们需要的类型,例如类或者集合
               .addConverterFactory(GsonConverterFactory.create())
               .build();

   }

    public NetApi getNetApi() {
        netApi = retrofit.create(NetApi.class);
        return netApi;
    }
}
