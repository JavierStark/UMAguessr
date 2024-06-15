package backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;
import org.umaguessr.backend.ScoreService;
import org.umaguessr.backend.ImageService;

import java.io.IOException;
import java.sql.*;

class ScoreServiceTest {

	private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
	private static final String JDBC_USER = "postgres";
	private static final String JDBC_PASSWORD = "bombardeenlaetsii";
	private static final String SAMPLE_USERNAME = "testuser";

	ScoreService myScoreService;

	@BeforeEach
	void setUp() throws IOException, SQLException {
		ImageService imageService = new ImageService();
		myScoreService = new ScoreService(imageService, SAMPLE_USERNAME);
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
	void testInitialFinalScoreIs0() {
		assertEquals(0, myScoreService.getCurrentScore());
	}

	@Test
	void testMaxScoreOnExactLocation() {
		assertEquals(ScoreService.MAX_SCORE, myScoreService.calculateScore("1", 227, 721, 0));
	}

	@Test
	void testSendingCoordinatesFurtherThan200Score0() {
		assertEquals(0, myScoreService.calculateScore("5", 7, 27, 0));
	}

	@Test
	void testFinalScoreIncreasesEachRound() {
		myScoreService.calculateScore("1", 227, 721, 0);
		assertEquals(ScoreService.MAX_SCORE, myScoreService.getCurrentScore());

		myScoreService.calculateScore("5", 124, 796, 0);
		assertEquals(2 * ScoreService.MAX_SCORE, myScoreService.getCurrentScore());

		myScoreService.calculateScore("2", 238, 790, 0);
		assertEquals(3 * ScoreService.MAX_SCORE, myScoreService.getCurrentScore());
	}

	@Test
	void testScoreIsSavedToDatabase() throws SQLException {
		myScoreService.calculateScore("1", 227, 721, 0);

		try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 PreparedStatement ps = conn.prepareStatement(
					 "SELECT score FROM scores WHERE username = ? AND image_id = ? ORDER BY attempt_time DESC LIMIT 1")) {
			ps.setString(1, SAMPLE_USERNAME);
			ps.setInt(2, 1);

			try (ResultSet rs = ps.executeQuery()) {
				assertTrue(rs.next(), "No record found in scores table.");
				assertEquals(ScoreService.MAX_SCORE, rs.getInt("score"), "The score saved to the database should match the calculated score.");
			}
		}
	}

	// White box testing
	@Test
	void testLessScoreOnFurtherFromSolution() {
		assertTrue(myScoreService.calculateScore("1", 200, 700, 0) > myScoreService.calculateScore("1", 100, 500, 0));
	}
}