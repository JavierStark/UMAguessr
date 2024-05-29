package org.umaguessr.backend;

public class ScoreService {

	private int finalScore;
	private final ImageService imageService;

	public static final int MAX_SCORE = 100;
	private static final int MIN_DISTANCE_FOR_MAX_SCORE = 27;
	private static final int MAX_DISTANCE_FOR_ZERO_SCORE = 250;
	private static final double BASE = 1.06;
	private static final double EXPONENT_MULTIPLIER = -0.3;
	private static final double EXPONENT_CONSTANT = 87;

	public ScoreService(ImageService imageService) {
		this.imageService = imageService;
		this.finalScore = 0;
	}

	public int calculateScore(String id, int coordX, int coordY) {
		double distance = calculateDistance(id, coordX, coordY);
		int points = calculatePointsBasedOnDistance(distance);
		finalScore += points;
		return points;
	}

	public int getFinalScore() {
		return finalScore;
	}

	private double calculateDistance(String id, int coordX, int coordY) {
		Image image = imageService.getImageData(id);
    
		int differenceX = image.getCoordinates()[0] - coordX;
		int differenceY = image.getCoordinates()[1] - coordY;
		
		return Math.sqrt((double)(differenceX*differenceX) + differenceY*differenceY);
	}
	
	private int calculatePointsBasedOnDistance(double distance) {
		if (distance <= MIN_DISTANCE_FOR_MAX_SCORE)
			return MAX_SCORE;

		if (distance > MAX_DISTANCE_FOR_ZERO_SCORE)
			return 0;

		return (int) Math.ceil(Math.pow(BASE, EXPONENT_MULTIPLIER * distance + EXPONENT_CONSTANT));
	}
	
}