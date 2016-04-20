package com.example.dllo.mynewworks.untils;

import android.content.Context;
import android.content.Intent;

import com.example.dllo.mynewworks.myfor.ForActivity;
import com.example.dllo.mynewworks.one.OneActivity;
import com.example.dllo.mynewworks.thr.ThrActivity;
import com.example.dllo.mynewworks.two.TwoActivity;

/**
 * Created by dllo on 16/4/19.
 */
public class ToNextUntil {

    public static void toNextActivity(Context context,String key){
        switch (key){
            case "one":
                toActivity(context, OneActivity.class);
                break;
            case "two":
                toActivity(context, TwoActivity.class);
                break;
            case "thr":
                toActivity(context, ThrActivity.class);
                break;
            case "for":
                toActivity(context, ForActivity.class);
                break;

        }
    }

    private static void toActivity(Context context,Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(context,cls);
        context.startActivity(intent);
    }

}
