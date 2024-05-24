package org.umaguessr.backend;

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
