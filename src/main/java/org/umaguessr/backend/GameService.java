package org.umaguessr.backend;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * The GameService class represents a service for managing game sessions.
 */
public class GameService {

    private LocalDateTime lastDatePlayed;
    private String username;

    private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "bombardeenlaetsii";

    /**
     * Enum for Difficulty.
     */
    public enum Difficulty {
        Easy,
        Medium,
        Hard;
    }

    private Difficulty difficulty;
    private boolean sessionActive;

    /**
     * Constructs a GameService object with the specified username.
     *
     * @param username the username of the player
     */
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

    /**
     * Starts a game session with the specified difficulty.
     *
     * @param difficulty the difficulty level of the game
     */
    public void startSession(Difficulty difficulty) {
        if (lastDatePlayed != null && lastDatePlayed.plusDays(1).isAfter(LocalDateTime.now())) {
            sessionActive = false;
            return;
        }
        this.difficulty = difficulty;
        sessionActive = true;
    }

    /**
     * Ends the current game session.
     */
    public void endSession() {
        sessionActive = false;
    }

    /**
     * Checks if a game session is active.
     *
     * @return true if a game session is active, false otherwise
     */
    public boolean isSessionActive() {
        return sessionActive;
    }

    /**
     * Gets the last date played by the user.
     *
     * @return the last date played
     */
    public LocalDateTime getLastDatePlayed() {
        return lastDatePlayed;
    }

    /**
     * Gets the username of the player.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}