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
		ImageService imageService = new ImageService("images.json");
		myScoreService = new ScoreService(imageService);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void theInitialFinalScoreIs0() {
		assertEquals(0, myScoreService.getFinalScore());
	}


	@Test
	void whenSendingTheExactLocationItReturnsTheFullScore() {
		assertEquals(ScoreService.MAX_SCORE, myScoreService.calculateScore("img101", 150, 250));
	}

	@Test
	void whenSendingCoordinatesFurtherThan200TheyGet0Score() {
		assertEquals(0, myScoreService.calculateScore("img105", 7, 27));
	}

	@Test
	void theFinalScoreIncreasesEachTime() {
		myScoreService.calculateScore("img101", 150, 250);
		assertEquals(ScoreService.MAX_SCORE, myScoreService.getFinalScore());

		myScoreService.calculateScore("img105", 27, 27);
		assertEquals(ScoreService.MAX_SCORE, myScoreService.getFinalScore());

		myScoreService.calculateScore("img102", 350, 450);
		assertEquals(2*ScoreService.MAX_SCORE, myScoreService.getFinalScore());

	}

	@Test
	void theFurtherAwayTheLowerTheScore() {
		assertTrue(myScoreService.calculateScore("img101", 100, 200) > myScoreService.calculateScore("img101", 80, 100));
	}

}