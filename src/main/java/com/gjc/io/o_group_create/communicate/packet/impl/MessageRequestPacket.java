package com.gjc.io.o_group_create.communicate.packet.impl;

import com.gjc.io.o_group_create.communicate.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.gjc.io.o_group_create.communicate.constant.Command.MESSAGE_REQUEST;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;
    private String toUserId;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
