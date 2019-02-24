package com.example.ai.swuplant.net.netframe;

import java.util.Observable;

public interface HttpCallBack {
    public  void onSuccess(Object response);
    public void onFailure(Throwable e);
}
