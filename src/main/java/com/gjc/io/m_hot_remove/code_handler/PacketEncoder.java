package com.gjc.io.m_hot_remove.code_handler;

import com.gjc.io.m_hot_remove.communicate.code.PacketCode;
import com.gjc.io.m_hot_remove.communicate.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

        PacketCode.getInstance().encode(byteBuf, packet);

    }
}
