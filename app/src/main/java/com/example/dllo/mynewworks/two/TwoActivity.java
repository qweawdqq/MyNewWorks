package com.example.dllo.mynewworks.two;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.base.BaseActivity;
import com.jakewharton.rxbinding.view.RxView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dllo on 16/4/19.
 */
public class TwoActivity extends BaseActivity {
    @Bind(R.id.two_et)
    EditText twoEt;
    @Bind(R.id.two_btn)
    Button twoBtn;

    private TwoPresenter presenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_two;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        presenter = new TwoPresenter();
    }


    @OnClick(R.id.two_btn)
    public void onClick() {
        String input = twoEt.getText().toString();
        presenter.sendMyMessage(input);
    }


}
