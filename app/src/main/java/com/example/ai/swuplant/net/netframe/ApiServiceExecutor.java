package com.example.ai.swuplant.net.netframe;

import com.example.ai.swuplant.net.bean.LoginBackResult;
import com.example.ai.swuplant.net.bean.RegisterBackResult;

import java.util.logging.Handler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;


public class ApiServiceExecutor extends BaseApiServiceExecutor {

    private ApiService mApiService;

    private ApiServiceExecutor(){
        mApiService = RetrofitServiceManager.getInstance().create(ApiService.class);
    }

    private static final class Singleton{
        private static final ApiServiceExecutor INSTANCE = new ApiServiceExecutor();
    }

    public static ApiServiceExecutor getInstance(){
        return Singleton.INSTANCE;
    }

    public void registerUser(String username, String password, HttpCallBack httpCallBack){
        observe(mApiService.getRegisterBackResult(username,password)).map(new Function<RegisterBackResult, RegisterBackResult>() {
            @Override
            public RegisterBackResult apply(RegisterBackResult registerBackResult) throws Exception {
                return registerBackResult;
            }
        }).subscribe(new Observer<RegisterBackResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBackResult registerBackResult) {
                httpCallBack.onSuccess(registerBackResult);
            }

            @Override
            public void onError(Throwable e) {
                httpCallBack.onFailure(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void loginUser(String username, String password, HttpCallBack httpCallBack){
        observe(mApiService.getLoginBackResult(username,password)).subscribe(new Observer<LoginBackResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBackResult result) {
                httpCallBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable e) {
                httpCallBack.onFailure(e);
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
