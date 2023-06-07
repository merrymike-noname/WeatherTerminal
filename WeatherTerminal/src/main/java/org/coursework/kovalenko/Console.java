package org.coursework.kovalenko;

import org.coursework.kovalenko.command.CommandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Console {
    private final Controller controller = Controller.getController();
    private final ArrayList<String> commandsList = new ArrayList<>(Arrays.asList("-ex", "-cw", "-tw", "-cdw", "-fdf", "-city", "-help"));
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
             if (commandsList.contains(command)) {
                executeCommand(command);
                 if (command.equals("-ex")) {
                     isRunning = false;
                 }
             } else {
                 System.out.println("Unknown command");
             }
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
