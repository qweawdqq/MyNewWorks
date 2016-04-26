package com.example.dllo.mynewworks.myfor;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by dllo on 16/4/22.
 */
public class ViewPagerHelperLayout extends LinearLayout {
    private ViewDragHelper viewDragHelper;
    private MyViewPager viewpager;
    private Point mAutoBackOriginPos = new Point();
    private int position;    //当前页面在viewpager的位置
    private int mTop;    //滑动的距离
    private int count;   //页面的总数
    private final int moved = 350;//判断需要的移动距离

    public ViewPagerHelperLayout(Context context) {
        super(context, null);
    }

    public ViewPagerHelperLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        viewDragHelper = ViewDragHelper.create(this, 30f, new DragHelperCallBack());

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewpager = (MyViewPager) getChildAt(0);
    }

    class DragHelperCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == viewpager;
        }



        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
           mTop = top;
            return top;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            setItemPosition(yvel);
            viewDragHelper.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
            invalidate();
        }
    }

    private void setItemPosition(float yv) {
        position = viewpager.getCurrentItem();

        if (mTop < -moved || yv < -1200) {
            int down = position + 1;
            count = viewpager.getAdapter().getCount();
            viewpager.setCurrentItem(down, false);
            viewpager.setMyImageLoader(down <= count - 1 ? down : position);
        } else if (mTop > moved || yv > 1200) {
            int up = position - 1;
            viewpager.setCurrentItem(up, false);
            viewpager.setMyImageLoader(up >= 0 ? up : 0);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = viewpager.getLeft();
        mAutoBackOriginPos.y = viewpager.getTop();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
