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

	/**
	 * This method calculates the score that the user gets given a guess.
	 * @param id Id of the current reference image
	 * @param coordX X coordinate of the user's guess
	 * @param coordY Y coordinate of the user's guess
	 * @param dailyAttempt Attempt number of the user
	 * @return Resulting score
	 */
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

	/**
	 * Auxiliary method that calculates the distance between the image and the guess
	 * @param id Current image
	 * @param coordX X coordinate of the guess
	 * @param coordY Y coordinate of the guess
	 * @return Distance between image and guess
	 */
	private double calculateDistance(String id, int coordX, int coordY) {
		System.out.println("Calculating distance for image " + id + " with coordinates (" + coordX + ", " + coordY + ")");
		Image image = imageService.getImageData(id);

		int differenceX = image.getCoordinates()[0] - coordX;
		int differenceY = image.getCoordinates()[1] - coordY;

		return Math.sqrt((double) (differenceX * differenceX) + differenceY * differenceY);
	}

	/**
	 * This auxiliary method calculates the score based on the distance. It is an exponential function
	 * with negative exponent; thus decreasing rapidly the further away the guess is from the actual image.
	 * All parameters of the function can be freely modified and are declared as constants in this class.
	 * @param distance
	 * @return Resulting points.
	 */
	private int calculatePointsBasedOnDistance(double distance) {
		if (distance <= MIN_DISTANCE_FOR_MAX_SCORE)
			return MAX_SCORE;

		if (distance > MAX_DISTANCE_FOR_ZERO_SCORE)
			return 0;

		return (int) Math.ceil(Math.pow(BASE, EXPONENT_MULTIPLIER * distance + EXPONENT_CONSTANT));
	}

	/**
	 * Auxiliary method that uses the class DatabaseService to save the resulting score into the database.
	 * @param imageId Current image
	 * @param score Score got
	 * @param dailyAttempt Daily attempt number
	 */
	private void saveScoreToDB(String imageId, int score, int dailyAttempt) {

		DatabaseService.saveScoreIntoDatabase(imageId, score, dailyAttempt, username);

	}

}