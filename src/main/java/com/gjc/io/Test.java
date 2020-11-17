package com.gjc.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws IOException {

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(8, 16,
                        10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                        Executors.defaultThreadFactory());

        ServerSocket serverSocket = new ServerSocket(1024);
        System.out.println("服务启动");
        while (true) {
            System.out.println("服务器名称：" + Thread.currentThread().getName());
            System.out.println("等待连接中");
            final Socket socket = serverSocket.accept();
            System.out.println("有连接进来了");
            threadPoolExecutor.execute(() -> handler(socket));
        }
    }

    public static void handler(Socket socket) {
        System.out.println("线程名称" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("进行线程通信的线程名称" + Thread.currentThread().getName());
                System.out.println("reading");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
