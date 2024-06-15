package org.umaguessr.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class handles all score-related tasks. It keeps track
 * of the current score and calculates the resulting points
 * given a guess from the user.
 */
public class ScoreService {

	private int currentScore;
	private final ImageService imageService;
	private String username;

	public static final int MAX_SCORE = 100;
	private static final int MIN_DISTANCE_FOR_MAX_SCORE = 36;
	private static final int MAX_DISTANCE_FOR_ZERO_SCORE = 250;
	private static final double BASE = 1.06;
	private static final double EXPONENT_MULTIPLIER = -0.22;
	private static final double EXPONENT_CONSTANT = 87;

	public ScoreService(ImageService imageService, String username) {
		this.imageService = imageService;
		this.currentScore = 0;
		this.username = username;
	}

	public int calculateScore(String id, int coordX, int coordY, int dailyAttempt) {
		double distance = calculateDistance(id, coordX, coordY);
		int points = calculatePointsBasedOnDistance(distance);
		currentScore += points;
		saveScoreToDB(id, points, dailyAttempt);
		return points;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	private double calculateDistance(String id, int coordX, int coordY) {
		Image image = imageService.getImageData(id);

		int differenceX = image.getCoordinates()[0] - coordX;
		int differenceY = image.getCoordinates()[1] - coordY;

		return Math.sqrt((double) (differenceX * differenceX) + differenceY * differenceY);
	}

	private int calculatePointsBasedOnDistance(double distance) {
		if (distance <= MIN_DISTANCE_FOR_MAX_SCORE)
			return MAX_SCORE;

		if (distance > MAX_DISTANCE_FOR_ZERO_SCORE)
			return 0;

		return (int) Math.ceil(Math.pow(BASE, EXPONENT_MULTIPLIER * distance + EXPONENT_CONSTANT));
	}

	private void saveScoreToDB(String imageId, int score, int dailyAttempt) {

		DatabaseService.saveScoreIntoDatabase(imageId, score, dailyAttempt, username);

	}

	public String getUsername() {
		return username;
	}
}