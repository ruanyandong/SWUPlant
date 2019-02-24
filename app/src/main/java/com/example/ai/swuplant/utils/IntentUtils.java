package com.example.ai.swuplant.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtils {


    public static <T>void showActivity(Context context,Class<T> clazz){
        showActivity(context,clazz,null);
    }

    public static <T>void showActivity(Context context, Class<T> clazz, Bundle bundle){
        showActivity(context,clazz,null,bundle);
    }

    public static <T>void showActivity(Context context, Class<T> clazz, String key, Bundle bundle){

        if (context == null || clazz == null){
            try {
                throw new Exception("Context of Intent or Class of Intent is null");
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }
            return;
        }

        Intent intent = new Intent(context,clazz);

        if (key == null && bundle == null){
            context.startActivity(intent);
        } else if (key == null && bundle != null){
            intent.putExtras(bundle);
            context.startActivity(intent);
        } else if (key != null && bundle == null){
            context.startActivity(intent);
        } else if (key != null && bundle != null){
            intent.putExtra(key,bundle);
            context.startActivity(intent);
        }

    }

}
