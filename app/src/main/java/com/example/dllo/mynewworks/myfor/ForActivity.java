package com.example.dllo.mynewworks.myfor;

import android.os.Bundle;
import android.preference.SwitchPreference;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.base.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dllo on 16/4/19.
 */
public class ForActivity extends BaseActivity {
    @Bind(R.id.for_img_bg)
    ImageView forImgBg;
    @Bind(R.id.for_viewpager)
    MyViewPager forViewpager;

    private ArrayList<String> datas;
    private VerPagerAdapter adapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_for;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        setForViewpager();
    }

    private void setForViewpager() {


        datas = new ArrayList<>();
        datas.add("http://e.hiphotos.baidu.com/image/pic/item/4d086e061d950a7b3ee598cd08d162d9f3d3c9e3.jpg");
        datas.add("http://a.hiphotos.baidu.com/image/pic/item/203fb80e7bec54e7ae8bcc2abc389b504fc26a9b.jpg");
        datas.add("http://e.hiphotos.baidu.com/image/pic/item/83025aafa40f4bfb27ecbf05014f78f0f73618a6.jpg");
        datas.add("http://a.hiphotos.baidu.com/image/pic/item/08f790529822720e5cc83afe79cb0a46f21fabb4.jpg");
        datas.add("http://e.hiphotos.baidu.com/image/pic/item/29381f30e924b8995d7368d66a061d950b7bf695.jpg");
        ArrayList<View> lists = new ArrayList<>();
        for (int i = 1; i < datas.size() + 1; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_for, null);
            TextView tv = (TextView) view.findViewById(R.id.for_item_tv);
            tv.setText(String.valueOf(i));
            lists.add(view);
        }
        adapter = new VerPagerAdapter(lists);
        forViewpager.setMyBackGound(forImgBg, datas);//设置背景图片
        forViewpager.setAdapter(adapter);
        //必须下这个 要不 初始化的时候不显示 背景图片第1张
        ImageLoader.getInstance().displayImage(datas.get(0),forImgBg);
    }
}
