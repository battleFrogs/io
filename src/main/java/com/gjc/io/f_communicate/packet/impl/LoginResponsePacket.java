package com.gjc.io.f_communicate.packet.impl;

import com.gjc.io.f_communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.g_login.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
