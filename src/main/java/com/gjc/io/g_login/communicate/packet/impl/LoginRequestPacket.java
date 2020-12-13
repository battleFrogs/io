package com.gjc.io.g_login.communicate.packet.impl;

import com.gjc.io.g_login.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.g_login.communicate.constant.Command.LOGIN_REQUEST;

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
