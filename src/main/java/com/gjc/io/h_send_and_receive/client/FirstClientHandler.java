package com.gjc.io.h_send_and_receive.client;

import com.gjc.io.h_send_and_receive.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.h_send_and_receive.communicate.packet.impl.LoginResponsePacket;
import com.gjc.io.h_send_and_receive.communicate.packet.Packet;
import com.gjc.io.h_send_and_receive.communicate.code.PacketCode;
import com.gjc.io.h_send_and_receive.attr.LoginUtil;
import com.gjc.io.h_send_and_receive.communicate.packet.impl.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {


    //这个方法会在客户端连接建立成功之后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(new Date() + "客户端开始登录");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("root");
        loginRequestPacket.setPassword("root");

        ByteBuf byteBuf = PacketCode.getInstance().encode(ctx.alloc(), loginRequestPacket);

        ctx.channel().writeAndFlush(byteBuf);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCode.getInstance().decode(byteBuf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + "登录成功");
                LoginUtil.markAsLogin(ctx.channel());
            } else {
                System.out.println(new Date() + "登录失败：原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {

            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端信息：" + messageResponsePacket.getMessage());
        }

    }


}
