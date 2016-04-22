package com.example.dllo.mynewworks.myfor;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;


/**
 *竖向viewpager
 * Created by jialiang on 16/4/5.
 *
 */
public class ItemVerticalViewpager extends ViewPager {
    private ArrayList<String> title;//背景图片的集合
    private float down_y, move_y, set_y;//按下时Y的坐标,滑动时Y坐标,固定值Y得坐标
    private ImageView back_iv;//需要修改的图片
    int heigh_x;//要移动的距离


    public ItemVerticalViewpager(Context context) {
        this(context, null);
    }

    public ItemVerticalViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(false, new DefaultTransformerX());//添加动画效果
    }

    /**
     * 转化成纵向移动
     *
     * @param event
     * @return
     */
    private MotionEvent swapTouchEvent(MotionEvent event) {
        float width = getWidth();
        float height = getHeight();

        float swappedX = (event.getY() / height) * width;
        float swappedY = (event.getX() / width) * height;

        event.setLocation(swappedX, swappedY);

        return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        swapTouchEvent(event);
        return super.onInterceptTouchEvent(swapTouchEvent(event));
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapTouchEvent(ev));
    }




    /**
     * 纵向移动要用到的翻页变化
     */
    public class DefaultTransformerX implements PageTransformer {
        @Override
        public void transformPage(View page, float position) {

            page.setTranslationX(page.getWidth() * -position);
            float yPosition = position * page.getHeight();
            page.setTranslationY(yPosition);
        }
    }

}
