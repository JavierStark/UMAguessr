package org.umaguessr.backend;

public class FilterByDifficulty implements ImageFilter {

	private int desiredDifficulty;
	
	public FilterByDifficulty(int newDesiredDifficulty) {
		this.desiredDifficulty = newDesiredDifficulty;
	}

	@Override
	public boolean check(Image image) {
		return image.getDifficulty() <= desiredDifficulty;
	}

}
