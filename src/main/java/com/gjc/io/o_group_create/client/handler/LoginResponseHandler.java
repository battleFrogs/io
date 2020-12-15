package com.gjc.io.o_group_create.client.handler;

import com.gjc.io.o_group_create.attr.LoginUtil;
import com.gjc.io.o_group_create.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.o_group_create.communicate.packet.impl.LoginResponsePacket;
import com.gjc.io.o_group_create.session.Session;
import com.gjc.io.o_group_create.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        String username = loginResponsePacket.getUsername();
        String userId = loginResponsePacket.getUserId();

        if (loginResponsePacket.isSuccess()) {
            System.out.println(String.format("用户【%s】您已登录信息 ,userId:%s", username, userId));
//            LoginUtil.markAsLogin(channelHandlerContext.channel());
            Session session = new Session();
            session.setUserId(loginResponsePacket.getUserId());
            session.setUsername(loginResponsePacket.getUsername());
            SessionUtil.bindSession(session, channelHandlerContext.channel());
        } else {
            System.out.println(String.format("用户【%s】登录失败", username));
        }


    }


//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setUserId(UUID.randomUUID().toString());
//        loginRequestPacket.setUsername("root");
//        loginRequestPacket.setPassword("root1");
//
//        ctx.channel().writeAndFlush(loginRequestPacket);
//
//    }
}
