package org.umaguessr.backend;

/**
 * Image class.
 * 
 * Abstraction of an Image. It has an unique ID and a URL 
 * to send to the frontend.
 * 
 * 
 *  TODO in refactoring: improve code and ADD EXCEPTIONS!!
 * 
 */


public class Image {
	
	private int id;
	private String url;
	private int xCoord;
	private int yCoord;
	
	
	/**
	 * Constructor of the Image class. 
	 * @param newId
	 * @param newURL
	 * @param newX
	 * @param newY
	 */
	public Image(int newId, String newURL, int newX, int newY) {
		this.id = newId;
		this.url = newURL;
		this.xCoord = newX;
		this.yCoord = newY;
	}
	
	/**
	 * Constructor that GSON uses.
	 * @param newId
	 * @param newURL
	 * @param newX
	 * @param newY
	 */
	public Image(String newId, String newURL, String newX, String newY) {
		this(Integer.parseInt(newId), newURL, Integer.parseInt(newX), Integer.parseInt(newY));
	}
	

	
	public void setX(int x) {
		this.xCoord = x;
	}
	
	public void setY(int y ) {
		this.yCoord = y;
	}
	
	public int getXCoord() {
		return this.xCoord;
	}
	
	public int getYCoord() {
		return this.yCoord;
	}
	
	@Override
	public String toString() {
		return "(ID: " + this.id + ", URL: " + this.url + ", X: " + this.xCoord + ", Y: " + this.yCoord + ")";
	}
	
}
