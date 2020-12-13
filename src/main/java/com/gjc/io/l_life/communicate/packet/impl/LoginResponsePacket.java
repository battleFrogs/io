package com.gjc.io.l_life.communicate.packet.impl;

import com.gjc.io.l_life.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.l_life.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
