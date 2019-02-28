package com.example.ai.swuplant.net.netframe;

import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.entity.PointInfo;
import com.example.ai.swuplant.net.bean.LoginBackResult;
import com.example.ai.swuplant.net.bean.RegisterBackResult;

import java.util.List;
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

    /**
     * 注册用户
     * @param username
     * @param password
     * @param httpCallBack
     */
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

    /**
     * 用户登录
     * @param username
     * @param password
     * @param httpCallBack
     */
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

    /**
     * 获取全部植物
     * @param httpCallBack
     */
    public void getPlantModel(HttpCallBack httpCallBack){
        observe(mApiService.getPlantModelList()).subscribe(new Observer<List<PlantModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PlantModel> plantModels) {
                httpCallBack.onSuccess(plantModels);
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

    /**
     * 获取植物点位信息
     * @param httpCallBack
     */
    public void getplantPointInfo(HttpCallBack httpCallBack){
        observe(mApiService.getPointInfoList()).subscribe(new Observer<List<PointInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PointInfo> pointInfos) {
                httpCallBack.onSuccess(pointInfos);
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

    /**
     * 获取竹类植物
     * @param httpCallBack
     */
    public void getBamboo(HttpCallBack httpCallBack){
        observe(mApiService.getBambooList()).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                httpCallBack.onSuccess(strings);
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

    /**
     * 获取蕨类植物
     * @param httpCallBack
     */
    public void getFern(HttpCallBack httpCallBack){
        observe(mApiService.getFernList()).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                httpCallBack.onSuccess(strings);
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

    /**
     * 获取裸子植物
     * @param httpCallBack
     */
    public void getGymnosperm(HttpCallBack httpCallBack){
        observe(mApiService.getGymnospermList()).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {
                httpCallBack.onSuccess(strings);
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
