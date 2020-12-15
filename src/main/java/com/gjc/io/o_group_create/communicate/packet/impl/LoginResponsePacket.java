package com.gjc.io.o_group_create.communicate.packet.impl;

import com.gjc.io.o_group_create.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.o_group_create.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String userId;
    private String username;
    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
