package com.gjc.io.m_hot_remove.communicate.packet.impl;

import com.gjc.io.m_hot_remove.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.m_hot_remove.communicate.constant.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
