package com.gjc.io.n_one_to_one.session;

import com.gjc.io.n_one_to_one.attr.Attributes;
import com.gjc.io.n_one_to_one.attr.LoginUtil;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {

        System.out.println(String.format("绑定的session对应的channel的信息 userId ：%s ," +
                "username : %s ", session.getUserId(), session.getUserName()));
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);

    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            System.out.println(String.format("解绑session对应的channel对应信息 userId： %s ," +
                    "username : %s ", getSession(channel).getUserId(), getSession(channel).getUserName()));
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }



}
