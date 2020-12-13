package com.gjc.io.i_pipeline.communicate.packet.impl;

import com.gjc.io.i_pipeline.communicate.packet.Packet;
import lombok.Data;

import static com.gjc.io.i_pipeline.communicate.constant.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
