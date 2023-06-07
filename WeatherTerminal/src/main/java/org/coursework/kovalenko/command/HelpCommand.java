package org.coursework.kovalenko.command;

public class HelpCommand implements Command{
    @Override
    public String buildMessage() {
        return "Here is the list of commands:\n|\t-cw -- get current weather\n|\t-tw -- weather till the end of this day" +
                "\n|\t-cdw -- choose a day to get its weather at 12 p.m.\n|\t-fdf -- get five day forecast (for 12 p.m. each day)" +
                "\n|\t-help -- get list of commands\n|\t-ex -- exit";
    }
}
