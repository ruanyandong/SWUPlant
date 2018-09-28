package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.utils.IntentUtils;

public class SplashActivity extends BaseActivity {

    private Animation mAnimation;
    private ImageView mSplashImage;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isTaskRoot()){
            finish();
            return;
        }


        initView();
    }

    @Override
    protected void initView() {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        mSplashImage = findViewById(R.id.splash_image);
        setAnimationListener();
        mSplashImage.startAnimation(mAnimation);
    }


    private void setAnimationListener(){

        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                IntentUtils.showActivity(SplashActivity.this,MainActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

}
