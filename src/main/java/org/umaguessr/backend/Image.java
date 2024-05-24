package org.umaguessr.backend;

public class Image {

    private String imageId;
    private String imageURL;
    private int posX;
    private int posY;
    private String faculty;
    private int difficulty;

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

    public String getId() {
        return this.imageId;
    }

    public String getURL() {
        return this.imageURL;
    }

    public int[] getCoordinates() {
        return new int[]{this.posX, this.posY};
    }
    
    public String getFaculty() {
    	return this.faculty;
    }
    
    public int getDifficulty() {
    	return this.difficulty;
    }
    
}