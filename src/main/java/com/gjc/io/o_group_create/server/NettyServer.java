package com.gjc.io.o_group_create.server;

import com.gjc.io.o_group_create.auth_handler.AuthHandler;
import com.gjc.io.o_group_create.code_handler.PacketDecoder;
import com.gjc.io.o_group_create.code_handler.PacketEncoder;
import com.gjc.io.o_group_create.server.handler.CreateGroupRequestHandler;
import com.gjc.io.o_group_create.server.handler.LoginRequestHandler;
import com.gjc.io.o_group_create.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {


    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginRequestHandler());
                        channel.pipeline().addLast(new CreateGroupRequestHandler());
                        channel.pipeline().addLast(new MessageRequestHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });


        serverBootstrap.bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("绑定端口【8000】成功");
                } else {
                    System.out.println("绑定端口【8000】失败");
                }
            }
        });
    }


}
