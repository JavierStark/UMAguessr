package org.umaguessr.backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.umaguessr.frontend.UI;

public class GameService{

	private ImageService imageService = new ImageService();
	private ScoreService scoreService = new ScoreService(imageService);
	private UI ui;

	private LocalDateTime lastDatePlayed;

	public GameService(LocalDateTime date) {
		try {
			ui = new UI(imageService, scoreService);
			ui.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sessionActive = false;
		this.lastDatePlayed = date;
	}

	public GameService() {
		sessionActive = false;
		try {
			ui = new UI(imageService, scoreService);
			ui.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		ui.setVisible(true);
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
