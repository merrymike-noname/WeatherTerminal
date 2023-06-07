package org.coursework.kovalenko;

import org.coursework.kovalenko.command.CommandHandler;

import java.util.Scanner;

public class Console {
    private final Controller controller = Controller.getController();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to WeatherTerminal!");
        updateController();
    }

    public void updateController(){
        chooseCity();
    }

    public void chooseAction() {
        System.out.println("""
                What do you want to do? (pick a number)
                |\t1. See current weather
                |\t2. See today`s weather
                |\t3. See weather for a chosen day
                |\t4. See a 5-day forecast
                |\t5. Change city
                |\t6. Exit""");
        executeCommand(Integer.parseInt(scanner.nextLine()));
    }

    public void chooseCity() {
        System.out.println("Enter the city name:");
        controller.update(scanner.nextLine(), "metric");
        chooseAction();
    }

    private void executeCommand(int commandIndex) {
        CommandHandler commandHandler = new CommandHandler(commandIndex);
        commandHandler.execute();
        chooseAction();
    }
}
