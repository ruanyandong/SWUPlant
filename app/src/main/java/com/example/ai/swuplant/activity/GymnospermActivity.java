package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.adapter.GymnospermListAdapter;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.data.PlantData;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.net.netframe.ApiServiceExecutor;
import com.example.ai.swuplant.net.netframe.HttpCallBack;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import com.example.customdialog.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class GymnospermActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private GymnospermListAdapter mAdapter;
    private List<PlantModel> plantModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();

    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.angiospermList);

    }

    @Override
    protected void initData() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);

        ApiServiceExecutor.getInstance().getGymnosperm(new HttpCallBack() {
            @Override
            public void onSuccess(Object response) {
                if (response != null){
                    List<String> gymnospermList = (List<String>) response;
                    for (int i = 0; i < gymnospermList.size(); i++) {

                        for (int j = 0; j < PlantData.plantModelList.size(); j++) {
                            if (gymnospermList.get(i).equals(PlantData.plantModelList.get(j).getPlantChineseName())){
                                plantModels.add(PlantData.plantModelList.get(j));
                                break;
                            }
                        }
                    }
                    pDialog.dismissWithAnimation();

                    mAdapter = new GymnospermListAdapter(GymnospermActivity.this,plantModels);
                    recyclerView.setLayoutManager(new LinearLayoutManager(GymnospermActivity.this));
                    recyclerView.addItemDecoration(new DividerItemDecoration(GymnospermActivity.this,DividerItemDecoration.VERTICAL));
                    mAdapter.setOnItemClickListener(new GymnospermListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String plantName) {
                            Bundle bundle = new Bundle();
                            bundle.putString(Constant.PLANT_NAME,plantName);
                            IntentUtils.showActivity(GymnospermActivity.this, PlantDetailActivity.class,bundle);
                        }
                    });
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                e.getMessage();
                e.printStackTrace();
            }
        });


    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_gymnosperm;
    }

}
