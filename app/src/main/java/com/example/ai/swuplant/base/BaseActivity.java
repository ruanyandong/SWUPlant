package com.example.ai.swuplant.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

    }

    protected void initView(){

    }

    protected void initData(){

    }

    protected void initEvent(){

    }


    /**
     * 核对是否有权限
     * @param permissions
     * @return
     */
    protected boolean hasPermission(Context context,String... permissions){

        for (String permission:permissions){

            if (ContextCompat.checkSelfPermission(context,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }

        return true;
    }

    /**
     * 权限申请
     * @param code
     * @param permissions
     */
    protected void requestPermissions(Context context, int code, String... permissions){

        if (context instanceof Activity){
            Activity activity = (Activity)context;
            ActivityCompat.requestPermissions(activity,permissions,code);
        }else{

            throw new IllegalArgumentException("参数错误异常");
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    protected abstract int getLayoutId();


}
