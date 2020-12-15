package com.gjc.io.o_group_create.communicate.packet.impl;

import com.gjc.io.o_group_create.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.o_group_create.communicate.constant.Command.LOGIN_REQUEST;

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
