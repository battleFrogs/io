package com.gjc.io.o_group_create.server.handler;

import com.gjc.io.o_group_create.session.SessionUtil;
import com.gjc.io.o_group_create.communicate.packet.impl.CreateGroupRequestPacket;
import com.gjc.io.o_group_create.communicate.packet.impl.CreateGroupResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {

        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        List<String> usernameList = new ArrayList<>();

        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        for (String userId : userIdList) {

            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }

        }


        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        String groupId = UUID.randomUUID().toString();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUsernameList(usernameList);

        SessionUtil.bindChannelGroup(groupId, channelGroup);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("群创建成功，id:[" + createGroupResponsePacket.getGroupId() + "],");
        System.out.println("群里面有" + createGroupResponsePacket.getUsernameList());

    }
}
