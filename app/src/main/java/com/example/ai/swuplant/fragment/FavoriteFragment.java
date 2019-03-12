package com.example.ai.swuplant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.activity.PlantDetailActivity;
import com.example.ai.swuplant.adapter.MyFavoriteListAdapter;
import com.example.ai.swuplant.base.BaseFragment;
import com.example.ai.swuplant.data.PlantData;
import com.example.ai.swuplant.data.UserData;
import com.example.ai.swuplant.entity.BatchManageModel;
import com.example.ai.swuplant.net.bean.CollectionBackResult;
import com.example.ai.swuplant.net.netframe.ApiServiceExecutor;
import com.example.ai.swuplant.net.netframe.HttpCallBack;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private MyFavoriteListAdapter mAdapter;
    private List<BatchManageModel> plantDatas = new ArrayList<>();

    private TextView batchManageTV;
    private LinearLayout manageLayout;
    private TextView allSelectTV;
    private TextView noneSelectTV;
    private TextView deleteTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favorite, container, false);
        initView(view);
        return view;
    }

    private void getCollection(){
        ApiServiceExecutor.getInstance().getCollectionList(UserData.username, new HttpCallBack() {
            @Override
            public void onSuccess(Object response) {
                if (response != null){
                    CollectionBackResult result = (CollectionBackResult) response;
                    if (result.getCode() == 200){
                        plantDatas.clear();
                        List<String> plantNames= result.getData().getPlantNames();
                        for (int i = 0; i < PlantData.plantModelList.size(); i++) {
                            for (int j = 0; j < plantNames.size(); j++) {
                                if (plantNames.get(j).equals(PlantData.plantModelList.get(i).getPlantChineseName())){
                                    plantDatas.add(new BatchManageModel(PlantData.plantModelList.get(i)));
                                }
                            }

                        }
                        mAdapter.notifyDataSetChanged();
                    }
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
    public void onResume() {
        super.onResume();
        getCollection();
        initEvent();

    }

    private void resetColor(TextView viewSelect,TextView _normal,TextView normal){
        viewSelect.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        _normal.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        normal.setTextColor(getResources().getColor(android.R.color.holo_green_light));
    }
    @Override
    protected void initEvent() {

        batchManageTV.setOnClickListener(v->{
            mAdapter.flage = !mAdapter.flage;
            if (mAdapter.flage) {
                manageLayout.setVisibility(View.VISIBLE);
                batchManageTV.setText(getResources().getString(R.string.cancel_manage));
            } else {
                manageLayout.setVisibility(View.GONE);
                batchManageTV.setText(getResources().getString(R.string.batch_manage));
            }
            mAdapter.notifyDataSetChanged();
        });

        allSelectTV.setOnClickListener(v->{
            if (mAdapter.flage) {
                resetColor(allSelectTV,noneSelectTV,deleteTV);
                for (int i = 0; i < plantDatas.size(); i++) {
                    plantDatas.get(i).setCheck(true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        noneSelectTV.setOnClickListener(v->{
            if (mAdapter.flage) {
                resetColor(noneSelectTV,allSelectTV,deleteTV);
                for (int i = 0; i < plantDatas.size(); i++) {
                    plantDatas.get(i).setCheck(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        deleteTV.setOnClickListener(v->{
            StringBuilder stringBuilder = new StringBuilder();
            if (mAdapter.flage){
                resetColor(deleteTV,allSelectTV,noneSelectTV);
                for (int i = 0; i < plantDatas.size(); i++) {
                    if (plantDatas.get(i).getCheck()){
                        stringBuilder.append(plantDatas.get(i).getPlantModel().getPlantChineseName()+",");
                    }
                }
                String plantNames = stringBuilder.toString();

                ApiServiceExecutor.getInstance().collectionPlant(false, UserData.username, plantNames, new HttpCallBack() {
                    @Override
                    public void onSuccess(Object response) {
                        if (response != null){
                            CollectionBackResult result = (CollectionBackResult) response;
                            if (result.getCode() == 200){
                                ApiServiceExecutor.getInstance().getCollectionList(UserData.username, new HttpCallBack() {
                                    @Override
                                    public void onSuccess(Object response) {
                                        if (response != null){
                                            CollectionBackResult result = (CollectionBackResult) response;
                                            if (result.getCode() == 200){
                                                plantDatas.clear();
                                                List<String> plantNames= result.getData().getPlantNames();
                                                for (int i = 0; i < PlantData.plantModelList.size(); i++) {
                                                    for (int j = 0; j < plantNames.size(); j++) {
                                                        if (plantNames.get(j).equals(PlantData.plantModelList.get(i).getPlantChineseName())){
                                                            plantDatas.add(new BatchManageModel(PlantData.plantModelList.get(i)));
                                                        }
                                                    }

                                                }
                                                mAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable e) {
                                    }
                                });
                            }
                        }
                    }
                    @Override
                    public void onFailure(Throwable e) {

                    }
                });
            }
        });
    }

    @Override
    protected void initView(View view) {

        recyclerView = view.findViewById(R.id.myFavoriteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mAdapter = new MyFavoriteListAdapter(getActivity(),plantDatas);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyFavoriteListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String plantName) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.PLANT_NAME,plantName);
                IntentUtils.showActivity(getActivity(), PlantDetailActivity.class,bundle);
            }
        });
        batchManageTV = view.findViewById(R.id.batch_manage);
        manageLayout = view.findViewById(R.id.manage_bar);
        allSelectTV = view.findViewById(R.id.all_select);
        noneSelectTV = view.findViewById(R.id.none_select);
        deleteTV = view.findViewById(R.id.delete);

        getCollection();
    }

}
