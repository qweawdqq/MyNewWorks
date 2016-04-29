package com.example.dllo.mynewworks.fiv;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dllo on 16/4/26.
 */
public class FivActivity extends BaseActivity {


    @Bind(R.id.fiv_recycleview)
    RecyclerView fivRecycleview;
    @Bind(R.id.fiv_layout)
    SuperSwipeRefreshLayout fivLayout;


    private FivAdapter adapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_fiv;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        setFivRecycleview();
    }

    private void setFivRecycleview() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        fivRecycleview.setLayoutManager(manager);
        adapter = new FivAdapter(this);
        fivRecycleview.setAdapter(adapter);
        fivRecycleview.addItemDecoration(new DividerGridItemDecoration(this));
    }



}
