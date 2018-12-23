package com.example.ai.swuplant.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author：AI
 * time ：2018/12/18
 */
public class RetrofitServiceManager {

    private static final int DEFAULT_TIME_OUT = 10;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private static final int DEFAULT_WRITE_TIME_OUT = 10;

    private Retrofit mRetrofit;

    private RetrofitServiceManager(){
        mRetrofit = getRetrofit();
    }

    private OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT,TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT,TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor());
        return builder.build();
    }

    private Retrofit getRetrofit(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(getOkHttpClient())
                .baseUrl(ApiConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    private static final class Singleton{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    public static RetrofitServiceManager getInstance(){
        return Singleton.INSTANCE;
    }

    public <T> T create(Class<T> clazz){
        return mRetrofit.create(clazz);
    }

}
