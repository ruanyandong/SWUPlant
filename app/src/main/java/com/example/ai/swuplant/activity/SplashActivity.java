package com.example.ai.swuplant.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.utils.FontUtils;
import com.example.ai.swuplant.utils.IntentUtils;

public class SplashActivity extends BaseActivity {

    private Animation mAnimation;
    private ImageView mSplashImage;
    private TextView textViewTop;
    private TextView textViewMiddle;
    private TextView textViewBottom;


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
        textViewTop = findViewById(R.id.splash_text_top);
        textViewMiddle = findViewById(R.id.splash_text_middle);
        textViewBottom = findViewById(R.id.splash_text_bottom);

        textViewTop.setTypeface(new FontUtils(getApplicationContext()).getTypeface());
        textViewMiddle.setTypeface(new FontUtils(getApplicationContext()).getTypeface());
        textViewBottom.setTypeface(new FontUtils(getApplicationContext()).getTypeface());

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
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ObjectAnimator objectAnimatorTran = ObjectAnimator.ofArgb(mSplashImage, "ColorFilter", getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.colorAccent));
                    //objectAnimatorTran.setDuration(500);
                    //objectAnimatorTran.setRepeatCount(-1);
                    objectAnimatorTran.start();
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IntentUtils.showActivity(SplashActivity.this,MainActivity.class);
                        finish();
                    }
                },1000);

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
