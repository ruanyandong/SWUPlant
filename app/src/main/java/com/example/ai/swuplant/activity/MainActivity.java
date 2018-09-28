package com.example.ai.swuplant.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.fragment.CampusMapFragment;
import com.example.ai.swuplant.fragment.FavoriteFragment;
import com.example.ai.swuplant.fragment.FloraFragment;
import com.example.ai.swuplant.fragment.FuzzyRetrievalFragment;
import com.example.ai.swuplant.adapter.ViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{


    private ViewPager mViewPager;
    private TextView mCampusMap;
    private TextView mFuzzyRetrieval;
    private TextView mFlora;
    private TextView mFavorite;

    private List<Fragment> mListFragment;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();

        initEvent();

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

        mListFragment.add(new CampusMapFragment());
        mListFragment.add(new FuzzyRetrievalFragment());
        mListFragment.add(new FloraFragment());
        mListFragment.add(new FavoriteFragment());
        mViewPagerAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initEvent() {
        mCampusMap.setOnClickListener(this);
        mFuzzyRetrieval.setOnClickListener(this);
        mFlora.setOnClickListener(this);
        mFavorite.setOnClickListener(this);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentPosition=mViewPager.getCurrentItem();
                resetTextColor();
                switch (currentPosition){

                    case 0:
                        mCampusMap.setTextColor(Color.parseColor("#007aff"));
                        Drawable drawableMap = getResources().getDrawable(R.drawable.map_blue);
                        mCampusMap.setCompoundDrawablesWithIntrinsicBounds(null, drawableMap,null,null);

                        break;
                    case 1:
                        mFuzzyRetrieval.setTextColor(Color.parseColor("#007aff"));
                        Drawable drawableRetrieval = getResources().getDrawable(R.drawable.search_blue);
                        mFuzzyRetrieval.setCompoundDrawablesWithIntrinsicBounds(null, drawableRetrieval,null,null);
                        break;
                    case 2:
                        mFlora.setTextColor(Color.parseColor("#007aff"));
                        Drawable drawableFlora = getResources().getDrawable(R.drawable.plant_blue);
                        mFlora.setCompoundDrawablesWithIntrinsicBounds(null,drawableFlora,null,null);
                        break;
                    case 3:
                        mFavorite.setTextColor(Color.parseColor("#007aff"));
                        Drawable drawableFavorite = getResources().getDrawable(R.drawable.favorites_blue);
                        mFavorite.setCompoundDrawablesWithIntrinsicBounds(null,drawableFavorite,null,null);
                        break;
                    default:
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
