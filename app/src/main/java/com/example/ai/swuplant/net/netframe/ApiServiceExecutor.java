package com.example.ai.swuplant.net.netframe;

import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.entity.PointInfo;
import com.example.ai.swuplant.net.bean.CollectionBackResult;
import com.example.ai.swuplant.net.bean.LoginBackResult;
import com.example.ai.swuplant.net.bean.RegisterBackResult;
import com.example.ai.swuplant.net.bean.UpdatePasswordBackResult;
import java.util.List;
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
        observe(mApiService.registerUser(username,password)).map(new Function<RegisterBackResult, RegisterBackResult>() {
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
     * 用户修改密码
     * @param username
     * @param newPassword
     * @param httpCallBack
     */
    public void updateUserPassword(String username,String newPassword,HttpCallBack httpCallBack){
        observe(mApiService.updateUserPassword(username,newPassword)).subscribe(new Observer<UpdatePasswordBackResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UpdatePasswordBackResult updatePasswordBackResult) {
                httpCallBack.onSuccess(updatePasswordBackResult);
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
        observe(mApiService.userLogin(username,password)).subscribe(new Observer<LoginBackResult>() {
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

    /**
     * 收藏接口
     * @param isCollection
     * @param username
     * @param plantNames
     * @param httpCallBack
     */
    public void collectionPlant(boolean isCollection,String username,String plantNames,HttpCallBack httpCallBack){
        observe(mApiService.collectionPlant(isCollection,username,plantNames)).subscribe(new Observer<CollectionBackResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CollectionBackResult collectionBackResult) {
                httpCallBack.onSuccess(collectionBackResult);
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
     * 获取收藏列表
     * @param username
     * @param httpCallBack
     */
    public void getCollectionList(String username,HttpCallBack httpCallBack){
        observe(mApiService.getCollectionList(username)).subscribe(new Observer<CollectionBackResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CollectionBackResult collectionBackResult) {
                httpCallBack.onSuccess(collectionBackResult);
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
