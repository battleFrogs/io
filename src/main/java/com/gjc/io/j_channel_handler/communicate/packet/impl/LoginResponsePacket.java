package com.gjc.io.j_channel_handler.communicate.packet.impl;

import com.gjc.io.j_channel_handler.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.j_channel_handler.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
