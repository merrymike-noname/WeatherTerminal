package org.coursework.kovalenko.command;

import org.coursework.kovalenko.Controller;

import java.time.LocalTime;
import java.util.Calendar;

public class FiveDayForecastCommand implements Command {
    Controller controller = Controller.getController();
    @Override
    public String buildMessage() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        LocalTime currentTime = LocalTime.now();
        int hours = currentTime.getHour();
        if (hours >= 15) day++;
        String startDay = String.valueOf(day);
        if (startDay.length() == 1) startDay = "0" + startDay;
        int startIndex = controller.getDateIndexForDay(startDay);
        StringBuilder forecast = new StringBuilder();
        for (int i = startIndex; i < controller.getLogLength(); i += 8) {
            forecast.append(controller.threeHourInfo(i));
        }
        return forecast.toString();
    }
}
