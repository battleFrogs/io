package com.gjc.io.k_take_part_package.code_handler;

import com.gjc.io.k_take_part_package.communicate.code.PacketCode;
import com.gjc.io.k_take_part_package.communicate.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

        PacketCode.getInstance().encode(byteBuf, packet);

    }
}
