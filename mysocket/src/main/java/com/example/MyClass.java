package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 7.应用层
 * 6.表示层
 * 5.会话层
 * 4.传输层        段    端口号
 * 3.网络层        包    IP地址
 * 2.数据链路层    帧    MAC地址   ARP协议
 * 1.物理层       比特      bit
 */


/**
 * //socket 套接字
 */
public class MyClass {

    public static void main(String[] args) {
        /**
         *  端口号范围0_65535  其中1024之前都被规定
         */

        try {
            final ServerSocket serverSocket = new ServerSocket(9090);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            //会阻塞当前线程 知道接收到消息
                            Socket socket = serverSocket.accept();
//获取输入流
                            InputStream is = socket.getInputStream();
                            InputStreamReader reader = new InputStreamReader(is);
                            BufferedReader br = new BufferedReader(reader);
                            StringBuilder builder = new StringBuilder();
                            String str = "";
                            while ((str = br.readLine()) != null) {
                                builder.append(str);
                            }
                            String content = builder.toString();
                            System.out.println("接收到消息-->" + content);

                            br.close();
                            socket.close();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
