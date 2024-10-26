
package com.weather.monitoring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import Gson . JSONObject;

public class WeatherDataFetcher {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public JSONObject fetchWeatherData(String city) {
        try {
            String urlString = BASE_URL + city + "&appid=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return new JSONObject(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

