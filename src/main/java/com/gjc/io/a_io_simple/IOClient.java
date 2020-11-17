package com.gjc.io.a_io_simple;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class IOClient {

    public static void main(String[] args) {

        new Thread(() -> {

            try {
                // 监听本地8000端口
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        // 每隔两秒发送信息
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

}
