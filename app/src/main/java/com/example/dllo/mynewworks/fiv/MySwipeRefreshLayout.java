package com.example.dllo.mynewworks.fiv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created by dllo on 16/4/26.
 */
public class MySwipeRefreshLayout extends LinearLayout{
    private int mTouchSlop;
    public MySwipeRefreshLayout(Context context) {
        super(context,null);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件
         */
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
}
