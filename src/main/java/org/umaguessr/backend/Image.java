package org.umaguessr.backend;

public class Image {

    private String imageId;
    private String imageURL;
    private int posX;
    private int posY;

    public Image(String imageId, String imageURL, int[] coords) {
        if (!imageURL.startsWith("https://")) {
            throw new IllegalArgumentException("Image URL must start with 'https://'");
        }
        this.imageURL = imageURL;

        this.imageId = imageId;

        this.posX = coords[0];
        this.posY = coords[1];
    }

    public String getId() {
        return this.imageId;
    }

    public String getURL() {
        return this.imageURL;
    }

    public int getXCoordinate() {
    	return this.posX;
    }
    
    public int getYCoordinate() {
    	return this.posY;
    }
    
    public int[] getCoordinates() {
        return new int[]{this.posX, this.posY};
    }
  
}
