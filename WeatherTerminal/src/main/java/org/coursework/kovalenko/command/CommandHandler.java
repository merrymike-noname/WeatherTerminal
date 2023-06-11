package org.coursework.kovalenko.command;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    String command;
    private Map<String, Command> commandMap = new HashMap<>(){{
        put("-cw", new CurrentWeatherCommand());
        put("-tw", new TodayWeatherCommand());
        put("-cdw", new ChosenDayWeatherCommand());
        put("-fdf", new FiveDayForecastCommand());
        put("-city", new ChangeCityCommand());
        put("-help", new HelpCommand());
        put("-ex", new ExitCommand());
    }};

    public CommandHandler(String command) {
        this.command = command;
    }

    public void execute () {
        Command commandInterface;
        if (commandMap.containsKey(command)){
            commandInterface = commandMap.get(command);
            System.out.println(commandInterface.buildMessage());
        } else {
            System.out.println("Unknown command");
        }
    }
}
