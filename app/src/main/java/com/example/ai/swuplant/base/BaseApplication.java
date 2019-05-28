package com.example.ai.swuplant.base;

import android.app.Application;
import com.baidu.mapapi.SDKInitializer;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class BaseApplication extends Application {


//    public static List<Integer> plantImageIdList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        //init();

        initLogger();
    }

    private void initLogger(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
    private void init(){

//        TypedArray ar = getApplicationContext().getResources().obtainTypedArray(
//                R.array.plantImageId);
//        int len = ar.length();
//        for (int i = 0; i < len; i++){
//            plantImageIdList.add(ar.getResourceId(i, 0));
//        }
//        ar.recycle();

    }

}
