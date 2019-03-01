package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;

public class HelpActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBarTitle("帮助");
        setTitleRTBtnVisiable(View.INVISIBLE);
        setCenterClick(false);

        initView();
        loadHtml();
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.helpWebView);

    }

    private void loadHtml(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/html/index.html");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

}
