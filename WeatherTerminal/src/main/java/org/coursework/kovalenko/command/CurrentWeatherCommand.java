package org.coursework.kovalenko.command;

import org.coursework.kovalenko.Controller;

public class CurrentWeatherCommand implements Command{
    Controller controller = Controller.getController();
    @Override
    public String buildMessage() {
        return controller.threeHourInfo(0);
    }
}
