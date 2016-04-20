package com.example.dllo.mynewworks.thr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.dllo.mynewworks.R;
import com.example.dllo.mynewworks.untils.Encoder;

import java.io.IOException;
import java.io.InputStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by dllo on 16/4/20.
 */
public class ThrPresenter {
    private ThrView thrView;


    public ThrPresenter(ThrView thrView) {
        this.thrView = thrView;
    }

    public void doQRCode() {
        final Encoder builder = new Encoder.Builder()
                .setOutputBitmapWidth(500)
                .setOutputBitmapHeight(500)
                .setOutputBitmapPadding(0)
                .build();


        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Log.e("text", thrView.getEditText().toString());

                Bitmap bm = builder.encode(thrView.getEditText(), getLogoImage((Context) thrView, R.mipmap.ic_launcher));
                subscriber.onNext(bm);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        thrView.setMyBitMap().setImageBitmap(bitmap);
                    }
                });
    }

    private Bitmap getLogoImage(Context context, int resourseId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 4;
        InputStream inputStream = context.getResources().openRawResource(resourseId);
        Bitmap bb = BitmapFactory.decodeStream(inputStream, null, options);
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bb;
    }
}
