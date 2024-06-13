package org.umaguessr.backend;

/**
 * Represents an image in the UMAguessr application.
 */
public class Image {

    private final String imageId;
    private final String imageURL;
    private final int posX;
    private final int posY;
    private final String faculty;
    private final int difficulty;

    /**
     * Constructs an Image object with the specified parameters.
     *
     * @param imageId    the unique identifier of the image
     * @param imageURL   the URL of the image
     * @param coords     the coordinates of the image on the map
     * @param faculty    the faculty associated with the image
     * @param difficulty the difficulty level of the image
     * @throws IllegalArgumentException if the imageURL does not start with "https://"
     */
    public Image(String imageId, String imageURL, int[] coords, String faculty, int difficulty) {
        if (!imageURL.startsWith("https://")) {
            throw new IllegalArgumentException("Image URL must start with 'https://'");
        }
        this.imageURL = imageURL;
        this.imageId = imageId;
        this.posX = coords[0];
        this.posY = coords[1];
        this.faculty = faculty;
        this.difficulty = difficulty;
    }

    /**
     * Returns the unique identifier of the image.
     *
     * @return the image identifier
     */
    public String getId() {
        return this.imageId;
    }

    /**
     * Returns the URL of the image.
     *
     * @return the image URL
     */
    public String getURL() {
        return this.imageURL;
    }

    /**
     * Returns the coordinates of the image on the map.
     *
     * @return an array containing the X and Y coordinates of the image
     */
    public int[] getCoordinates() {
        return new int[]{this.posX, this.posY};
    }

    /**
     * Returns the faculty associated with the image.
     *
     * @return the faculty of the image
     */
    public String getFaculty() {
        return this.faculty;
    }

    /**
     * Returns the difficulty level of the image.
     *
     * @return the difficulty level of the image
     */
    public int getDifficulty() {
        return this.difficulty;
    }
}