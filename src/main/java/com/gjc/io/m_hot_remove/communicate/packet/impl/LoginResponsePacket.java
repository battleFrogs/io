package com.gjc.io.m_hot_remove.communicate.packet.impl;

import com.gjc.io.m_hot_remove.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.m_hot_remove.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
