package com.example.ai.swuplant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


    private List<String> plantNameList = new ArrayList<>();

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
        View view=inflater.
                inflate(R.layout.fragment_favorite,
                        container, false);

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
                        plantNameList = result.getPlantNames();
                        for (int i = 0; i < PlantData.plantModelList.size(); i++) {
                            if (plantNameList.get(i).equals(PlantData.plantModelList.get(i).getPlantChineseName())){

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();

        initEvent();

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
                for (int i = 0; i < plantDatas.size(); i++) {
                    plantDatas.get(i).setCheck(true);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        noneSelectTV.setOnClickListener(v->{
            if (mAdapter.flage) {
                for (int i = 0; i < plantDatas.size(); i++) {
                    plantDatas.get(i).setCheck(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        deleteTV.setOnClickListener(v->{
            if (mAdapter.flage){
                for (int i = 0; i < plantDatas.size(); i++) {
                    if (plantDatas.get(i).getCheck()){

                    }
                }

                Log.d("TAG", "initEvent: "+plantDatas.size());
                mAdapter.notifyDataSetChanged();
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
    }

}
