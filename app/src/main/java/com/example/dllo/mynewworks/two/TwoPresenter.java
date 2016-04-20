package com.example.dllo.mynewworks.two;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dllo on 16/4/20.
 */
public class TwoPresenter {
    private TwoView twoView;

    public void sendMyMessage(String input) {
        Observable.just(input).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return "贾亮发送" + s;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("线程", "=" + Thread.currentThread().getName());
                        sendMessage(s);
                    }
                });
    }

    private void sendMessage(String input) {
        try {
            Socket socket = new Socket("172.16.17.193", 9090);
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter wr = new BufferedWriter(osw);
            wr.write(input);

            wr.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
