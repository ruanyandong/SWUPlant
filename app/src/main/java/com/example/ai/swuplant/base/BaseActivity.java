package com.example.ai.swuplant.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.customcomponent.TitleBar;

public abstract class BaseActivity extends AppCompatActivity {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Light_NoTitleBar);
        setContentView(getLayoutId());

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initBaseView();
    }

    public void initBaseView() {
        mTitleBar = (TitleBar) findViewById(R.id.base_titlebar);
    }


    public void setTitleBarTitle(String tite) {
        if (mTitleBar != null) {
            mTitleBar.setTitle(tite);
        }
    }

    public void setTitleBarTitle(int titleId) {
        if (mTitleBar != null) {
            mTitleBar.setTitle(getString(titleId));
        }
    }

    public void setTitleBarTitleDrawable(Drawable drawable){
        if (mTitleBar != null) {
            mTitleBar.setTitleRightDrawable(drawable);
        }
    }

    //返回键事件
    public void finishActivity() {
        finish();
    }

    public void setTitleRTBtnVisiable(int visiable) {
        if (mTitleBar != null) {
            mTitleBar.setRTBtnVisiable(visiable);
        }

    }

    public void setTitleRTBtnText(String text) {
        if (mTitleBar != null) {
            mTitleBar.setRTBtnText(text);
        }
    }

    public void setTitleRTBtnText(int textId) {
        if (mTitleBar != null) {
            mTitleBar.setRTBtnText(getString(textId));
        }
    }

    public void setTitleRTBtnFocusable(boolean focusable) {
        if (mTitleBar != null) {
            mTitleBar.setRTBtnFocusable(focusable);
        }
    }
    //中间的Title点击事件
    public void setCenterClick(boolean bool) {
        mTitleBar.setTitleClick(bool);
    }

    public void onRtBtnClick() {
        // titlebar左上角button的click时间
    }

    public void onCenterClick() {
        // titlebar中间的title点击事件

    }


    protected void initView(){

    }

    protected void initData(){

    }

    protected void initEvent(){

    }




    protected abstract int getLayoutId();


}
