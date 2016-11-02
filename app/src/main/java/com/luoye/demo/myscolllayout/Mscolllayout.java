package com.luoye.demo.myscolllayout;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Luoye on 2016/10/31.
 */

public class Mscolllayout extends HorizontalScrollView {
    private LinearLayout layout,scoll, content;
    private int width;
    private int padding;
    private boolean first = true;
    private boolean isFirst = true;
    private int scollwidth;

    public Mscolllayout(Context context) {
        this(context,null);
        Log.i("TAG", "gouzao1");
    }

    public Mscolllayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.i("TAG", "gouzao2");
    }

    public Mscolllayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("TAG", "gouzao3");
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Mscolllayout);
        padding = typedArray.getDimensionPixelSize(R.styleable.Mscolllayout_padding, 10);
        Log.i("TAG", padding+"");
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        width = outMetrics.widthPixels;
        Log.i("TAG", "width:"+width);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (first) {
            layout = (LinearLayout) getChildAt(0);
            scoll = (LinearLayout) layout.getChildAt(0);
            content = (LinearLayout) layout.getChildAt(1);

            scollwidth = width-padding;
            scoll.getLayoutParams().width = width-padding;
            content.getLayoutParams().width = width;
            first = false;
            Log.i("TAG", "onmeasure");
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (isFirst) {
            this.scrollTo(scollwidth, 0);
            Log.i("TAG", "onlayout");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_UP:
                // 隐藏在左边的宽度
                int scrollX = getScrollX();
                if (scrollX >= scollwidth / 2)
                {
                    this.smoothScrollTo(scollwidth, 0);
//                    isOpen = false;
                } else
                {
                    this.smoothScrollTo(0, 0);
//                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
}
