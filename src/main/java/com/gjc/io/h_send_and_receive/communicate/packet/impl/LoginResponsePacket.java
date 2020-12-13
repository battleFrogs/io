package com.gjc.io.h_send_and_receive.communicate.packet.impl;

import com.gjc.io.h_send_and_receive.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.h_send_and_receive.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
