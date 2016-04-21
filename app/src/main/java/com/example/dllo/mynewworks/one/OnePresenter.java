package com.example.dllo.mynewworks.one;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.mynewworks.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/4/19.
 */
public class OnePresenter {
    private OneView oneView;
    private ArrayList<String> datas;
    private ArrayList<View> views;
    private PicLoopAdapter adapter;
    private OneViewPager viewPager;


    public OnePresenter(OneView oneView) {
        this.oneView = oneView;

        views = new ArrayList<>();
        datas = new ArrayList<>();
        datas.add("http://e.hiphotos.baidu.com/image/pic/item/4d086e061d950a7b3ee598cd08d162d9f3d3c9e3.jpg");
        datas.add("http://a.hiphotos.baidu.com/image/pic/item/203fb80e7bec54e7ae8bcc2abc389b504fc26a9b.jpg");
        datas.add("http://e.hiphotos.baidu.com/image/pic/item/83025aafa40f4bfb27ecbf05014f78f0f73618a6.jpg");
        datas.add("http://a.hiphotos.baidu.com/image/pic/item/08f790529822720e5cc83afe79cb0a46f21fabb4.jpg");
        datas.add("http://e.hiphotos.baidu.com/image/pic/item/29381f30e924b8995d7368d66a061d950b7bf695.jpg");
        setViewDatas(datas);
    }

    private void setViewDatas(ArrayList<String> datas) {
        for (int i = 0; i < datas.size(); i++) {
            View view = LayoutInflater.from((Context) oneView).inflate(R.layout.item_one, null);
            ImageView iv = (ImageView) view.findViewById(R.id.one_item_img);
            Picasso.with((Context) oneView).load(datas.get(i)).into(iv);
            views.add(view);
        }
        adapter = new PicLoopAdapter((Context) oneView, views);
        oneView.getPagerAdapter(adapter);
    }

}
