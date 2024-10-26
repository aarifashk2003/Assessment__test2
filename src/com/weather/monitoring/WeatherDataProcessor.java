
package com.weather.monitoring;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataProcessor {
    private List<Double> dailyTemperatures = new ArrayList<>();
    private List<String> weatherConditions = new ArrayList<>();

    public double convertKelvinToCelsius(double kelvinTemp) {
        return kelvinTemp - 273.15;
    }

    public void processWeatherData(JSONObject weatherData) {
        double tempKelvin = ((Object) weatherData).getJSONObject("main").getDouble("temp");
        double tempCelsius = convertKelvinToCelsius(tempKelvin);
        String condition = weatherData.getJSONArray("weather").getJSONObject(0).getString("main");

        dailyTemperatures.add(tempCelsius);
        weatherConditions.add(condition);

        System.out.println("Current Temp: " + tempCelsius + "°C, Condition: " + condition);
    }

    public double calculateAverageTemperature() {
        return dailyTemperatures.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public double calculateMaxTemperature() {
        return dailyTemperatures.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
    }

    public double calculateMinTemperature() {
        return dailyTemperatures.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
    }

    public String calculateDominantCondition() {
        return weatherConditions.stream()
                .reduce((a, b) -> a.equals(b) ? a : "Mixed")
                .orElse("Unknown");
    }
}
