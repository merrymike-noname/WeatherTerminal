package org.coursework.kovalenko.command;

import java.util.Scanner;

public class CommandHandler {
    String command;

    public CommandHandler (String command){
        this.command = command;
    }

    public void execute () {
        Command commandInterface = null;
        switch (command){
            case "-cw": commandInterface = new CurrentWeatherCommand();
            break;
            case "-tw": commandInterface = new TodayWeatherCommand();
            break;
            case "-cdw": commandInterface = new ChosenDayWeatherCommand(chooseDate());
            break;
            case "-fdf": commandInterface = new FiveDayForecastCommand();
            break;
            case "-city": commandInterface = new ChangeCityCommand();
            break;
            case "-help": commandInterface = new HelpCommand();
            break;
            case "-ex":
                System.out.println("Exiting...");
                System.exit(0);
        }
        if (commandInterface == null) {
            System.out.println("Incorrect command. Try again");
        } else System.out.println(commandInterface.buildMessage());
    }

    private String chooseDate() {
        System.out.println("Choose day (not farther than 5 days):");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
