package com.gjc.io.i_pipeline.communicate.packet.impl;

import com.gjc.io.i_pipeline.communicate.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.gjc.io.i_pipeline.communicate.constant.Command.MESSAGE_REQUEST;


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
