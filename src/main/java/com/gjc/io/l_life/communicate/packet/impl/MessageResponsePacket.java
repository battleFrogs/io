package com.gjc.io.l_life.communicate.packet.impl;

import com.gjc.io.l_life.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.l_life.communicate.constant.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
