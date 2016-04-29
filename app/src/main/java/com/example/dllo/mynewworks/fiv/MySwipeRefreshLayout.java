package com.example.dllo.mynewworks.fiv;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
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
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

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
        if (isChildScrollToTop() && yesOrNo > 0) {
            return true;
        } else if (isChildScrollToBottom() && yesOrNo < 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("ev", "==" + event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down_y = (int) event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                move_y = (int) event.getY();
                move_count = move_y - down_y;
                Log.e("count", "=" + move_count);
                if (move_count > 0 && isChildScrollToTop()) {
                    move_myInt = (int) (-move_count * 0.4);
                    this.scrollTo(0, move_myInt);
                }
                Log.e("??", "="+isChildScrollToBottom());
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


    public boolean isChildScrollToTop() {


            return !ViewCompat.canScrollVertically(recyclerView, -1);
     
    }

    /**
     * 是否滑动到底部
     *
     * @return
     */
    public boolean isChildScrollToBottom() {
        if (isChildScrollToTop()) {
            return false;
        }
        if (recyclerView instanceof RecyclerView) {

            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int count = recyclerView.getAdapter().getItemCount();
            if (layoutManager instanceof LinearLayoutManager && count > 0) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == count - 1) {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                int[] lastItems = new int[2];
                staggeredGridLayoutManager
                        .findLastCompletelyVisibleItemPositions(lastItems);
                int lastItem = Math.max(lastItems[0], lastItems[1]);
                if (lastItem == count - 1) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }




    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView = (RecyclerView) getChildAt(0);
    }


    private void animatorFooterToBottom(final LinearLayout layout, int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(400);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                layout.scrollTo(0, value);
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
    }

//    public boolean isChildScrollToTop() {
//        return !ViewCompat.canScrollVertically(recyclerView, -1);
//    }
//
//    /**
//     * 是否滑动到底部
//     *
//     * @return
//     */
//    public boolean isChildScrollToBottom() {
//
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        int count = recyclerView.getAdapter().getItemCount();
//        if (manager instanceof LinearLayoutManager && count > 0) {
//
//            LinearLayoutManager layoutManager = (LinearLayoutManager) manager;
//            Log.e(""+layoutManager.findLastCompletelyVisibleItemPosition(), "没有走这里吧");
//            if (layoutManager.findLastCompletelyVisibleItemPosition() == count - 1) {
//
//                return true;
//            } else if (manager instanceof StaggeredGridLayoutManager && count > 0) {
//                Log.e("===", "stagger");
//                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
//                int[] lastItems = new int[2];
//                staggeredGridLayoutManager
//                        .findLastCompletelyVisibleItemPositions(lastItems);
//                int lastItem = Math.max(lastItems[0], lastItems[1]);
//                if (lastItem == count - 1) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
}
