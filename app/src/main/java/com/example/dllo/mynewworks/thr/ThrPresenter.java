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
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
                String s = thrView.getEditText().toString();
                String xx = "https://api.mch.weixin.qq.com/pay/unifiedorder";
                Bitmap bm = builder.encode(xx, getLogoImage((Context) thrView, R.mipmap.ic_launcher));
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


    /**
     * 获取本机Ip
     * <p/>
     * 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。
     * 获得符合 <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
     *
     * @return
     */
//    @SuppressWarnings("rawtypes")
//    private String localIp() {
//        String ip = null;
//        Enumeration allNetInterfaces = null;
//        try {
//            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//        while (allNetInterfaces.hasMoreElements()) {
//            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
//            List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
//            for (InterfaceAddress add : InterfaceAddress) {
//                InetAddress Ip = add.getAddress();
//                if (Ip != null && Ip instanceof Inet4Address) {
//                    ip = Ip.getHostAddress();
//                }
//            }
//        }
//        return ip;
//    }

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
