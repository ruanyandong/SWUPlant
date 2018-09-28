package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.adapter.PointInfoListAdapter;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.entity.PointInfo;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import java.util.ArrayList;
import java.util.List;
import static com.example.ai.swuplant.base.BaseApplication.plantModelList;

public class PointInfoActivity extends BaseActivity {

    private RecyclerView recyclerView ;
    private PointInfoListAdapter mAdapter;

    private List<PlantModel> plantModels = new ArrayList<>();

    private PointInfo pointInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            pointInfo = (PointInfo)bundle.getSerializable(Constant.PLANT_NAME);
            List<String> plantNameList = pointInfo.getPlantNameList();
            for (int i = 0; i < plantNameList.size(); i++) {

                for (int j = 0; j < plantModelList.size(); j++) {
                    if (plantNameList.get(i).equals(plantModelList.get(j).getPlantCNName())){
                        plantModels.add(plantModelList.get(j));
                        break;
                    }
                }

            }
        }
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.pointInfoPlantList);
        mAdapter = new PointInfoListAdapter(this,plantModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new PointInfoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String plantName) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.PLANT_NAME,plantName);
                IntentUtils.showActivity(PointInfoActivity.this, PlantDetailActivity.class,bundle);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pointinfo;
    }


}
