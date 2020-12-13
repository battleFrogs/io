package com.gjc.io.m_hot_remove.client.handler;

import com.gjc.io.m_hot_remove.attr.LoginUtil;
import com.gjc.io.m_hot_remove.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.m_hot_remove.communicate.packet.impl.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {

        if (loginResponsePacket.isSuccess()) {
            System.out.println(new Date() + "客户端登录");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        } else {
            System.out.println(new Date() + "客户端登录失败 ， 原因:" + loginResponsePacket.getReason());
        }

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("root");
        loginRequestPacket.setPassword("root");

        ctx.channel().writeAndFlush(loginRequestPacket);


    }
}
