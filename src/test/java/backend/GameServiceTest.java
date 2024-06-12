package backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.umaguessr.backend.GameService;
import java.time.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "bombardeenlaetsii";
    private static final String SAMPLE_USERNAME = "testuser";

    @BeforeEach
    void setUp() throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement()) {
            String query = "INSERT INTO scores (image_id, score, daily_attempt, username, attempt_time) " +
                    "VALUES (1, 50, 1, '" + SAMPLE_USERNAME + "', '" + LocalDateTime.now().minusDays(3) + "')";
            stmt.executeUpdate(query);
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement()) {
            String query = "DELETE FROM scores WHERE username = '" + SAMPLE_USERNAME + "'";
            stmt.executeUpdate(query);
        }
    }

    @Test
    void testStartSession() {
        GameService sut = new GameService(SAMPLE_USERNAME);
        sut.startSession(GameService.Difficulty.Easy);
        assertTrue(sut.isSessionActive());
    }

    @Test
    void testGetLastDatePlayed() {
        GameService sut = new GameService(SAMPLE_USERNAME);
        assertNotNull(sut.getLastDatePlayed());
    }

    @Test
    void testCannotStartSessionIfLessThanADayPassed() throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = conn.createStatement()) {
            String query = "INSERT INTO scores (image_id, score, daily_attempt, username, attempt_time) " +
                    "VALUES (1, 50, 1, '" + SAMPLE_USERNAME + "', '" + LocalDateTime.now() + "')";
            stmt.executeUpdate(query);
        }

        GameService sut = new GameService(SAMPLE_USERNAME);
        assertFalse(sut.startSession(GameService.Difficulty.Easy));
    }

    @Test
    void testCanStartSessionIfMoreThanADayPassed() {
        GameService sut = new GameService(SAMPLE_USERNAME);
        sut.startSession(GameService.Difficulty.Easy);
        assertTrue(sut.isSessionActive());
    }

    @Test
    void testEndSession() {
        GameService sut = new GameService(SAMPLE_USERNAME);
        sut.startSession(GameService.Difficulty.Easy);
        sut.endSession();
        assertFalse(sut.isSessionActive());
    }
}