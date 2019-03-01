package com.example.ai.swuplant.customcomponent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ai.swuplant.R;
import com.example.ai.swuplant.base.BaseActivity;

public class TitleBar extends RelativeLayout implements View.OnClickListener{

    private ImageView mBack;
    private TextView mTitle;
    private  TextView mRTBtn;
    BaseActivity mContext;

    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = (BaseActivity) getContext();
    }

    /**
     * 初始化控件
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContext = (BaseActivity) getContext();
        mBack = (ImageView) findViewById(R.id.titlebar_back);
        mTitle = (TextView) findViewById(R.id.titlebar_title);
        mRTBtn = (TextView) findViewById(R.id.titlebar_rtbtn);
        mBack.setOnClickListener(this);
        mTitle.setOnClickListener(this);
        mRTBtn.setOnClickListener(this);
        mTitle.setClickable(false);
    }

    /**
     * 右边的控件是否可见
     */
    public void setRTBtnVisiable(int visiable) {
        mRTBtn.setVisibility(visiable);
    }

    /**
     * 右边控件的文版
     */
    public void setRTBtnText(String title) {
        mRTBtn.setText(title);
    }

    /**
     * 右边控件的显示颜色变化
     */
    public void setRTBtnFocusable(boolean focusable) {
        mRTBtn.setEnabled(focusable);
        if (focusable) {
            mRTBtn.setTextColor(getResources().getColor(R.color.blue_btn_bg_pressed_color));
        } else {
            mRTBtn.setTextColor(getResources().getColor(R.color.blue_btn_bg_color));
        }
    }

    /**
     * 返回右边控件
     */
    public TextView getRTBtnTextView() {
        return mRTBtn;
    }

    /**
     * 中间控件的点击事件
     */
    public void setTitleClick(boolean bool) {
        mTitle.setClickable(bool);
    }

    /**
     * 中间控件文本
     */
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    /**
     * 中间控件图标
     */
    public void setCompoundDrawables(Drawable drawable) {
        mTitle.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 中间控件图标
     */
    public void setTitleRightDrawable(Drawable drawable) {
        mTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    /**
     * 控件的点击情况，分别去调用BaseActivity的方法，
     * 基类被重写后将在子类中调用这些方法
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_back:
                mContext.finishActivity();
                break;
            case R.id.titlebar_title:
                mContext.onCenterClick();
                break;
            case R.id.titlebar_rtbtn:
                mContext.onRtBtnClick();
                break;
            default:
                break;
        }
    }


}
