package com.gjc.io.n_one_to_one.communicate.packet.impl;

import com.gjc.io.n_one_to_one.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.n_one_to_one.communicate.constant.Command.LOGIN_RESPONSE;

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
