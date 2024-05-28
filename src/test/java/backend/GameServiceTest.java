package backend;

import org.junit.jupiter.api.Test;
import org.umaguessr.backend.GameService;
import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {
    @Test
    void testStartSession() {
        GameService sut = new GameService();
        sut.startSession(GameService.Difficulty.Easy);
        assertTrue(sut.isSessionActive());
    }

    @Test
    void testGetLastDatePlayed(){
        LocalDateTime date = LocalDateTime.of(2021, 1, 1, 0, 0);
        GameService sut = new GameService(date);
        assertEquals(date, sut.getLastDatePlayed());
    }

    @Test
    void testCannotStartSessionIfLessThanADayPassed() {
        LocalDateTime date = LocalDateTime.now();
        GameService sut = new GameService(date);
        sut.startSession(GameService.Difficulty.Easy);
        assertFalse(sut.isSessionActive());
    }

    @Test
    void testCanStartSessionIfMoreThanADayPassed() {
        LocalDateTime date = LocalDateTime.now().minusDays(2);
        GameService sut = new GameService(date);
        sut.startSession(GameService.Difficulty.Easy);
        assertTrue(sut.isSessionActive());
    }

    @Test
    void testEndSession() {
        GameService sut = new GameService();
        sut.startSession(GameService.Difficulty.Easy);
        sut.endSession();
        assertFalse(sut.isSessionActive());
    }
}
