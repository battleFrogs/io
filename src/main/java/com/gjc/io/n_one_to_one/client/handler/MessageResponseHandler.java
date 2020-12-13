package com.gjc.io.n_one_to_one.client.handler;

import com.gjc.io.n_one_to_one.communicate.packet.impl.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {

        String fromUseId = messageResponsePacket.getFromUseId();
        String fromUsername = messageResponsePacket.getFromUsername();

        System.out.println(fromUseId + ":" + fromUsername + "->" + messageResponsePacket.getMessage());


    }
}
