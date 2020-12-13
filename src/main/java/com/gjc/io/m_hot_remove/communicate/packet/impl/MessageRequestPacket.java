package com.gjc.io.m_hot_remove.communicate.packet.impl;

import com.gjc.io.m_hot_remove.communicate.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.gjc.io.m_hot_remove.communicate.constant.Command.MESSAGE_REQUEST;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
