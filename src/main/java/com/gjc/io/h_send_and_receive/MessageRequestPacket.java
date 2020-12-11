package com.gjc.io.h_send_and_receive;

import com.gjc.io.f_communicate.Packet;
import lombok.Data;

import static com.gjc.io.f_communicate.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
