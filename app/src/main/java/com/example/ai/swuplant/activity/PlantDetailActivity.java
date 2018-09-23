package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.utils.Constant;
import static com.example.ai.swuplant.activity.SplashActivity.plantModelList;

public class PlantDetailActivity extends BaseActivity {

    private ImageView plantImageView;
    private TextView plantCnNameTv;
    private TextView plantEnNameTv;
    private TextView plantPropertyTv;
    private TextView plantDescriptionTv;
    private TextView plantDistributionTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initData();


    }

    @Override
    protected void initData() {

        Bundle bundle = getIntent().getExtras();
        String plantName = null;

        if (bundle != null){
            plantName = bundle.getString(Constant.PLANT_NAME);
        }

        for (int i = 0; i < plantModelList.size(); i++) {
           if (plantModelList.get(i).getPlantCNName().equals(plantName)){
               plantImageView.setImageResource(plantModelList.get(i).getPlantImageId());
               plantCnNameTv.setText(plantModelList.get(i).getPlantCNName());
               plantEnNameTv.setText(plantModelList.get(i).getPlantEnName());
               plantPropertyTv.setText(plantModelList.get(i).getPlantProperty());
               plantDescriptionTv.setText(plantModelList.get(i).getPlantDescription());
               plantDistributionTv.setText(plantModelList.get(i).getPlantDistribution());
               break;
           }
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plant_details;
    }

    @Override
    protected void initView() {

        plantImageView = findViewById(R.id.plant_detail_image);
        plantCnNameTv = findViewById(R.id.plant_detail_CNname);
        plantEnNameTv = findViewById(R.id.plant_detail_ENname);
        plantPropertyTv = findViewById(R.id.plant_detail_property);
        plantDescriptionTv = findViewById(R.id.plant_detail_description);
        plantDistributionTv = findViewById(R.id.plant_detail_distribution);

    }


}
