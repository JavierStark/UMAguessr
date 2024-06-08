package org.umaguessr.backend;

import java.sql.*;
import java.time.LocalDateTime;

public class GameService {

    private LocalDateTime lastDatePlayed;
    private String username;

    private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "bombardeenlaetsii";

    // Enum for Difficulty
    public enum Difficulty {
        Easy,
        Medium,
        Hard;
    }
    private Difficulty difficulty;
    private boolean sessionActive;

    // Constructor
    public GameService(String username) {
        this.username = username;
        this.sessionActive = false;
        this.lastDatePlayed = getLastDateByUsername(username);
    }

    private LocalDateTime getLastDateByUsername(String username) {
        LocalDateTime lastDate = null;
        String query = "SELECT attempt_time FROM scores WHERE username = ? ORDER BY attempt_time DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lastDate = rs.getTimestamp("attempt_time").toLocalDateTime();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastDate;
    }

    public void startSession(Difficulty difficulty) {
        if (lastDatePlayed != null && lastDatePlayed.plusDays(1).isAfter(LocalDateTime.now())) {
            sessionActive = false;
            return;
        }
        this.difficulty = difficulty;
        sessionActive = true;
    }

    public void endSession() {
        sessionActive = false;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public LocalDateTime getLastDatePlayed() {
        return lastDatePlayed;
    }

    public String getUsername() {
        return username;
    }
}