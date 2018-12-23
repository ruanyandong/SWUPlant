package com.example.ai.swuplant.net.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ai.swuplant.utils.ToastUtils;

/**
 * 网络监听广播
 * 用于局部监听
 */
public class NetworkBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()){
            ToastUtils.showToast(context,"网络已连接");
        }else {
            ToastUtils.showToast(context,"网络已断开");
        }
    }

}
