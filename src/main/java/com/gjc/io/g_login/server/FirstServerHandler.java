package com.gjc.io.g_login.server;

import com.gjc.io.g_login.communicate.packet.impl.LoginRequestPacket;
import com.gjc.io.g_login.communicate.packet.impl.LoginResponsePacket;
import com.gjc.io.g_login.communicate.packet.Packet;
import com.gjc.io.g_login.communicate.code.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    // channelRead()，这个方法在接收到客户端发来的数据之后被回调
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCode.getInstance().decode(byteBuf);

        if (packet instanceof LoginRequestPacket) {
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

            // 向客户端回应登录成功
            ByteBuf responseByteBuf = PacketCode.getInstance().encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);


        }



    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }


}
