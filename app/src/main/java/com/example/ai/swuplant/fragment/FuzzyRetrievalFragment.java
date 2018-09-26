package com.example.ai.swuplant.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.activity.BambooActivity;
import com.example.ai.swuplant.activity.FernActivity;
import com.example.ai.swuplant.activity.FuzzySearchActivity;
import com.example.ai.swuplant.activity.GymnospermActivity;
import com.example.ai.swuplant.base.BaseFragment;
import com.example.ai.swuplant.utils.IntentUtils;

public class FuzzyRetrievalFragment extends BaseFragment {

    // 被子植物
    private TextView mAngiosperm;
    // 裸子植物
    private TextView mGymnosperm;
    // 蕨类植物
    private TextView mFern;

    private TextView mBamboo;


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
        mBamboo = view.findViewById(R.id.bamboo);
    }

    @Override
    protected void initEvent() {

        mAngiosperm.setOnClickListener(v->{
            IntentUtils.showActivity(getActivity(), FuzzySearchActivity.class);

        });

        mGymnosperm.setOnClickListener(v->{
            IntentUtils.showActivity(getActivity(), GymnospermActivity.class);

        });

        mFern.setOnClickListener(v->{
            IntentUtils.showActivity(getActivity(), FernActivity.class);

        });

        mBamboo.setOnClickListener(v->{
            IntentUtils.showActivity(getActivity(), BambooActivity.class);
        });

    }



}
