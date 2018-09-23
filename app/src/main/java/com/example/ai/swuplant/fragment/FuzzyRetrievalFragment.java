package com.example.ai.swuplant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.activity.FuzzySearchActivity;
import com.example.ai.swuplant.base.BaseFragment;

public class FuzzyRetrievalFragment extends BaseFragment {

    // 被子植物
    private TextView mAngiosperm;
    // 裸子植物
    private TextView mGymnosperm;
    // 蕨类植物
    private TextView mFern;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.
                inflate(R.layout.fragment_fuzzy_retrieval,
                container, false);

        initView(view);

        initEvent();

        return view;
    }


    @Override
    protected void initView(View view) {

        mAngiosperm = view.findViewById(R.id.angiosperm);
        mGymnosperm = view.findViewById(R.id.gymnosperm);
        mFern = view.findViewById(R.id.fern);
    }

    @Override
    protected void initEvent() {

        mAngiosperm.setOnClickListener(v->{

            showAngiospermActivity();

        });

        mGymnosperm.setOnClickListener(v->{

            showAngiospermActivity();

        });

        mFern.setOnClickListener(v->{

            showAngiospermActivity();

        });
    }

    private void showAngiospermActivity() {
        Intent intent = new Intent(getActivity(), FuzzySearchActivity.class);
        getActivity().startActivity(intent);
    }




}
