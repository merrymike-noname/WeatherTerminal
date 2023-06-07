package org.coursework.kovalenko.command;

import org.coursework.kovalenko.Console;

public class ChangeCityCommand implements Command{
    @Override
    public String buildMessage() {
        Console console = new Console();
        console.chooseCity();
        return "";
    }
}
