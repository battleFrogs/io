package com.gjc.io.n_one_to_one.server.handler;

import com.gjc.io.n_one_to_one.attr.LoginUtil;
import com.gjc.io.n_one_to_one.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.n_one_to_one.communicate.packet.impl.LoginResponsePacket;
import com.gjc.io.n_one_to_one.session.Session;
import com.gjc.io.n_one_to_one.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        String userId = UUID.randomUUID().toString();
        loginResponsePacket.setUserId(userId);
        loginResponsePacket.setSuccess(true);
        loginResponsePacket.setUsername(loginRequestPacket.getUsername());
        SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), channelHandlerContext.channel());

        System.out.println(String.format("【%s】登录成功", loginRequestPacket.getUsername()));
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }




}
