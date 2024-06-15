package org.umaguessr.backend;

/**
 * This class is an auxiliary filter that selects
 * all images that are from the selected Faculty.
 */
public class FilterByFaculty implements ImageFilter {

	private String desiredFaculty;
	
	public FilterByFaculty(String newDesiredFaculty) {
		this.desiredFaculty = newDesiredFaculty;
	}
	
	@Override
	public boolean check(Image image) {
		return (image.getFaculty().equalsIgnoreCase(desiredFaculty));
	}

}
