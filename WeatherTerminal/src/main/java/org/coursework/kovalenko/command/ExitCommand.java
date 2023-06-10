package org.coursework.kovalenko.command;

public class ExitCommand implements Command{
    @Override
    public String buildMessage() {
        System.out.println("Exiting...");
        System.exit(0);
        return null;
    }
}
