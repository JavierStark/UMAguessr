package org.umaguessr.backend;

public class FilterByDifficulty implements ImageFilter {

	private int desiredDifficulty;

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
