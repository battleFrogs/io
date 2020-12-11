package com.gjc.io.h_send_and_receive;

import com.gjc.io.f_communicate.LoginRequestPacket;
import com.gjc.io.f_communicate.LoginResponsePacket;
import com.gjc.io.f_communicate.Packet;
import com.gjc.io.f_communicate.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    // channelRead()，这个方法在接收到客户端发来的数据之后被回调
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.getInstance().decode(byteBuf);

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
            ByteBuf responseByteBuf = PacketCodeC.getInstance().encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);

        } else if (packet instanceof MessageRequestPacket) {

            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + "：收到客户端消息:" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("好的，我收到了");
            messageResponsePacket.setMessage("服务器回复【" + messageResponsePacket.getMessage() + "】");
            ByteBuf messageResponseByteBuf = PacketCodeC.getInstance().encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(messageResponseByteBuf);

        }



    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }


}
