package com.gjc.io.o_group_create.command.impl;

import com.gjc.io.o_group_create.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {


    private Map<String, ConsoleCommand> consoleCommandMap;


    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
//        consoleCommandMap.put("sendToUser", );
//        consoleCommandMap.put("logout", );
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.out.println("无法识别【" + command + "】指令，请重新输入");
        }
    }
}
