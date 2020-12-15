package com.gjc.io.o_group_create.server.handler;

import com.gjc.io.o_group_create.communicate.packet.impl.MessageRequestPacket;
import com.gjc.io.o_group_create.communicate.packet.impl.MessageResponsePacket;
import com.gjc.io.o_group_create.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {


        System.out.println(String.format("来自客户端给【%s】信息：【%s】", messageRequestPacket.getToUserId(), messageRequestPacket.getMessage()));


        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());


        Channel channel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        channel.writeAndFlush(messageResponsePacket);
    }


}
