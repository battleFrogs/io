package com.gjc.io.h_send_and_receive.communicate.packet.impl;

import com.gjc.io.h_send_and_receive.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.h_send_and_receive.communicate.constant.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
