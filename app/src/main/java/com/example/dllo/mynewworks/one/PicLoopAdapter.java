package com.example.dllo.mynewworks.one;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

/**
 * Created by dllo on 16/4/19.
 */
public class PicLoopAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<View> datas;

    public PicLoopAdapter(Context context, ArrayList<View> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getMyCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int cur = position % datas.size();
        Log.e("cur" + cur, "position=" + position);

        View view = datas.get(cur);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "hahaha", Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
