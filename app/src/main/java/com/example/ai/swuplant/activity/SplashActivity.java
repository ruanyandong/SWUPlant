package com.example.ai.swuplant.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.data.PlantData;
import com.example.ai.swuplant.data.UserData;
import com.example.ai.swuplant.login.LoginActivity;
import com.example.ai.swuplant.utils.FontUtils;
import com.example.ai.swuplant.utils.IntentUtils;

public class SplashActivity extends BaseActivity {

    private Animation mFlowerAnimation,mTextAnimation,mBgAnimation,mBottomAnimation;
    private ImageView mSplashImage;
    private TextView textViewTop;
    private TextView textViewMiddle;
    private TextView textViewBottom;

    private RelativeLayout mBg;
    private LinearLayout mBottomLayout;

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
        mFlowerAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_flower_animation);
        mTextAnimation = AnimationUtils.loadAnimation(this,R.anim.text_animation);
        mBgAnimation = AnimationUtils.loadAnimation(this,R.anim.splash_bg_animation);
        mBottomAnimation = AnimationUtils.loadAnimation(this,R.anim.splash_bottom_animation);

        mBottomLayout = findViewById(R.id.bottom_layout);
        mBg = findViewById(R.id.splash_bg);
        mSplashImage = findViewById(R.id.splash_image);
        textViewTop = findViewById(R.id.splash_text_top);
        textViewMiddle = findViewById(R.id.splash_text_middle);
        textViewBottom = findViewById(R.id.splash_text_bottom);

        textViewTop.setTypeface(new FontUtils(getApplicationContext()).getTypeface());
        textViewMiddle.setTypeface(new FontUtils(getApplicationContext()).getTypeface());
        textViewBottom.setTypeface(new FontUtils(getApplicationContext()).getTypeface());

        setAnimationListener();
        mBg.startAnimation(mBgAnimation);
        mBottomLayout.startAnimation(mBgAnimation);
        mSplashImage.startAnimation(mFlowerAnimation);
        textViewTop.startAnimation(mTextAnimation);
    }

    private void setAnimationListener(){

        mFlowerAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ObjectAnimator objectAnimatorTran = ObjectAnimator.ofArgb(mSplashImage, "ColorFilter", getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.colorAccent));
                    objectAnimatorTran.setDuration(3000);
                    //objectAnimatorTran.setRepeatCount(-1);
                    objectAnimatorTran.start();
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (UserData.isLogin && PlantData.plantModelList.size()>0 && PlantData.plantInfoList.size()>0){
                            IntentUtils.showActivity(SplashActivity.this,MainActivity.class);
                        }else {
                            IntentUtils.showActivity(SplashActivity.this,LoginActivity.class);
                        }
                        finish();
                    }
                },5000);

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
