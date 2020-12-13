package com.gjc.io.l_life.communicate.packet.impl;

import com.gjc.io.l_life.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.l_life.communicate.constant.Command.LOGIN_REQUEST;

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
