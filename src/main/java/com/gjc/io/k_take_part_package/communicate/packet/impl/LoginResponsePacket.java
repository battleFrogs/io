package com.gjc.io.k_take_part_package.communicate.packet.impl;

import com.gjc.io.k_take_part_package.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.k_take_part_package.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
