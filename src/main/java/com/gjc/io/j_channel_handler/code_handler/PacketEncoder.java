package com.gjc.io.j_channel_handler.code_handler;

import com.gjc.io.j_channel_handler.communicate.packet.Packet;
import com.gjc.io.j_channel_handler.communicate.code.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCode.getInstance().encode(byteBuf, packet);

    }
}
