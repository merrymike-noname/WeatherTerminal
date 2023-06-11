package org.coursework.kovalenko;

import org.coursework.kovalenko.command.*;

import java.util.*;

public class Console {
    private final Controller controller = Controller.getController();

    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to WeatherTerminal!");
        chooseCity();
    }

    public void commandListener() {
        boolean isRunning = true;
        String command;
        while (isRunning){
            command = scanner.nextLine();
            executeCommand(command);
        }
    }

    public void chooseCity() {
        System.out.println("Enter the city name:");
        controller.update(scanner.nextLine(), "metric");
        System.out.println("City changed successfully\nType -help to get a list of available commands");
        commandListener();
    }

    private void executeCommand(String command) {
        CommandHandler commandHandler = new CommandHandler(command);
        commandHandler.execute();
        commandListener();
    }
}
