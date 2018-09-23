package com.example.ai.swuplant.activity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.entity.PlantModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashActivity extends BaseActivity {

    private Animation mAnimation;
    private ImageView mSplashImage;

    public static List<String> plantCnNameList = new ArrayList<>();
    public static List<String> plantEnNameList = new ArrayList<>();
    public static List<String> plantPropertyList = new ArrayList<>();
    public static List<String> plantDescriptionList = new ArrayList<>();
    public static List<String> plantDistributionList = new ArrayList<>();
    public static List<Integer> plantImageIdList = new ArrayList<>();
    public static List<PlantModel> plantModelList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isTaskRoot()){

            finish();
            return;
        }

        initView();

        initData();

        initEvent();


    }

    @Override
    protected void initView() {
        mAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        mSplashImage = findViewById(R.id.splash_image);
        setAnimationListener();
        mSplashImage.startAnimation(mAnimation);
    }

    @Override
    protected void initEvent() {

    }

    private void setAnimationListener(){

        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                showActivity(SplashActivity.this,MainActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void initData() {

        plantCnNameList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantCN));
        plantEnNameList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantEn));
        plantPropertyList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantProperty));
        plantDescriptionList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantDescription));
        plantDistributionList = Arrays.asList(getApplicationContext().getResources().getStringArray(R.array.plantDistribution));

        TypedArray ar = getApplicationContext().getResources().obtainTypedArray(
                R.array.plantImageId);
        int len = ar.length();
        for (int i = 0; i < len; i++){
            plantImageIdList.add(ar.getResourceId(i, 0));
        }
        ar.recycle();

        for (int i = 0; i < plantCnNameList.size(); i++) {
            PlantModel plantModel = new PlantModel(
                    plantImageIdList.get(i),
                    plantCnNameList.get(i),
                    plantEnNameList.get(i),
                    plantPropertyList.get(i),
                    plantDescriptionList.get(i),
                    plantDistributionList.get(i));
            plantModelList.add(plantModel);
        }



    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

}
