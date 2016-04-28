package com.example.dllo.mynewworks.six;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.base.BaseActivity;
import com.example.dllo.mynewworks.untils.net.OKhttpHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dllo on 16/4/27.
 */
public class SixActivity extends BaseActivity {
    @Bind(R.id.six_tv)
    TextView sixTv;
    private String get_url = "http://img1.money.126.net/data/hs/time/today/0600600.json";
private String post_url = "http://api101.test.mirroreye.cn/index.php/story/info";

    @Override
    protected int initLayout() {
        return R.layout.activity_six;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        getDataFromNet();
//        postAnsync();
    }

    private void postAnsync() {
//        OKhttpHelper ok = new OKhttpHelper();
//        Map<String,String>map = new HashMap<String, String>();
//        map.put("token","");
//        map.put("device_type","2");
//        map.put("story_id","2");
//        ok.postKeyValueAsync(post_url, map, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("??", "??");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("okok", "okok");
//                Log.e("111111",response.body().string());
//                Log.e("111111",response.toString());
//            }
//        });
    }

    public void getDataFromNet() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                Map<String,String>map = new HashMap<String, String>();
                map.put("token","");
                map.put("device_type","2");
                map.put("story_id","2");
                String result = null;
                try {
                    result = OKhttpHelper.getInstance(SixActivity.this).postKeyValuePair(post_url, map, "six");
                    subscriber.onNext(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        sixTv.setText(s);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
