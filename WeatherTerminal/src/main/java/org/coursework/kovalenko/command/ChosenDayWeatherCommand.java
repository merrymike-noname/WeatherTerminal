package org.coursework.kovalenko.command;

import org.coursework.kovalenko.Controller;

import java.util.Scanner;

public class ChosenDayWeatherCommand implements Command {
    Controller controller = Controller.getController();

    @Override
    public String buildMessage() {
        System.out.println("Choose day (not farther than 5 days):");
        Scanner scanner = new Scanner(System.in);
        int timeIndex = controller.getDateIndexForDay(scanner.nextLine());
        if (timeIndex < 0) return "Day is out of range\n";
        return controller.threeHourInfo(timeIndex);
    }
}
