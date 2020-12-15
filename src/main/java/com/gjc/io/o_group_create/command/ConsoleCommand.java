package com.gjc.io.o_group_create.command;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
