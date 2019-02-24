package com.example.ai.swuplant.net.netframe;

import com.example.ai.swuplant.net.bean.LoginBackResult;
import com.example.ai.swuplant.net.bean.RegisterBackResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("RegisterServlet")
    Observable<RegisterBackResult> getRegisterBackResult(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("LoginServlet")
    Observable<LoginBackResult> getLoginBackResult(@Field("username") String username,@Field("password") String password);

}
