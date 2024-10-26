
package com.weather.monitoring;

public class AlertService {
    private static final double TEMP_THRESHOLD = 35.0;

    public void checkAlert(double temperature) {
        if (temperature > TEMP_THRESHOLD) {
            System.out.println("Alert: Temperature exceeded threshold of " + TEMP_THRESHOLD + "°C!");
        }
    }
}
