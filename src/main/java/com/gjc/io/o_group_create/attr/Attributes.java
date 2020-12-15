package com.gjc.io.o_group_create.attr;

import com.gjc.io.o_group_create.session.Group;
import com.gjc.io.o_group_create.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

    AttributeKey<Group> GROUP = AttributeKey.newInstance("group");

}
