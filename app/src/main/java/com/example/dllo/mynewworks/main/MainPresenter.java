package com.example.dllo.mynewworks.main;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by dllo on 16/4/19.
 */
public class MainPresenter {
    private MainView mainView;
    private MainAdapter adapter;

    public MainPresenter(MainView mainView) {

        this.mainView = mainView;
    }

    public void setAdapter(Context context) {
        adapter = new MainAdapter(context);
        ArrayList<String> name = new ArrayList<>();
        name.add("one");
        name.add("two");
        name.add("thr");
        name.add("for");
        adapter.addInfo(name);
        mainView.getRecycleAdapter(adapter);
    }

}
