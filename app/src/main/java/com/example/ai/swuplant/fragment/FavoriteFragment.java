package com.example.ai.swuplant.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseFragment;

public class FavoriteFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.
                inflate(R.layout.fragment_favorite,
                        container, false);
        return view;
    }
}
