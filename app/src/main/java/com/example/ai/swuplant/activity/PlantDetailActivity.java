package com.example.ai.swuplant.activity;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.database.MyFavoriteDatabaseHelper;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.ToastUtils;
import java.util.ArrayList;
import java.util.List;
import static com.example.ai.swuplant.base.BaseApplication.plantModelList;

public class PlantDetailActivity extends BaseActivity {

    private ImageView plantImageView;
    private ImageView imgHeart;
    private TextView plantCnNameTv;
    private TextView plantEnNameTv;
    private TextView plantPropertyTv;
    private TextView plantDescriptionTv;
    private TextView plantDistributionTv;

    private MyFavoriteDatabaseHelper databaseHelper;

    private List<String> plantNameList = new ArrayList<>();

    private int imageId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new MyFavoriteDatabaseHelper(getApplicationContext());

        query();

        initView();

        initData();

        initEvent();

    }


    private void query(){
        Cursor cursor = databaseHelper.getAllData();
        plantNameList.clear();
        if(cursor!=null){
            while(cursor.moveToNext()){
                String plantName=cursor.getString(cursor.getColumnIndex("plantName"));
                plantNameList.add(plantName);
            }
            cursor.close();
        }
    }

    @Override
    protected void initEvent() {

        Drawable.ConstantState hollowHeart = getResources().getDrawable(R.drawable.hollow_heart).getConstantState();
        Drawable.ConstantState solidHeart = getResources().getDrawable(R.drawable.solid_heart).getConstantState();
        imgHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
                String plantName = plantCnNameTv.getText().toString();
                boolean ishave = isContains(plantName);

                Drawable.ConstantState drawable = imgHeart.getDrawable().getConstantState();

                if (drawable.equals(hollowHeart)){
                    if (!ishave){
                        databaseHelper.insert(imageId,plantName);
                        imgHeart.setImageResource(R.drawable.solid_heart);
                        ToastUtils.showToast(getApplicationContext(),"收藏成功");
                    }else {
                        imgHeart.setImageResource(R.drawable.solid_heart);
                    }
                }else if (drawable.equals(solidHeart)){
                    if (ishave){
                        databaseHelper.deleteData(imageId);
                        imgHeart.setImageResource(R.drawable.hollow_heart);
                        ToastUtils.showToast(getApplicationContext(),"取消收藏");
                    }else {
                        imgHeart.setImageResource(R.drawable.hollow_heart);
                    }
                }

            }
        });
    }

    private boolean isContains(String plantName){
        for (int i = 0; i < plantNameList.size(); i++) {
            if (plantName.equals(plantNameList.get(i))){
                return true;
            }
        }
        return false;
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
               imageId = plantModelList.get(i).getPlantImageId();
               break;
           }
        }

        if (isContains(plantName)){
            imgHeart.setImageResource(R.drawable.solid_heart);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plant_details;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.close();
        databaseHelper = null;
    }

}
