package com.example.dllo.mynewworks.one;

import android.view.View;
import android.widget.Button;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dllo on 16/4/19.
 */
public class OneActivity extends BaseActivity implements OneView {


    @Bind(R.id.one_viewpager)
    OneViewPager oneViewpager;
    @Bind(R.id.btn_one_start)
    Button btnOneStart;
    @Bind(R.id.btn_one_stop)
    Button btnOneStop;
   @Bind(R.id.one_indictorview)
   IndictorView indictorView;

    private OnePresenter onePresenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_one;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        setOnePresenter();
    }


    @OnClick({R.id.btn_one_start, R.id.btn_one_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one_start:
                oneViewpager.startLoopView();
                break;
            case R.id.btn_one_stop:
                oneViewpager.stopLoop();
                break;
        }
    }

    @Override
    protected void onResume() {
        oneViewpager.startLoopView();
        super.onResume();
    }

    @Override
    protected void onStop() {
        oneViewpager.stopLoop();
        super.onStop();
    }

    public void setOnePresenter() {
        onePresenter = new OnePresenter(this);
    }


    @Override
    public void getPagerAdapter(PicLoopAdapter adapter) {
        oneViewpager.setAdapter(adapter);
        oneViewpager.setCurrentItem(50000);
        indictorView.setViewPager(oneViewpager);
    }
}
