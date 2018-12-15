package com.example.ai.swuplant.login.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.example.ai.swuplant.R;

/**
 * 自定义textview 可在布局文件设置drawableLeft等大小
 */

public class DrawableTextView extends android.support.v7.widget.AppCompatTextView {

    private Context mContext;
    /**
     * resID
     */
    private int drawableLeft ;
    private int drawableRight ;
    private int drawableTop ;
    private int drawableBottom ;
    /**
     * 图标宽度
     */
    private float drawableWidth ;
    /**
     * 图标高度
     */
    private float drawableHeight ;

    public DrawableTextView(Context context) {
        this(context,null);
    }

    public DrawableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context ;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.drawableText);
            try {
                // 得到资源ID
                drawableLeft = a.getResourceId(R.styleable.drawableText_leftDrawable, 0);
                drawableRight = a.getResourceId(R.styleable.drawableText_rightDrawable, 0);
                drawableTop = a.getResourceId(R.styleable.drawableText_topDrawable, 0);
                drawableBottom = a.getResourceId(R.styleable.drawableText_bottomDrawable, 0);

                drawableWidth = a.getDimensionPixelSize(R.styleable.drawableText_drawableWidth, 30);
                drawableHeight = a.getDimensionPixelSize(R.styleable.drawableText_drawableHeight, 30);

            } finally {
                if (a != null) {
                    a.recycle();
                }
            }
        }
        init();
    }

    private void init() {
        Drawable leftDrawable = null ;
        if (drawableLeft!=0)
        {
            /**
             * setBounds(int left, int top, int right, int bottom),这四个参数指的是drawable将在被绘制在canvas的哪个矩形区域内。
             */
            leftDrawable = ContextCompat.getDrawable(mContext,drawableLeft);
            leftDrawable.setBounds(0, 0, (int)drawableWidth, (int)drawableHeight);
        }
        Drawable rightDrawable = null;
        if(drawableRight!=0){
            rightDrawable = ContextCompat.getDrawable(mContext,drawableRight);
            rightDrawable.setBounds(0, 0, (int)drawableWidth, (int)drawableHeight);
        }
        Drawable topDrawable = null;
        if (drawableTop!=0){
            topDrawable = ContextCompat.getDrawable(mContext,drawableTop);
            topDrawable.setBounds(0, 0, (int)drawableWidth, (int)drawableHeight);
        }
        Drawable bottomDrawable = null ;
        if (drawableBottom!=0){
            bottomDrawable = ContextCompat.getDrawable(mContext,drawableBottom);
            bottomDrawable.setBounds(0, 0, (int)drawableWidth, (int)drawableHeight);
        }

        this.setCompoundDrawables(leftDrawable,topDrawable,rightDrawable,bottomDrawable);

    }
}
