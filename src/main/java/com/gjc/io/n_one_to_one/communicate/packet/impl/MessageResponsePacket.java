package com.gjc.io.n_one_to_one.communicate.packet.impl;

import com.gjc.io.n_one_to_one.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.n_one_to_one.communicate.constant.Command.MESSAGE_RESPONSE;

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
