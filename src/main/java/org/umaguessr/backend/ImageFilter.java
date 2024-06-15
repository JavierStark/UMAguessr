package org.umaguessr.backend;

/**
 * The {@code ImageFilter} interface represents an image filter.
 * Implementations of this interface can be used to check if an image meets certain criteria.
 */
public interface ImageFilter {

	/**
	 * Checks if the given image passes the filter.
	 *
	 * @param image the image to be checked
	 * @return {@code true} if the image passes the filter, {@code false} otherwise
	 */
	boolean check(Image image);
}
