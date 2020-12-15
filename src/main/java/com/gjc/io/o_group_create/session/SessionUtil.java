package com.gjc.io.o_group_create.session;

import com.gjc.io.o_group_create.attr.Attributes;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private final static Map<String, Channel> map = new ConcurrentHashMap<>();
    private final static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        map.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            map.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Channel getChannel(String userId) {
        return map.get(userId);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();

    }

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        channelGroupMap.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return channelGroupMap.get(groupId);
    }

}
