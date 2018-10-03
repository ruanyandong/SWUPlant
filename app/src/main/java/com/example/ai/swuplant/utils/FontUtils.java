package com.example.ai.swuplant.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

public class FontUtils {

    private Typeface typeface;
    private Context context;

    public FontUtils(Context context) {
        this.context = context;
        typeface = Typeface.createFromAsset(context.getAssets(),"fonts/youyuan.TTF");
    }

    public Typeface getTypeface() {
        return typeface;
    }

}
