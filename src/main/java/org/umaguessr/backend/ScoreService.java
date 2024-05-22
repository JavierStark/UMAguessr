package org.umaguessr.backend;

public class ScoreService {

	private int finalScore;
	private ImageService imageService;

	
	public static final int MAX_SCORE = 100;
	
	
	public ScoreService(ImageService imageService) {
		this.imageService = imageService;
		finalScore = 0;
	}
	
	
	public int calculateScore(String id, int coordX, int coordY) {
		
		int points;
		double dist = calculateDistance(id, coordX, coordY);
		
		if (dist <= 15) {
			
			points = MAX_SCORE;
			
		} else if (dist > 250) {
			
			points = 0;
			
		} else {
			// 1.06^{-(0.3x - 87)}, being x the distance from the actual coordinate.
			points = (int) Math.ceil(Math.pow(1.06, -(0.3*dist - 87)));
					
		}
		
		finalScore += points;
		return points;
		
	}
	
	public int getFinalScore() {
		return this.finalScore;
	}
	
	
	private double calculateDistance(String id, int coordX, int coordY) {
		Image image = imageService.getImageData(id);
		
		int differenceX = image.getXCoordinate() - coordX;
		int differenceY = image.getYCoordinate() - coordY;
		
		return Math.sqrt((double)(differenceX*differenceX) - differenceY*differenceY);
	}
	
}
