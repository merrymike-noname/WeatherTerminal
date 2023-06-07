package org.coursework.kovalenko.command;

import org.coursework.kovalenko.Controller;

public class ChosenDayWeatherCommand implements Command {
    Controller controller = Controller.getController();
    private final String day;

    public ChosenDayWeatherCommand(String day) {
        this.day = day;
    }

    @Override
    public String buildMessage() {
        int timeIndex = controller.getDateIndexForDay(day);
        if (timeIndex < 0) return "Day is out of range\n";
        return controller.threeHourInfo(timeIndex);
    }
}
