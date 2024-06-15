package org.umaguessr.backend;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This auxiliary class handles all database-related queries.
 */
public abstract class DatabaseService {

    private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "WCa%YVo6L$35@7Z";

    // ScoreService's methods

    public static void saveScoreIntoDatabase(String imageId, int score, int dailyAttempt, String username) {

        String query = "INSERT INTO scores (image_id, score, daily_attempt, username) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, Integer.parseInt(imageId));
            ps.setInt(2, score);
            ps.setInt(3, dailyAttempt); // Default daily attempt value for simplicity
            ps.setString(4, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[ERROR | SQL] Saving score into database.");
            e.printStackTrace();
        }
    }


    // ImageService's methods

    public static void loadImagesData(List<Image> imagesData)  {

        String query = "SELECT * FROM images";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                imagesData.add(new Image(
                        Integer.toString(rs.getInt("image_id")),
                        rs.getString("image_url"),
                        new int[]{rs.getInt("pos_x"), rs.getInt("pos_y")},
                        rs.getString("faculty"),
                        rs.getInt("difficulty")
                ));
            }
        } catch (SQLException e) {
            System.err.println("[ERROR | SQL] Loading image data.");
            e.printStackTrace();
        }
    }

    // GameService's methods

    public static int getNumberOfDailyAttemptsByUsername(String username) {

        int dailyAttempts = 0;

        String query = "SELECT daily_attempt FROM scores WHERE username = ? ORDER BY attempt_time DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dailyAttempts = rs.getInt("daily_attempt");
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR | SQL] Reading daily number of attempts by username.");
            e.printStackTrace();
        }

        return dailyAttempts;
    }

    public static LocalDateTime getLastDatePlayedByUsername(String username) {

        LocalDateTime lastDate = null;

        String query = "SELECT attempt_time FROM scores WHERE username = ? ORDER BY attempt_time DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getTimestamp("attempt_time") != null){
                    lastDate = rs.getTimestamp("attempt_time").toLocalDateTime();
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR | SQL] Reading last date played by username.");
            e.printStackTrace();
        }

        return lastDate;

    }

}
