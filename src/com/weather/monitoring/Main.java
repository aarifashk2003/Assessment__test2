
package com.weather.monitoring;

import Gson.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        WeatherDataFetcher fetcher = new WeatherDataFetcher();
        WeatherDataProcessor processor = new WeatherDataProcessor();
        AlertService alertService = new AlertService();
        DatabaseService dbService = new DatabaseService();

        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (String city : cities) {
                    JSONObject weatherData = fetcher.fetchWeatherData(city);
                    if (weatherData != null) {
                        processor.processWeatherData(weatherData);

                        double avgTemp = processor.calculateAverageTemperature();
                        double maxTemp = processor.calculateMaxTemperature();
                        double minTemp = processor.calculateMinTemperature();
                        String dominantCondition = processor.calculateDominantCondition();

                        alertService.checkAlert(avgTemp);
                        dbService.storeDailySummary(avgTemp, maxTemp, minTemp, dominantCondition);
                    }
                }
            }
        }, 0, 300000); // 5-minute intervals (300,000 ms)
    }
}
