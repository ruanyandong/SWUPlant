package com.example.ai.swuplant.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.activity.PlantDetailActivity;
import com.example.ai.swuplant.adapter.MyFavoriteListAdapter;
import com.example.ai.swuplant.base.BaseFragment;
import com.example.ai.swuplant.database.MyFavoriteDatabaseHelper;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import java.util.ArrayList;
import java.util.List;
import static com.example.ai.swuplant.activity.SplashActivity.plantModelList;

public class FavoriteFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private MyFavoriteListAdapter mAdapter;

    private List<PlantModel> plantDatas = new ArrayList<>();

    private MyFavoriteDatabaseHelper databaseHelper;

    private List<String> plantNameList = new ArrayList<>();

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
                    plantDatas.add(plantModelList.get(j));
                    break;
                }
            }
        }
        Log.d("TAG", "query: "+plantDatas.size());


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.
                inflate(R.layout.fragment_favorite,
                        container, false);
        databaseHelper = new MyFavoriteDatabaseHelper(getActivity().getApplicationContext());
        Log.d("TAG", "onCreateView: ");
        query();

        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (databaseHelper == null){
            databaseHelper = new MyFavoriteDatabaseHelper(getActivity().getApplicationContext());
        }
        Log.d("TAG", "onResume: ");
        query();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseHelper.close();
        databaseHelper = null;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.myFavoriteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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

    }

}
