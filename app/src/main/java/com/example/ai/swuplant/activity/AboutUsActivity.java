package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;

public class AboutUsActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBarTitle(this.getResources().getString(R.string.about_us));
        setTitleRTBtnVisiable(View.INVISIBLE);
        setCenterClick(false);

        initView();
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/html/about_us.html");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

}
