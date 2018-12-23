package com.example.ai.swuplant.net.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.example.ai.swuplant.utils.ToastUtils;
import com.orhanobut.logger.Logger;
/**
 * 监听网络的广播
 * 全局监听
 */
public class NetworkBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("==========网络状态发生了变化=============");
        // 检测api是不是小于23，因为api23之后getNetworkInfo(int networkType)被弃用
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            Logger.d("==============API小于23================");
            // 获得ConnectivityManager对象
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取ConnectivityManager对象对应的NetworkInfo对象
            // 获取连接wifi的信息
            NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            // 获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()){
                ToastUtils.showToast(context,"wifi已连接，移动数据已连接");
            }else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()){
                ToastUtils.showToast(context,"wifi已连接，移动数据已断开");
            }else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()){
                ToastUtils.showToast(context,"wifi已断开，移动数据已连接");
            }else {
                ToastUtils.showToast(context,"wifi已断开，移动数据已断开");
            }
        }else {
            Logger.d("================API大于23===============");
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (int i=0; i < networks.length; i++){
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            }
            ToastUtils.showToast(context,sb.toString());
        }
    }

}
