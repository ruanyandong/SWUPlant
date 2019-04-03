package com.example.ai.swuplant.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.data.PlantData;
import com.example.ai.swuplant.data.UserData;
import com.example.ai.swuplant.net.bean.CollectionBackResult;
import com.example.ai.swuplant.net.netframe.ApiServiceExecutor;
import com.example.ai.swuplant.net.netframe.HttpCallBack;
import com.example.ai.swuplant.net.utils.netUtil;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.ToastUtils;
import com.example.customdialog.SweetAlertDialog;
import java.util.List;

public class PlantDetailActivity extends BaseActivity {

    private ImageView plantImageView;
    private ImageView imgHeart;
    private TextView plantCnNameTv;
    private TextView plantEnNameTv;
    private TextView plantPropertyTv;
    private TextView plantDescriptionTv;
    private TextView plantDistributionTv;

    private String plantName = null;
    private boolean isHave = false;

    private Toolbar toolBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private ImageView bottomImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        initData();

        initEvent();

    }

    @Override
    protected void initEvent() {

        /**
         * 在android 5.0之前，我们可以通过下面的方式判断当前的ImageView使用的是哪张图片
         *
         * if(img.getDrawable().getCurrent().getConstantState().equals(getResources().getDrawable(R.mipmap.a).getConstantState()))
         * {
         * //是图片a
         * }else{
         * //不是
         * }
         *
         * 当在android 5.0以后再使用此种方式去判断的话，会发现即使当前的img设置的图片为 a，但是两者比较后是不相等的，因为在android 5.0以后这中方式就不行了，需要用以下的方式去比较：
         * if((img.getDrawable().getCurrent().getConstantState()).equals(ContextCompat.getDrawable(this,R.mipmap.a).getConstantState()))
         * {
         * //是图片a
         * } else {
         * //不是
         * }
         *
         * android 5.0 以后获取drawable的方式应该改为：ContextCompat.getDrawable(this,R.mipmap.a)。
         */
        Drawable.ConstantState hollowHeart = ContextCompat.getDrawable(this,R.drawable.hollow_heart).getConstantState();
        Drawable.ConstantState solidHeart = ContextCompat.getDrawable(this,R.drawable.solid_heart).getConstantState();
        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantName = plantCnNameTv.getText().toString();
                Drawable.ConstantState drawable = imgHeart.getDrawable().getCurrent().getConstantState();
                ApiServiceExecutor.getInstance().getCollectionList(UserData.username, new HttpCallBack() {
                    @Override
                    public void onSuccess(Object response) {
                        if (response != null){
                            CollectionBackResult result = (CollectionBackResult) response;
                            if (result.getCode() == 200){
                                List<String> plantNames = result.getData().getPlantNames();
                                if (plantNames != null){
                                    isHave = isContains(plantName,plantNames);
                                }
                                if (drawable.equals(hollowHeart)){
                                    if (!isHave){
                                        ApiServiceExecutor.getInstance().collectionPlant(true, UserData.username, plantName, new HttpCallBack() {
                                            @Override
                                            public void onSuccess(Object response) {
                                                if (response != null){
                                                    CollectionBackResult result = (CollectionBackResult) response;
                                                    if (result.getCode() == 200){
                                                        imgHeart.setImageResource(R.drawable.solid_heart);
                                                        ToastUtils.showToast(getApplicationContext(),PlantDetailActivity.this.getResources().getString(R.string.collection_success));
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onFailure(Throwable e) {
                                                e.getMessage();
                                            }
                                        });
                                    }
                                }else if (drawable.equals(solidHeart)){
                                    if (isHave){
                                        ApiServiceExecutor.getInstance().collectionPlant(false, UserData.username, plantName, new HttpCallBack() {
                                            @Override
                                            public void onSuccess(Object response) {
                                                if (response != null){
                                                    CollectionBackResult result = (CollectionBackResult) response;
                                                    if (result.getCode() == 200){
                                                        imgHeart.setImageResource(R.drawable.hollow_heart);
                                                        ToastUtils.showToast(getApplicationContext(),PlantDetailActivity.this.getResources().getString(R.string.cancel_collection));
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Throwable e) {
                                                e.getMessage();
                                            }
                                        });
                                    }
                                }

                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                });

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            plantName = bundle.getString(Constant.PLANT_NAME);

        }

        toolBar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(plantName);

        bottomImage = findViewById(R.id.plant_detail_image_bottom);
        plantImageView = findViewById(R.id.plant_detail_image);
        imgHeart = findViewById(R.id.plant_detail_heart);
        plantCnNameTv = findViewById(R.id.plant_detail_CNname);
        plantEnNameTv = findViewById(R.id.plant_detail_ENname);
        plantPropertyTv = findViewById(R.id.plant_detail_property);
        plantDescriptionTv = findViewById(R.id.plant_detail_description);
        plantDistributionTv = findViewById(R.id.plant_detail_distribution);
    }

    private SweetAlertDialog loadingDialog = null;

    @Override
    protected void initData() {

        if (netUtil.isNetworkAvailable(this)){
            loadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Loading");
            loadingDialog.show();
            loadingDialog.setCancelable(false);

            for (int i = 0; i < PlantData.plantModelList.size(); i++) {
                if (PlantData.plantModelList.get(i).getPlantChineseName().equals(plantName)){
                    Glide.with(this).load(PlantData.plantModelList.get(i).getPlantImageURL()).into(plantImageView);
                    Glide.with(this).load(PlantData.plantModelList.get(i).getPlantImageURL()).into(bottomImage);
                    plantCnNameTv.setText(PlantData.plantModelList.get(i).getPlantChineseName());
                    plantEnNameTv.setText(PlantData.plantModelList.get(i).getPlantEnglishName());
                    plantPropertyTv.setText(PlantData.plantModelList.get(i).getPlantProperty());
                    plantDescriptionTv.setText(PlantData.plantModelList.get(i).getPlantDescription());
                    plantDistributionTv.setText(PlantData.plantModelList.get(i).getPlantDistribution());
                    break;
                }
            }

            loadingDialog.dismissWithAnimation();

            ApiServiceExecutor.getInstance().getCollectionList(UserData.username, new HttpCallBack() {
                @Override
                public void onSuccess(Object response) {
                    if (response != null){
                        CollectionBackResult result = (CollectionBackResult) response;
                        if (result.getCode() == 200){
                            List<String> plantNames = result.getData().getPlantNames();
                            if (plantNames != null){
                                isHave = isContains(plantName,plantNames);
                            }
                            if (isHave){
                                imgHeart.setImageResource(R.drawable.solid_heart);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Throwable e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            });
        }else {
            // 没有网络
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("糟糕...")
                    .setContentText("网络无连接！")
                    .setCancelText("取消")
                    .setConfirmText("设置网络")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();
                            startActivity(new Intent(Settings.ACTION_SETTINGS));
                        }
                    })
                    .show();
        }

    }

    private boolean isContains(String plantName,List<String> plantNameList){
        for (int i = 0; i < plantNameList.size(); i++) {
            if (plantName.equals(plantNameList.get(i))){
                return true;
            }
        }
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plant_details;
    }


}
