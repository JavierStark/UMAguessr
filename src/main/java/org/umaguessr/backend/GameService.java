package org.umaguessr.backend;

import java.time.LocalDateTime;

public class GameService {

    private LocalDateTime lastDatePlayed;

    public GameService(LocalDateTime date) {
        sessionActive = false;
        this.lastDatePlayed = date;
    }
    public GameService() {
        sessionActive = false;
    }



    public enum Difficulty {
        Easy,
        Medium,
        Hard;
    }
    private Difficulty difficulty;

    private boolean sessionActive;
    public void startSession(Difficulty difficulty) {
        // gather data
        if (lastDatePlayed != null && lastDatePlayed.plusDays(1).isAfter(LocalDateTime.now())) {
            sessionActive = false;
            return;
        }
        this.difficulty = difficulty;
        sessionActive = true;
    }

    public void endSession() {
        sessionActive = false;
        //send data
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public LocalDateTime getLastDatePlayed() {
        return lastDatePlayed;
    }
}
