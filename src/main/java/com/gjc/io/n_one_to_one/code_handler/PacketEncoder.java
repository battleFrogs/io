package com.gjc.io.n_one_to_one.code_handler;

import com.gjc.io.n_one_to_one.communicate.code.PacketCode;
import com.gjc.io.n_one_to_one.communicate.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

        PacketCode.getInstance().encode(byteBuf, packet);

    }
}
