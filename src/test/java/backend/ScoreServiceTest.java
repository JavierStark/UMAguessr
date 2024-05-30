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

class ScoreServiceTest {

	ScoreService myScoreService;

	@BeforeEach
	void setUp() throws Exception {
		ImageService imageService = new ImageService();
		myScoreService = new ScoreService(imageService);
	}

	@Test
	void testInitialFinalScoreIs0() {
		assertEquals(0, myScoreService.getFinalScore());
	}

	@Test
	void testMaxScoreOnExactLocation() {
		assertEquals(ScoreService.MAX_SCORE, myScoreService.calculateScore("img101", 227, 721));
	}

	@Test
	void testSendingCoordinatesFurtherThan200Score0() {
		assertEquals(0, myScoreService.calculateScore("img105", 7, 27));
	}

	@Test
	void testFinalScoreIncreasesEachRound() {
		myScoreService.calculateScore("img101", 227, 721);
		assertEquals(ScoreService.MAX_SCORE, myScoreService.getFinalScore());

		myScoreService.calculateScore("img105", 124, 796);
		assertEquals(2*ScoreService.MAX_SCORE, myScoreService.getFinalScore());

		myScoreService.calculateScore("img102", 238, 790);
		assertEquals(3*ScoreService.MAX_SCORE, myScoreService.getFinalScore());

	}

	// white box testing
	@Test
	void testLessScoreOnFurtherFromSolution() {
		assertFalse(myScoreService.calculateScore("img101", 100, 200) > myScoreService.calculateScore("img101", 80, 100));
	}

}