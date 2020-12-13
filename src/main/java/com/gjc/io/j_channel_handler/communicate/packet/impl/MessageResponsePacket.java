package com.gjc.io.j_channel_handler.communicate.packet.impl;

import com.gjc.io.j_channel_handler.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.j_channel_handler.communicate.constant.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
