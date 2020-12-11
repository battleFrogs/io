package com.gjc.io.f_communicate;

import lombok.Data;

import static com.gjc.io.f_communicate.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
