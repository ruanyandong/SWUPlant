package com.example.ai.swuplant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;
import com.example.ai.swuplant.utils.Constant;
import com.example.ai.swuplant.utils.IntentUtils;
import java.util.ArrayList;

public class FuzzySearchActivity extends BaseActivity {

    private Spinner habitSpinner;// 习性
    private Spinner stemSpinner;// 茎
    private Spinner thornSpinner;// 刺
    private Spinner milkSpinner;// 乳汁
    private Spinner stipuleSpinner;// 托叶
    private Spinner leafAppearanceSpinner;//叶外观
    private Spinner leafBearingFormSpinner;// 叶着生形式
    private Spinner petioleSpinner;//叶柄
    private Spinner veinSpinner;//叶脉
    private Spinner leafMarginSpinner;//叶缘
    private Spinner flowerSpinner;//花序
    private Spinner fruitSpiiner;//果实
    private Button okBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
    }

    @Override
    protected void initView() {
        habitSpinner = findViewById(R.id.habit); //习性
        stemSpinner = findViewById(R.id.stem);// 茎
        thornSpinner = findViewById(R.id.thorn);// 刺
        milkSpinner = findViewById(R.id.milk);// 乳汁
        stipuleSpinner = findViewById(R.id.stipule);// 托叶
        leafAppearanceSpinner = findViewById(R.id.leaf_appearance);//叶外观
        leafBearingFormSpinner = findViewById(R.id.leaf_bearing_form);// 叶着生形式
        petioleSpinner = findViewById(R.id.petiole);//叶柄
        veinSpinner = findViewById(R.id.vein);//叶脉
        leafMarginSpinner = findViewById(R.id.leaf_margin);//叶缘
        flowerSpinner = findViewById(R.id.flower);//花序
        fruitSpiiner = findViewById(R.id.fruit);//果实
        okBtn = findViewById(R.id.ok);

    }

    @Override
    protected void initEvent() {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String habitStr = (String)habitSpinner.getSelectedItem();
                String stemStr = (String)stemSpinner.getSelectedItem();
                String thornStr = (String)thornSpinner.getSelectedItem();
                String milkStr = (String)milkSpinner.getSelectedItem();
                String stipuleStr = (String)stipuleSpinner.getSelectedItem();
                String leafAppearanceStr = (String)leafAppearanceSpinner.getSelectedItem();
                String leafBearingFormStr = (String)leafBearingFormSpinner.getSelectedItem();
                String petioleStr = (String)petioleSpinner.getSelectedItem();
                String veinStr = (String)veinSpinner.getSelectedItem();
                String leafMarginStr = (String)leafMarginSpinner.getSelectedItem();
                String flowerStr = (String)flowerSpinner.getSelectedItem();
                String fruitStr = (String)fruitSpiiner.getSelectedItem();

                ArrayList<String> list = new ArrayList<>();

                list.add(habitStr);
                list.add(stemStr);
                list.add(thornStr);
                list.add(milkStr);
                list.add(stipuleStr);
                list.add(leafAppearanceStr);
                list.add(leafBearingFormStr);
                list.add(petioleStr);
                list.add(veinStr);
                list.add(leafMarginStr);
                list.add(flowerStr);
                list.add(fruitStr);

                Bundle bundle = new Bundle();
                bundle.putStringArrayList(Constant.PLANT_FEATURE,list);
                IntentUtils.showActivity(FuzzySearchActivity.this,FuzzySearchAngiospermResultActivity.class,bundle);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fuzzy_search;
    }

}
