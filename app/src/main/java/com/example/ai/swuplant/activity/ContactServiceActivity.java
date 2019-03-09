package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;

public class ContactServiceActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBarTitle(this.getResources().getString(R.string.contact_service));
        setTitleRTBtnVisiable(View.INVISIBLE);
        setCenterClick(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_service;
    }
}
