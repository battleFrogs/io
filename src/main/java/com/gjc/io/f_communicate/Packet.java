package com.gjc.io.f_communicate;


import lombok.Data;

@Data
public abstract class Packet {


    // 协议版本
    private Byte version = 1;
    // 指令
    public abstract Byte getCommond();



}
