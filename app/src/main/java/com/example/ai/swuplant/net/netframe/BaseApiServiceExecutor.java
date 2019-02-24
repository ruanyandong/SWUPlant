package com.example.ai.swuplant.net.netframe;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseApiServiceExecutor {
    protected <T> Observable<T> observe(Observable<T> observable){
        return observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
