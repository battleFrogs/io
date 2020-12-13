package com.gjc.io.k_take_part_package.communicate.packet.impl;

import com.gjc.io.k_take_part_package.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.k_take_part_package.communicate.constant.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
