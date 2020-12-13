package com.gjc.io.n_one_to_one.server.handler;

import com.gjc.io.n_one_to_one.communicate.packet.impl.MessageRequestPacket;
import com.gjc.io.n_one_to_one.communicate.packet.impl.MessageResponsePacket;
import com.gjc.io.n_one_to_one.session.Session;
import com.gjc.io.n_one_to_one.session.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {

        //  拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(channelHandlerContext.channel());

        System.out.println("toUserId:" + messageRequestPacket.getToUserId());
        // 通过消息发送方的会话信息构造要发送的信息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUseId(session.getUserId());
        messageResponsePacket.setFromUsername(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        // 获取消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        // 发送消息给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.out.println("[" + messageRequestPacket.getToUserId() + "] 不在线 , 发送失败");
        }

    }


}
