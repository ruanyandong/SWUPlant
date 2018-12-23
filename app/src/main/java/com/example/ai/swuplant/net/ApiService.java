package com.example.ai.swuplant.net;

import com.example.ai.swuplant.net.bean.RegisterBackResult;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("RegisterServlet")
    Observable<RegisterBackResult> getBackResult(@Field("username") String username,@Field("password") String password);
}
