package com.example.ai.swuplant.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.customcomponent.CustomViewPager;
import com.example.ai.swuplant.data.PlantData;
import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.entity.PointInfo;
import com.example.ai.swuplant.fragment.CampusMapFragment;
import com.example.ai.swuplant.fragment.FavoriteFragment;
import com.example.ai.swuplant.fragment.FloraFragment;
import com.example.ai.swuplant.fragment.FuzzyRetrievalFragment;
import com.example.ai.swuplant.adapter.ViewPagerAdapter;
import com.example.ai.swuplant.net.netframe.ApiServiceExecutor;
import com.example.ai.swuplant.net.netframe.HttpCallBack;
import com.example.customdialog.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{


    private CustomViewPager mViewPager;
    private TextView mCampusMap;
    private TextView mFuzzyRetrieval;
    private TextView mFlora;
    private TextView mFavorite;

    private List<Fragment> mListFragment;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        initFragment();
        initEvent();

    }

    private void initFragment() {
        mListFragment.add(new CampusMapFragment());
        mListFragment.add(new FuzzyRetrievalFragment());
        mListFragment.add(new FloraFragment());
        mListFragment.add(new FavoriteFragment());
        mViewPagerAdapter.notifyDataSetChanged();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mViewPager=findViewById(R.id.view_pager);

        mCampusMap=findViewById(R.id.campus_map);
        mFuzzyRetrieval=findViewById(R.id.fuzzy_retrieval);
        mFlora=findViewById(R.id.plant_flora);
        mFavorite=findViewById(R.id.favorite);

        mListFragment=new ArrayList<>();
        mViewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),mListFragment);
        mViewPager.setAdapter(mViewPagerAdapter);
        /**
         * 默认选中第一个
         */
        mViewPager.setCurrentItem(0);
        mCampusMap.setTextColor(Color.parseColor("#007aff"));
        Drawable drawableMap = getResources().getDrawable(R.drawable.map_blue);
        mCampusMap.setCompoundDrawablesWithIntrinsicBounds(null, drawableMap,null,null);

    }


    @Override
    protected void initData() {
        if (PlantData.plantModelList.size() == 0 || PlantData.plantInfoList.size() == 0){
            PlantData.plantModelList.clear();
            PlantData.plantInfoList.clear();

            final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("数据初始化中...");
            pDialog.show();
            pDialog.setCancelable(false);

            /**
             * 初始化数据
             */
            ApiServiceExecutor.getInstance().getPlantModel(new HttpCallBack() {
                @Override
                public void onSuccess(Object response) {
                    if (response != null){
                        List<PlantModel> list = (List<PlantModel>) response;
                        PlantData.plantModelList = list;
                        ApiServiceExecutor.getInstance().getplantPointInfo(new HttpCallBack() {
                            @Override
                            public void onSuccess(Object response) {
                                if (response != null){
                                    List<PointInfo> list = (List<PointInfo>) response;
                                    PlantData.plantInfoList = list;
                                    pDialog.dismissWithAnimation();

                                }
                            }
                            @Override
                            public void onFailure(Throwable e) {
                                e.getMessage();
                                e.printStackTrace();
                            }
                        });
                    }
                }
                @Override
                public void onFailure(Throwable e) {
                    e.getMessage();
                    e.printStackTrace();
                }
            });
        }

    }

    @Override
    protected void initEvent() {
        mCampusMap.setOnClickListener(this);
        mFuzzyRetrieval.setOnClickListener(this);
        mFlora.setOnClickListener(this);
        mFavorite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        resetTextColor();
        switch (v.getId()){
            case R.id.campus_map:
                mViewPager.setCurrentItem(0,true);
                mCampusMap.setTextColor(Color.parseColor("#007aff"));
                Drawable drawableMap = getResources().getDrawable(R.drawable.map_blue);
                mCampusMap.setCompoundDrawablesWithIntrinsicBounds(null, drawableMap,null,null);
                break;
            case R.id.fuzzy_retrieval:
                mViewPager.setCurrentItem(1,true);
                mFuzzyRetrieval.setTextColor(Color.parseColor("#007aff"));
                Drawable drawableRetrieval = getResources().getDrawable(R.drawable.search_blue);
                mFuzzyRetrieval.setCompoundDrawablesWithIntrinsicBounds(null, drawableRetrieval,null,null);
                break;
            case R.id.plant_flora:
                mViewPager.setCurrentItem(2,true);
                mFlora.setTextColor(Color.parseColor("#007aff"));
                Drawable drawableFlora = getResources().getDrawable(R.drawable.plant_blue);
                mFlora.setCompoundDrawablesWithIntrinsicBounds(null,drawableFlora,null,null);
                break;
            case R.id.favorite:
                mViewPager.setCurrentItem(3,true);
                mFavorite.setTextColor(Color.parseColor("#007aff"));
                Drawable drawableFavorite = getResources().getDrawable(R.drawable.favorites_blue);
                mFavorite.setCompoundDrawablesWithIntrinsicBounds(null,drawableFavorite,null,null);
                break;
            default:
                break;
        }


    }

    private void resetTextColor(){


        mCampusMap.setTextColor(Color.parseColor("#ffffff"));
        Drawable drawableMap = getResources().getDrawable(R.drawable.map);
        mCampusMap.setCompoundDrawablesWithIntrinsicBounds(null, drawableMap,null,null);

        mFuzzyRetrieval.setTextColor(Color.parseColor("#ffffff"));
        Drawable drawableRetrieval = getResources().getDrawable(R.drawable.search);
        mFuzzyRetrieval.setCompoundDrawablesWithIntrinsicBounds(null, drawableRetrieval,null,null);

        mFlora.setTextColor(Color.parseColor("#ffffff"));
        Drawable drawableFlora = getResources().getDrawable(R.drawable.plant);
        mFlora.setCompoundDrawablesWithIntrinsicBounds(null,drawableFlora,null,null);

        mFavorite.setTextColor(Color.parseColor("#ffffff"));
        Drawable drawableFavorite = getResources().getDrawable(R.drawable.favorites);
        mFavorite.setCompoundDrawablesWithIntrinsicBounds(null,drawableFavorite,null,null);
    }

}
