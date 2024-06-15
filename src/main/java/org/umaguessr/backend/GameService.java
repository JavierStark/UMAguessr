package org.umaguessr.backend;

import java.sql.*;
import java.time.LocalDateTime;

public class GameService {

    private static final int MAX_ATTEMPTS = 6;
    private int dailyAttempt;
    private LocalDateTime lastDatePlayed;
    private String username;

    private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "WCa%YVo6L$35@7Z";

    public int getDailyAttempt() {
        return dailyAttempt;
    }

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
        this.dailyAttempt = getDailyAttemptByUsername(username);
    }

    private int getDailyAttemptByUsername(String username) {
        String query = "SELECT daily_attempt FROM scores WHERE username = ? ORDER BY attempt_time DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dailyAttempt = rs.getInt("daily_attempt");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dailyAttempt;
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
        System.out.println(lastDate);
        return lastDate;
    }

    public boolean startSession(Difficulty difficulty) {
        dailyAttempt++;
        if (!canPlay()) {
            sessionActive = false;
            return false;
        }
        dailyAttempt++;
        this.difficulty = difficulty;
        sessionActive = true;
        return true;
    }

    private boolean canPlay() {
        return lastDatePlayed == null ||
                (dailyAttempt <= MAX_ATTEMPTS || lastDatePlayed.plusDays(1).isBefore(LocalDateTime.now()));
    }

    public boolean continuePlaying(){
        dailyAttempt++;
        return canPlay();
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
