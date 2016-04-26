package com.example.dllo.mynewworks.myfor;

import android.content.Context;

import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/4/22.
 */
public class MyViewPager extends ViewPager{

    private ArrayList<String> title;//背景图片的集合
    private ImageView back_iv;//需要修改的图片

    public MyViewPager(Context context) {
        super(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void setMyImageLoader(int position){
        Picasso.with(getContext()).load(title.get(position)).into(back_iv);
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
