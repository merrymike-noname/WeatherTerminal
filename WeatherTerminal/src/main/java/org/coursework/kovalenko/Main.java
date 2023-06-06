package org.coursework.kovalenko;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Main {
    public static final String TOKEN = "dbddd4c7226f884898db58e2d5e7cad8";
    public static Map<String, Object> jsonToMap (String input){
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>(){}.getType();
        HashMap<String, Object> map = gson.fromJson(input, type);
        return map;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the city name: ");
        String city = scanner.nextLine();
        String urlRequest = "http://api.openweathermap.org/data/2.5/forecast?q="
                + city + "&appid=" + TOKEN + "&units=metric";
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
            //System.out.println(response);
            Map<String, Object> responseMapFull = jsonToMap(response.toString());
            ArrayList<Map<String, Object>> content = (ArrayList<Map<String, Object>>) responseMapFull.get("list");
            Map<String, Object> main = (Map<String, Object>) content.get(0).get("main");

            System.out.println(main.get("temp"));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}