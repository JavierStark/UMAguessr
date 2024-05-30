package backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.umaguessr.backend.ScoreService;
import org.umaguessr.backend.Image;
import org.umaguessr.backend.ImageService;

import java.io.IOException;
import java.sql.SQLException;

class ScoreServiceTest {

	ScoreService myScoreService;

	@BeforeEach
	void setUp() throws IOException, SQLException {
		ImageService imageService = new ImageService();
		myScoreService = new ScoreService(imageService);
	}

	@Test
	void testInitialFinalScoreIs0() {
		assertEquals(0, myScoreService.getFinalScore());
	}

	@Test
	void testMaxScoreOnExactLocation() {
		assertEquals(ScoreService.MAX_SCORE, myScoreService.calculateScore("1", 227, 721));
	}

	@Test
	void testSendingCoordinatesFurtherThan200Score0() {
		assertEquals(0, myScoreService.calculateScore("5", 7, 27));
	}

	@Test
	void testFinalScoreIncreasesEachRound() {
		myScoreService.calculateScore("1", 227, 721);
		assertEquals(ScoreService.MAX_SCORE, myScoreService.getFinalScore());

		myScoreService.calculateScore("5", 124, 796);
		assertEquals(2*ScoreService.MAX_SCORE, myScoreService.getFinalScore());

		myScoreService.calculateScore("2", 238, 790);
		assertEquals(3*ScoreService.MAX_SCORE, myScoreService.getFinalScore());
	}

	// white box testing
	@Test
	void testLessScoreOnFurtherFromSolution() {
		assertFalse(myScoreService.calculateScore("1", 100, 200) > myScoreService.calculateScore("1", 80, 100));
	}
}