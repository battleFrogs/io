package com.gjc.io.o_group_create.server.handler;

import com.gjc.io.o_group_create.attr.LoginUtil;
import com.gjc.io.o_group_create.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.o_group_create.communicate.packet.impl.LoginResponsePacket;
import com.gjc.io.o_group_create.session.Session;
import com.gjc.io.o_group_create.session.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        String username = loginRequestPacket.getUsername();

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(String.format("[%s]登录", username));
//            LoginUtil.markAsLogin(channelHandlerContext.channel());
            Session session = new Session();
            session.setUsername(loginRequestPacket.getUsername());
            session.setUserId(loginRequestPacket.getUserId());
            SessionUtil.bindSession(session, channelHandlerContext.channel());
            System.out.println(SessionUtil.getChannel(session.getUserId()) != null);
            System.out.println(session.getUserId());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号或者密码错误");
            System.out.println(String.format("用户【%s】登录失败,账号或者密码错误", username));
        }

        loginResponsePacket.setUserId(loginRequestPacket.getUserId());
        loginResponsePacket.setUsername(loginRequestPacket.getUsername());

        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);

    }



    private boolean valid(LoginRequestPacket loginRequestPacket) {
//        if ("root".equals(loginRequestPacket.getUsername())
//                && "root".equals(loginRequestPacket.getPassword())) {
//            return true;
//        }
//        return false;
        return true;


    }
}
