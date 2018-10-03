package com.example.ai.swuplant.fragment;

import android.database.Cursor;
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
import com.example.ai.swuplant.database.MyFavoriteDatabaseHelper;
import com.example.ai.swuplant.entity.BatchManageModel;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import java.util.ArrayList;
import java.util.List;
import static com.example.ai.swuplant.base.BaseApplication.plantModelList;

public class FavoriteFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private MyFavoriteListAdapter mAdapter;

    private List<BatchManageModel> plantDatas = new ArrayList<>();

    private MyFavoriteDatabaseHelper databaseHelper;

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
        databaseHelper = new MyFavoriteDatabaseHelper(getActivity().getApplicationContext());
        query();

        initView(view);
        return view;
    }

    private void query(){
        Cursor cursor = databaseHelper.getAllData();
        plantNameList.clear();
        if(cursor!=null){
            while(cursor.moveToNext()){
                String plantName=cursor.getString(cursor.getColumnIndex("plantName"));
                plantNameList.add(plantName);
            }
            Log.d("TAG", "query: plantNameList"+plantNameList.size());

            cursor.close();
        }

        plantDatas.clear();
        for (int i = 0; i < plantNameList.size(); i++) {

            for (int j = 0; j < plantModelList.size(); j++) {
                if (plantNameList.get(i).equals(plantModelList.get(j).getPlantCNName())){
                    plantDatas.add(new BatchManageModel(plantModelList.get(j)));
                    break;
                }
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (databaseHelper == null){
            databaseHelper = new MyFavoriteDatabaseHelper(getActivity().getApplicationContext());
        }

        query();
        mAdapter.notifyDataSetChanged();

        initEvent();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseHelper.close();
        databaseHelper = null;
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
                        databaseHelper.deleteData(plantDatas.get(i).getPlantModel().getPlantImageId());
                        //plantDatas.remove(i);//TODO:有问题
                        Log.d("TAG", "initEvent: "+i);
                    }
                }
                query();
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
