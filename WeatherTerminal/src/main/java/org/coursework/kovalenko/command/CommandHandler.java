package org.coursework.kovalenko.command;

import java.util.Scanner;

public class CommandHandler {
    int commandIndex;

    public CommandHandler (int commandIndex){
        this.commandIndex = commandIndex;
    }

    public void execute () {
        Command command = null;
        switch (commandIndex){
            case 1: command = new CurrentWeatherCommand();
            break;
            case 2: command = new TodayWeatherCommand();
            break;
            case 3: command = new ChosenDayWeatherCommand(chooseDate());
            break;
            case 4: command = new FiveDayForecastCommand();
            break;
            case 5: command = new ChangeCityCommand();
            break;
            case 6: System.exit(0);
        }
        if (command == null) {
            System.out.println("Incorrect command. Try again");
        } else System.out.println(command.buildMessage());
    }

    private String chooseDate() {
        System.out.println("Choose day (not farther than 5 days):");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
