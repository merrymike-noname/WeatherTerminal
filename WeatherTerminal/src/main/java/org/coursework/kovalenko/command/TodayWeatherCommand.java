package org.coursework.kovalenko.command;

import org.coursework.kovalenko.Controller;

import java.util.Calendar;

public class TodayWeatherCommand implements Command{
    Controller controller = Controller.getController();
    @Override
    public String buildMessage() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int timeIndex = 0;
        StringBuilder message = new StringBuilder();
        while (day == controller.getDay(timeIndex)) {
            message.append(controller.threeHourInfo(timeIndex));
            timeIndex++;
        }
        return message.toString();
    }
}
