package com.gjc.io.f_communicate;

import lombok.Data;

import static com.gjc.io.f_communicate.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet {


    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }


}
