package org.coursework.kovalenko;

import java.util.ArrayList;
import java.util.Map;

public class Controller {
    private static Controller controller;
    private final WeatherConnection weatherConnection = WeatherConnection.getWeatherConnection();
    private ArrayList<Map<String, Object>> content = null;

    public static synchronized Controller getController() {
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    private Controller() {

    }

    public void update (String city, String units) {
        content = weatherConnection.getContent(city, units);
        if (content == null) {
            System.out.println("\nIncorrect city name");
            System.exit(0);
        }
    }

    private Map<String, Object> getRequestedData (int timeIndex, String requestedContent) {
        if (requestedContent.equals("weather")){
            ArrayList<Map<String, Object>> weather = (ArrayList<Map<String, Object>>) content.get(timeIndex).get("weather");
            return weather.get(0);
        }
        return (Map<String, Object>) content.get(timeIndex).get(requestedContent);
    }

    public int getDay (int timeIndex) {
        return Integer.parseInt(content.get(timeIndex).get("dt_txt").toString().substring(8, 10));
    }

    public int getDateIndexForDay(String day){
        int i = 0;
        while(i < content.size() && !content.get(i).get("dt_txt").toString().matches("\\d{4}-\\d{2}-" + day + " 12:00:00")){
            i++;
        }
        if (i >= content.size()) {
            i = -1;
        }
        return i;
    }

    public int getLogLength() {
        return content.size();
    }

    public String threeHourInfo (int timeIndex) {
        StringBuilder threeHourInfo = new StringBuilder();
        Map<String, Object> mainData = getRequestedData(timeIndex, "main");
        Map<String, Object> weatherData = getRequestedData(timeIndex, "weather");
        Map<String, Object> windData = getRequestedData(timeIndex, "wind");

        threeHourInfo.append("\n- Date & time: ").append(content.get(timeIndex).get("dt_txt")).append("\n");
        threeHourInfo.append("- Temperature: ").append(mainData.get("temp")).append("\n");
        threeHourInfo.append("- Feels like: ").append(mainData.get("feels_like")).append("\n");
        threeHourInfo.append("- Wind speed: ").append(windData.get("speed")).append("\n");
        threeHourInfo.append("- Weather: ").append(weatherData.get("main"))
                .append(" (").append(weatherData.get("description")).append(")");
        return threeHourInfo.toString();
    }
}
