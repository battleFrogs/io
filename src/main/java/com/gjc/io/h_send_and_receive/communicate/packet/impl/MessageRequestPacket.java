package com.gjc.io.h_send_and_receive.communicate.packet.impl;

import com.gjc.io.h_send_and_receive.communicate.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.gjc.io.h_send_and_receive.communicate.constant.Command.MESSAGE_REQUEST;


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
