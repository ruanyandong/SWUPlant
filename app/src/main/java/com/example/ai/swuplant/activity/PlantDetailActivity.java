package com.example.ai.swuplant.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBarBackgroundColor(this.getResources().getColor(R.color.success_stroke_color));

        initView();

        initData();

        initEvent();

    }

    @Override
    protected void initEvent() {

        Drawable.ConstantState hollowHeart = getResources().getDrawable(R.drawable.hollow_heart).getConstantState();
        Drawable.ConstantState solidHeart = getResources().getDrawable(R.drawable.solid_heart).getConstantState();
        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plantName = plantCnNameTv.getText().toString();

                Drawable.ConstantState drawable = imgHeart.getDrawable().getConstantState();

                if (drawable.equals(hollowHeart)){
                    if (!isHave){
                        ApiServiceExecutor.getInstance().collectionPlant(true, UserData.username, plantName, new HttpCallBack() {
                            @Override
                            public void onSuccess(Object response) {
                                if (response != null){
                                    CollectionBackResult result = (CollectionBackResult) response;
                                    if (result.getCode() == 200){
                                        imgHeart.setImageResource(R.drawable.solid_heart);
                                        ToastUtils.showToast(getApplicationContext(),"收藏成功");
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Throwable e) {
                                e.getMessage();
                            }
                        });
                    }else {
                        imgHeart.setImageResource(R.drawable.solid_heart);
                    }
                }else if (drawable.equals(solidHeart)){
                    if (isHave){
                        ApiServiceExecutor.getInstance().collectionPlant(true, UserData.username, plantName, new HttpCallBack() {
                            @Override
                            public void onSuccess(Object response) {
                                if (response != null){
                                    CollectionBackResult result = (CollectionBackResult) response;
                                    if (result.getCode() == 200){
                                        imgHeart.setImageResource(R.drawable.hollow_heart);
                                        ToastUtils.showToast(getApplicationContext(),"取消收藏");
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Throwable e) {
                                e.getMessage();
                            }
                        });

                    }else {
                        imgHeart.setImageResource(R.drawable.hollow_heart);
                    }
                }

            }
        });
    }

    @Override
    protected void initView() {
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

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            plantName = bundle.getString(Constant.PLANT_NAME);
        }

        if (netUtil.isNetworkAvailable(this)){
            loadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("Loading");
            loadingDialog.show();
            loadingDialog.setCancelable(false);

            for (int i = 0; i < PlantData.plantModelList.size(); i++) {
                if (PlantData.plantModelList.get(i).getPlantChineseName().equals(plantName)){
                    Glide.with(this).load(PlantData.plantModelList.get(i).getPlantImageURL()).into(plantImageView);
                    plantCnNameTv.setText(PlantData.plantModelList.get(i).getPlantChineseName());
                    plantEnNameTv.setText(PlantData.plantModelList.get(i).getPlantEnglishName());
                    plantPropertyTv.setText(PlantData.plantModelList.get(i).getPlantProperty());
                    plantDescriptionTv.setText(PlantData.plantModelList.get(i).getPlantDescription());
                    plantDistributionTv.setText(PlantData.plantModelList.get(i).getPlantDistribution());
                    break;
                }
            }

            ApiServiceExecutor.getInstance().getCollectionList(UserData.username, new HttpCallBack() {
                @Override
                public void onSuccess(Object response) {
                    if (response != null){
                        CollectionBackResult result = (CollectionBackResult) response;
                        if (result.getCode() == 200){
                            List<String> plantNames = result.getPlantNames();
                            if (plantNames != null){
                                isHave = isContains(plantName,plantNames);
                            }
                            if (isHave){
                                imgHeart.setImageResource(R.drawable.solid_heart);
                            }
                            loadingDialog.dismissWithAnimation();
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
