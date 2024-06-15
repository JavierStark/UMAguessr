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

    /**
     * Default constructor.
     * @param username Username
     */
    public GameService(String username) {
        this.username = username;
        this.sessionActive = false;
        this.lastDatePlayed = getLastDateByUsername(username);
        this.dailyAttempt = getNumberOfDailyAttemptsByUsername(username);
    }

    /**
     * @param username  Username
     * @return Number of daily attempts that the user has played.
     */
    private int getNumberOfDailyAttemptsByUsername(String username) {

        return DatabaseService.getNumberOfDailyAttemptsByUsername(username);
    }

    /**
     *
     * @param username Username
     * @return Last time that the user has played the game.
     */
    private LocalDateTime getLastDateByUsername(String username) {

        return DatabaseService.getLastDatePlayedByUsername(username);
    }

    /**
     * This class starts a new session of the game.
     * @param newGameDifficulty Desired difficulty of the game.
     * @return True if the session started succesfully. False otherwise.
     */
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

    /**
     * Starts, if possible, a new game.
     * @return True if the game can be started, false otherwise.
     */
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

    /**
     * @return True if the user can play another game, false otherwise.
     */
    private boolean canPlay() {
        return lastDatePlayed == null ||
                dailyAttempt <= MAX_ATTEMPTS ||
                lastDatePlayed.plusDays(1).isBefore(LocalDateTime.now());
    }

}
