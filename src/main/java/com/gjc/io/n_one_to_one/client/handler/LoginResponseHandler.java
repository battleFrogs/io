package com.gjc.io.n_one_to_one.client.handler;

import com.gjc.io.n_one_to_one.attr.LoginUtil;
import com.gjc.io.n_one_to_one.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.n_one_to_one.communicate.packet.impl.LoginResponsePacket;
import com.gjc.io.n_one_to_one.session.Session;
import com.gjc.io.n_one_to_one.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {

        String userId = loginResponsePacket.getUserId();
        String username = loginResponsePacket.getUsername();
        if (loginResponsePacket.isSuccess()) {
            System.out.println(String.format("【%s】登录成功", loginResponsePacket.getUsername()));
            SessionUtil.bindSession(new Session(userId, username), channelHandlerContext.channel());
        } else {
            System.out.println(new Date() + "客户端登录失败 ， 原因:" + loginResponsePacket.getReason());
        }

    }


//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUsername("root");
//        loginRequestPacket.setPassword("root");
//
//        ctx.channel().writeAndFlush(loginRequestPacket);
//
//
//    }
}
