package com.gjc.io.o_group_create.communicate.packet.impl;

import com.gjc.io.o_group_create.communicate.packet.Packet;
import lombok.Data;

import java.util.List;

import static com.gjc.io.o_group_create.communicate.constant.Command.CREATE_GROUP_REQUEST;

@Data
public class CreateGroupRequestPacket extends Packet {


    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
