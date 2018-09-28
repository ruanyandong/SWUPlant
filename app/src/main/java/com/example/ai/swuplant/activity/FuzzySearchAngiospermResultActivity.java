package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.adapter.AngiospermSearchListAdapter;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import java.util.ArrayList;
import java.util.List;
import static com.example.ai.swuplant.base.BaseApplication.plantModelList;

public class FuzzySearchAngiospermResultActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private AngiospermSearchListAdapter mAdapter;
    private List<PlantModel> plantModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            List<String> plantFeatureList = bundle.getStringArrayList(Constant.PLANT_FEATURE);
            String habitStr = plantFeatureList.get(0);
            String stemStr = plantFeatureList.get(1);
            String thornStr= plantFeatureList.get(2);
            String milkStr = plantFeatureList.get(3);
            String stipuleStr = plantFeatureList.get(4);
            String leafAppearanceStr = plantFeatureList.get(5);
            String leafBearingFormStr = plantFeatureList.get(6);
            String petioleStr = plantFeatureList.get(7);
            String veinStr = plantFeatureList.get(8);
            String leafMarginStr = plantFeatureList.get(9);
            String flowerStr = plantFeatureList.get(10);
            String fruitStr = plantFeatureList.get(11);

            for (int i = 0; i < plantModelList.size(); i++) {
               String description = plantModelList.get(i).getPlantDescription();

                if ((description.contains(habitStr) &&
                        description.contains(stemStr) &&
                        description.contains(thornStr) &&
                        description.contains(milkStr)) ||

                        (description.contains(stipuleStr) &&
                        description.contains(leafAppearanceStr) &&
                        description.contains(leafBearingFormStr) &&
                        description.contains(petioleStr)) ||

                        (description.contains(veinStr) &&
                        description.contains(leafMarginStr) &&
                        description.contains(flowerStr) &&
                        description.contains(fruitStr))){

                    plantModels.add(plantModelList.get(i));

                }
            }
        }

    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.angiospermList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AngiospermSearchListAdapter(this,plantModels);
        mAdapter.setOnItemClickListener(new AngiospermSearchListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String plantName) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.PLANT_NAME,plantName);
                IntentUtils.showActivity(FuzzySearchAngiospermResultActivity.this, PlantDetailActivity.class,bundle);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fuzzy_search_angiosperm_result;
    }


}
