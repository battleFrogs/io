package com.gjc.io.o_group_create.client;

import com.gjc.io.o_group_create.attr.LoginUtil;
import com.gjc.io.o_group_create.client.handler.CreateGroupResponseHandler;
import com.gjc.io.o_group_create.client.handler.LoginResponseHandler;
import com.gjc.io.o_group_create.client.handler.MessageResponseHandler;
import com.gjc.io.o_group_create.code_handler.PacketDecoder;
import com.gjc.io.o_group_create.code_handler.PacketEncoder;
import com.gjc.io.o_group_create.command.impl.ConsoleCommandManager;
import com.gjc.io.o_group_create.command.impl.LoginConsoleCommand;
import com.gjc.io.o_group_create.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.o_group_create.communicate.packet.impl.MessageRequestPacket;
import com.gjc.io.o_group_create.communicate.packet.impl.MessageResponsePacket;
import com.gjc.io.o_group_create.session.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;
import java.util.UUID;

public class NettyClient {


    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();


        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new CreateGroupResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });

        bootstrap.connect("127.0.0.1", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {

                if (future.isSuccess()) {
                    System.out.println("连接服务端【8000】端口成功");
                    Channel channel = ((ChannelFuture) future).channel();
                    startThread(channel);

                } else {
                    System.out.println("连接服务端【8000】端口失败");
                }

            }
        });
    }

    public static void startThread(Channel channel) {

        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {

            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }

        }).start();
    }


}
