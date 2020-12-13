package com.gjc.io.l_life.server.handler;

import com.gjc.io.l_life.communicate.packet.impl.MessageRequestPacket;
import com.gjc.io.l_life.communicate.packet.impl.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        MessageResponsePacket messageResponsePacket = receiveMessage(messageRequestPacket);
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }


    public MessageResponsePacket receiveMessage(MessageRequestPacket messageRequestPacket) {
        System.out.println(new Date() + "：收到客户端消息:" + messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("好的，我收到了");
        messageResponsePacket.setMessage("服务器回复【" + messageResponsePacket.getMessage() + "】");
        return messageResponsePacket;
    }
}
