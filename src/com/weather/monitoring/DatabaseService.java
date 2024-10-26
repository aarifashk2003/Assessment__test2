
package com.weather.monitoring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/weather";
    private static final String USER = "root";
    private static final String PASS = "password";

    public void storeDailySummary(double avgTemp, double maxTemp, double minTemp, String dominantCondition) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "INSERT INTO daily_summary (avg_temp, max_temp, min_temp, condition) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, avgTemp);
            pstmt.setDouble(2, maxTemp);
            pstmt.setDouble(3, minTemp);
            pstmt.setString(4, dominantCondition);
            pstmt.executeUpdate();
            System.out.println("Daily summary saved to database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
