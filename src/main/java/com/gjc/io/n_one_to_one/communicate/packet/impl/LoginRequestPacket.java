package com.gjc.io.n_one_to_one.communicate.packet.impl;

import com.gjc.io.n_one_to_one.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.n_one_to_one.communicate.constant.Command.LOGIN_REQUEST;

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
