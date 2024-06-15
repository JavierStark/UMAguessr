package org.umaguessr.backend;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * This class handles the user interaction with the game. It controls
 * the maximum amount of times per day a user can play.
 */
public class GameService {

    private static final int MAX_ATTEMPTS = 6;
    private int dailyAttempt;
    private LocalDateTime lastDatePlayed;
    private String username;

    public int getDailyAttempt() {
        return dailyAttempt;
    }

    private int gameDifficulty;
    private boolean sessionActive;

    public GameService(String username) {
        this.username = username;
        this.sessionActive = false;
        this.lastDatePlayed = getLastDateByUsername(username);
        this.dailyAttempt = getNumberOfDailyAttemptsByUsername(username);
    }

    private int getNumberOfDailyAttemptsByUsername(String username) {

        return DatabaseService.getNumberOfDailyAttemptsByUsername(username);
    }

    private LocalDateTime getLastDateByUsername(String username) {

        return DatabaseService.getLastDatePlayedByUsername(username);
    }

    public boolean startSession(int newGameDifficulty) {

        if (!canPlay()) {
            sessionActive = false;
            return false;
        }

        dailyAttempt++;
        this.gameDifficulty = newGameDifficulty;
        sessionActive = true;
        return true;
    }

    private boolean canPlay() {
        if(dailyAttempt > MAX_ATTEMPTS) {
            return false;
        }
        if(lastDatePlayed != null) {
            if(lastDatePlayed.getDayOfYear() != LocalDateTime.now().getDayOfYear()) {
                dailyAttempt = 0;
                return true;
            }
        }

        return true;
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

}
