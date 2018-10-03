package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.adapter.FernListAdapter;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import java.util.ArrayList;
import java.util.List;
import static com.example.ai.swuplant.base.BaseApplication.fernList;
import static com.example.ai.swuplant.base.BaseApplication.plantModelList;

public class FernActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private FernListAdapter mAdapter;
    private List<PlantModel> plantModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.angiospermList);
        mAdapter = new FernListAdapter(FernActivity.this,plantModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemClickListener(new FernListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String plantName) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.PLANT_NAME,plantName);
                IntentUtils.showActivity(FernActivity.this, PlantDetailActivity.class,bundle);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < fernList.size(); i++) {

            for (int j = 0; j < plantModelList.size(); j++) {
                if (fernList.get(i).equals(plantModelList.get(j).getPlantCNName())){
                    plantModels.add(plantModelList.get(j));
                    break;
                }
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fern;
    }


}
