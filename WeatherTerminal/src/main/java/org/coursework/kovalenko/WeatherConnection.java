package org.coursework.kovalenko;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherConnection {
    private static WeatherConnection weatherConnection;
    private static final String TOKEN = "dbddd4c7226f884898db58e2d5e7cad8";

    public static synchronized WeatherConnection getWeatherConnection() {
        if (weatherConnection == null){
            weatherConnection = new WeatherConnection();
        }
        return weatherConnection;
    }

    private WeatherConnection() {

    }

    public static Map<String, Object> jsonToMap (String input) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
        return gson.<HashMap<String, Object>>fromJson(input, type);
    }

    public Map<String, Object> getFullResponseMap (String city, String units) {
        Map<String, Object> fullResponseMap = new HashMap<>();
        String urlRequest = "https://api.openweathermap.org/data/2.5/forecast?q="
                + city + "&appid=" + TOKEN + "&units=" + units;
        try{
            StringBuilder response = new StringBuilder();
            URL url = new URL(urlRequest);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();
            fullResponseMap = jsonToMap(response.toString());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return fullResponseMap;
    }

    public ArrayList<Map<String, Object>> getContent (String city, String units) {
        Map<String, Object> fullResponseMap = getFullResponseMap(city, units);
        return (ArrayList<Map<String, Object>>) fullResponseMap.get("list");
    }
}
