package org.coursework.kovalenko;

import org.coursework.kovalenko.command.*;

import java.util.*;

public class Console {
    private final Controller controller = Controller.getController();
    private Map<String, Command> commandMap = new HashMap<>(){{
        put("-cw", new CurrentWeatherCommand());
        put("-tw", new TodayWeatherCommand());
        put("-cdw", new ChosenDayWeatherCommand());
        put("-fdf", new FiveDayForecastCommand());
        put("-city", new ChangeCityCommand());
        put("-help", new HelpCommand());
        put("-ex", new ExitCommand());
    }};
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
             if (commandMap.containsKey(command)) {
                executeCommand(command);
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
        CommandHandler commandHandler = new CommandHandler(command, commandMap);
        commandHandler.execute();
        commandListener();
    }
}
