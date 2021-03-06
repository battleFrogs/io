package com.gjc.io.g_login.clinet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new FirstClientHandler());
                    }
                });

        bootstrap.connect("127.0.0.1", 8000).addListener(future -> {

            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                System.err.println("连接失败!");
                // 失败重连
            }

        });


    }


}
