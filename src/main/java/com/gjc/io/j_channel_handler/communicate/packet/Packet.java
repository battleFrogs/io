package com.gjc.io.j_channel_handler.communicate.packet;

import lombok.Data;

@Data
public abstract class Packet {


    /**
     * 协议版本
     */
    public Byte version = 1;


    /**
     * 指令
     */
    public abstract Byte getCommand();


}
