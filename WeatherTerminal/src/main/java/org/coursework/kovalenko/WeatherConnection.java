package org.coursework.kovalenko;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WeatherConnection {
    private static WeatherConnection weatherConnection;

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
        String TOKEN = getToken();
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

    private String getToken() {
        String configFilePath = "src/config.properties";
        FileInputStream propsInput;
        Properties prop = new Properties();
        try {
            propsInput = new FileInputStream(configFilePath);
            prop.load(propsInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop.getProperty("TOKEN");
    }

    public ArrayList<Map<String, Object>> getContent (String city, String units) {
        Map<String, Object> fullResponseMap = getFullResponseMap(city, units);
        return (ArrayList<Map<String, Object>>) fullResponseMap.get("list");
    }
}
