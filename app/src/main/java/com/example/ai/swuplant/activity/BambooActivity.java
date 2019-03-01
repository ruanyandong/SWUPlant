package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.adapter.PlantListAdapter;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.data.PlantData;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.net.netframe.ApiServiceExecutor;
import com.example.ai.swuplant.net.netframe.HttpCallBack;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import com.example.customdialog.SweetAlertDialog;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

public class BambooActivity extends BaseActivity {

    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private PlantListAdapter mAdapter;
    private List<PlantModel> plantModels = new ArrayList<>();

    // 分页加载
    // RecyclerView的数据源
    private List<PlantModel> dataSource = new ArrayList<>();
    private int totalDataNumber;
    private int pageSize = 6;
    private int totalPageNumber;
    private int currentPage = 1;
    private boolean isExactDivision;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBarTitle("竹类植物");
        setTitleRTBtnVisiable(View.INVISIBLE);
        setCenterClick(false);

        initView();
        initData();

    }

    @Override
    protected void initView() {
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.plantList);
        recyclerView.setLayoutManager(new LinearLayoutManager(BambooActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(BambooActivity.this,DividerItemDecoration.VERTICAL));

        mAdapter = new PlantListAdapter(BambooActivity.this,dataSource);
        mAdapter.setOnItemClickListener(new PlantListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String plantName) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.PLANT_NAME,plantName);
                IntentUtils.showActivity(BambooActivity.this, PlantDetailActivity.class,bundle);
            }
        });
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    protected void initData() {

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);

        ApiServiceExecutor.getInstance().getBamboo(new HttpCallBack() {
            @Override
            public void onSuccess(Object response) {
                if (response != null){

                    List<String> bambooList = (List<String>) response;
                    for (int i = 0; i < bambooList.size(); i++) {

                        for (int j = 0; j < PlantData.plantModelList.size(); j++) {
                            if (bambooList.get(i).equals(PlantData.plantModelList.get(j).getPlantChineseName())){
                                plantModels.add(PlantData.plantModelList.get(j));
                                break;
                            }
                        }
                    }
                    pDialog.dismissWithAnimation();

                    // 分页参数
                    initDivPageParameters();

                    smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                            dataSource.clear();
                            mAdapter.notifyDataSetChanged();
                            currentPage = 1;
                            initDivPageParameters();
                            mAdapter.notifyDataSetChanged();
                            refreshLayout.finishRefresh();
                        }
                    });

                    smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                            loadMoreData();
                            refreshLayout.finishLoadMore();
                        }
                    });

                    smartRefreshLayout.setEnableLoadMore(true);
                    //smartRefreshLayout.autoRefresh();
                }
            }
            @Override
            public void onFailure(Throwable e) {
                e.getMessage();
                e.printStackTrace();
            }
        });
    }

    private void loadMoreData() {
        if (currentPage < totalPageNumber) {
            ++currentPage;
            if (isExactDivision) {
                for (int i = (currentPage - 1) * pageSize; i < (currentPage - 1) * pageSize + 6; i++) {
                    dataSource.add(plantModels.get(i));
                }
                Logger.d("dataSource size is" + dataSource.size());
                mAdapter.notifyDataSetChanged();
            } else {
                if (currentPage < totalPageNumber) {
                    for (int i = (currentPage - 1) * pageSize; i < (currentPage - 1) * pageSize + 6; i++) {
                        dataSource.add(plantModels.get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                    Logger.d("dataSource size is" + dataSource.size());
                } else if (currentPage == totalPageNumber) {
                    for (int i = (currentPage - 1) * pageSize; i < (currentPage - 1) * pageSize + totalDataNumber % pageSize; i++) {
                        dataSource.add(plantModels.get(i));
                    }
                    mAdapter.notifyDataSetChanged();
                    Logger.d("dataSource size is" + dataSource.size());
                }
            }
        }
    }

    private void initDivPageParameters() {
        totalDataNumber = plantModels.size();
        Logger.d("totalDataNumber size is" + totalDataNumber);
        if (totalDataNumber <= pageSize){
            totalPageNumber = 1;
            for (int i = 0; i < plantModels.size(); i++) {
                dataSource.add(plantModels.get(i));
            }
            mAdapter.notifyDataSetChanged();
            Logger.d("dataSource size is" + dataSource.size());
        }else {
            if (totalDataNumber%pageSize == 0){
                totalPageNumber = totalDataNumber/pageSize;
                isExactDivision = true;
            }else {
                totalPageNumber = totalDataNumber/pageSize+1;
                isExactDivision = false;
            }

            for (int i = 0;i<6;i++){
                dataSource.add(plantModels.get(i));
                Logger.d("dataSource size is" + dataSource.size());
            }
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_plant_list;
    }


}
