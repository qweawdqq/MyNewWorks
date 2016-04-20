package com.example.dllo.mynewworks.thr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dllo on 16/4/19.
 */
public class ThrActivity extends BaseActivity implements ThrView {
    @Bind(R.id.thr_et)
    EditText thrEt;
    @Bind(R.id.thr_btn)
    Button thrBtn;
    @Bind(R.id.thr_img)
    ImageView thrImg;

    private ThrPresenter presenter;

    @Override
    protected int initLayout() {
        return R.layout.activity_thr;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        presenter = new ThrPresenter(this);

    }


    @OnClick(R.id.thr_btn)
    public void onClick() {
        if (TextUtils.isEmpty(thrEt.getText())){
            Toast.makeText(ThrActivity.this, "请输入文字", Toast.LENGTH_SHORT).show();
        }else {
                    presenter.doQRCode();
        }

    }

    @Override
    public String getEditText() {
        return thrEt.getText().toString();
    }

    @Override
    public ImageView setMyBitMap() {
        return thrImg;
    }
}
