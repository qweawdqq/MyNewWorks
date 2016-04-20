package com.example.dllo.mynewworks.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by dllo on 16/4/19.
 */
public class BaseAppLication extends Application {
    private static Context context;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        context = this;
    }
}
