package com.gjc.io.i_pipeline.communicate.packet.impl;

import com.gjc.io.i_pipeline.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.i_pipeline.communicate.constant.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
