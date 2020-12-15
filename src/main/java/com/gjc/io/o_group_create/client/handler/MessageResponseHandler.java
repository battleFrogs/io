package com.gjc.io.o_group_create.client.handler;

import com.gjc.io.o_group_create.communicate.packet.impl.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {

        System.out.println(String.format("收到了消息【%s】", messageResponsePacket.getMessage()));

    }
}
