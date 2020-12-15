package com.gjc.io.o_group_create.command.impl;

import com.gjc.io.o_group_create.command.ConsoleCommand;
import com.gjc.io.o_group_create.communicate.packet.impl.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.UUID;

public class LoginConsoleCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入登录信息");

        String username = scanner.next();
        String password = scanner.next();

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword(password);
        loginRequestPacket.setUserId(UUID.randomUUID().toString());

        channel.writeAndFlush(loginRequestPacket);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
