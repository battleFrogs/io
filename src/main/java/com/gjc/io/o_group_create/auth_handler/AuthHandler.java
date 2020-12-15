package com.gjc.io.o_group_create.auth_handler;

import com.gjc.io.o_group_create.attr.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("已经登陆已经无需在验证");
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
            System.out.println("断开连接");
        }

    }
}
