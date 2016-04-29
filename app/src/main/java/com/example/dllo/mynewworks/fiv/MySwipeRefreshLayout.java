package com.example.dllo.mynewworks.fiv;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * Created by dllo on 16/4/26.
 */
public class MySwipeRefreshLayout extends LinearLayout {

    private RecyclerView recyclerView;
    int down_y;
    int move_y;
    int move_count;
    int move_myInt;
    int is_down;
    int is_move;


    public MySwipeRefreshLayout(Context context) {
        super(context, null);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setRecyclerViewTouch();

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                is_down = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                is_move = (int) ev.getY();
                break;
        }
        int yesOrNo = is_move - is_down;
        if (Math.abs(yesOrNo) < 10) {
            return false;
        } else if (isChildScrollToTop() && yesOrNo > 0) {
            return true;
        } else if (isChildScrollToBottom() && yesOrNo < 0) {
            Log.e("拦截了吗", "拦截");
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_y = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                move_y = (int) event.getY();
                move_count = move_y - down_y;
                if (move_count > 0 && isChildScrollToTop()) {
                    move_myInt = (int) (-move_count * 0.4);
                    this.scrollTo(0, move_myInt);
                }
                if (move_count < 0 && isChildScrollToBottom()) {
                    move_myInt = (int) (move_count * 0.4);
                    this.scrollTo(0, move_myInt);
                }
                break;
            case MotionEvent.ACTION_UP:

                animatorFooterToBottom(this, move_myInt, 0);

                break;
        }
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView = (RecyclerView) getChildAt(0);
    }


    private void animatorFooterToBottom(final LinearLayout layout, int start, final int end) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(400);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                layout.scrollTo(0, value);

            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                move_myInt = 0;
                valueAnimator.cancel();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();

    }

    public boolean isChildScrollToTop() {
        return !ViewCompat.canScrollVertically(recyclerView, -1);
    }

    /**
     * 是否滑动到底部
     *
     * @return
     */
    public boolean isChildScrollToBottom() {
        Log.e("zheli", "zheli");
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        int count = recyclerView.getAdapter().getItemCount();
        Log.e("getItem", "==" + count);
        if (manager instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) manager;
            Log.e("???" + layoutManager.findLastVisibleItemPosition(), "没有走这里吧");
            if (layoutManager.findLastVisibleItemPosition() == count - 1) {

                return true;
            } else if (manager instanceof StaggeredGridLayoutManager) {
                Log.e("===", "stagger");
                StaggeredGridLayoutManager staggeredGridLayoutManager =
                        (StaggeredGridLayoutManager) manager;
                int[] lastItems = new int[2];
                staggeredGridLayoutManager
                        .findLastCompletelyVisibleItemPositions(lastItems);
                int lastItem = Math.max(lastItems[0], lastItems[1]);
                if (lastItem == count - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
