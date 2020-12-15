package com.gjc.io.o_group_create.communicate.packet.impl;

import com.gjc.io.o_group_create.communicate.packet.Packet;
import lombok.Data;

import java.util.List;

import static com.gjc.io.o_group_create.communicate.constant.Command.CREATE_GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {


    private boolean success;
    private String groupId;
    private List<String> usernameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }

}
