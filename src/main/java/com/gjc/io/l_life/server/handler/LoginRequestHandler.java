package com.gjc.io.l_life.server.handler;

import com.gjc.io.l_life.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.l_life.communicate.packet.impl.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        // 登录逻辑
        LoginResponsePacket loginResponsePacket = login(loginRequestPacket);
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }


    private LoginResponsePacket login(LoginRequestPacket packet) {
        LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;




        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(loginRequestPacket)) {
            // 校验成功
            loginResponsePacket.setSuccess(true);
        } else {
            // 校验失败
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
        }

        return loginResponsePacket;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
