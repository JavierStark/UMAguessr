package org.umaguessr.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ScoreService {

	private int finalScore;
	private final ImageService imageService;
	private String username;

	public static final int MAX_SCORE = 100;
	private static final int MIN_DISTANCE_FOR_MAX_SCORE = 27;
	private static final int MAX_DISTANCE_FOR_ZERO_SCORE = 250;
	private static final double BASE = 1.06;
	private static final double EXPONENT_MULTIPLIER = -0.3;
	private static final double EXPONENT_CONSTANT = 87;

	private static final String JDBC_URL = "jdbc:postgresql://vps.damianverde.es:5432/umaguessr";
	private static final String JDBC_USER = "postgres";
	private static final String JDBC_PASSWORD = "bombardeenlaetsii";

	public ScoreService(ImageService imageService, String username) {
		this.imageService = imageService;
		this.finalScore = 0;
		this.username = username;
	}

	public int calculateScore(String id, int coordX, int coordY) {
		double distance = calculateDistance(id, coordX, coordY);
		int points = calculatePointsBasedOnDistance(distance);
		finalScore += points;
		saveScoreToDB(id, points);
		return points;
	}

	public int getFinalScore() {
		return finalScore;
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

	private void saveScoreToDB(String imageId, int score) {
		String query = "INSERT INTO scores (image_id, score, daily_attempt, username) VALUES (?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			 PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, Integer.parseInt(imageId));
			ps.setInt(2, score);
			ps.setInt(3, 1); // Default daily attempt value for simplicity
			ps.setString(4, this.username);
				ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}
}