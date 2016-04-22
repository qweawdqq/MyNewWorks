package com.example.dllo.mynewworks.myfor;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.mynewworks.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

/**
 * Created by dllo on 16/4/22.
 */
public class MyViewPager extends ViewPager{

    private ArrayList<String> title;//背景图片的集合
    private float down_y, move_y, set_y;//按下时Y的坐标,滑动时Y坐标,固定值Y得坐标
    private ImageView back_iv;//需要修改的图片
    int heigh_x;//要移动的距离
    private DisplayImageOptions options;
    public MyViewPager(Context context) {
        super(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //显示图片的配置
       options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.ic_launcher)   //加载过程中的图片
                .showImageOnFail(R.mipmap.ic_launcher) //加载失败的图片
                .cacheInMemory(true)//是否放到内存缓存中
                .cacheOnDisk(true)//是否放到硬盘缓存中
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)//图片的类型
                .build();//创建
    }

    public void setMyImageLoader(int position){
        ImageLoader.getInstance().displayImage(title.get(position), back_iv, options);
    }

    /**
     * 对外获得需要修改的图片窗口   以及图片资源集合
     *
     * @param iv
     * @param title
     */
    public void setMyBackGound(ImageView iv, ArrayList<String> title) {
        this.back_iv = iv;
        this.title = title;
    }


}
