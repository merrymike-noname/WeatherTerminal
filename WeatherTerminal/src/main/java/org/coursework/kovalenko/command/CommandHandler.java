package org.coursework.kovalenko.command;

import java.util.Map;

public class CommandHandler {
    String command;
    Map<String, Command> commandMap;

    public CommandHandler(String command, Map<String, Command> commandMap) {
        this.command = command;
        this.commandMap = commandMap;
    }

    public void execute () {
        Command commandInterface = null;
        if (commandMap.containsKey(command)){
            commandInterface = commandMap.get(command);
            System.out.println(commandInterface.buildMessage());
        } else {
            System.out.println("Incorrect command. Try again");
        }
    }
}
