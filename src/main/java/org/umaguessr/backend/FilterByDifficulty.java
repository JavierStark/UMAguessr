package org.umaguessr.backend;

public class FilterByDifficulty implements ImageFilter {

	private int desiredDifficulty;

	public static final int EASY_DIFFICULTY = 1;
	public static final int MEDIUM_DIFFICULTY = 2;
	public static final int HARD_DIFFICULTY = 3;


	/**
	 * Constructs a new FilterByDifficulty object with the specified desired difficulty.
	 *
	 * @param newDesiredDifficulty the desired difficulty to filter by
	 */
	public FilterByDifficulty(int newDesiredDifficulty) {
		this.desiredDifficulty = newDesiredDifficulty;
	}

	/**
	 * Checks if the given image's difficulty is less than or equal to the desired difficulty.
	 *
	 * @param image the image to check
	 * @return {@code true} if the image's difficulty is less than or equal to the desired difficulty, {@code false} otherwise
	 */
	@Override
	public boolean check(Image image) {
		return image.getDifficulty() <= desiredDifficulty;
	}

}
