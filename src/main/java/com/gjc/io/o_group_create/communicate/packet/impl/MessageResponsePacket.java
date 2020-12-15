package com.gjc.io.o_group_create.communicate.packet.impl;

import com.gjc.io.o_group_create.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.o_group_create.communicate.constant.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;
    private String fromUseId;
    private String fromUsername;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
