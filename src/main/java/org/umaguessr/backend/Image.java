package org.umaguessr.backend;

public class Image {

	private int id;
	private String url;
	private int x;
	private int y;
	
	public Image() {}
	
	public Image(String z, String newURL, String a, String b) {
		this.id = Integer.parseInt(z);
		this.url = newURL;
		this.x = Integer.parseInt(a);
		this.y = Integer.parseInt(b);
	}
	
	public Image(int z, String newURL, int x, int y) {
		this.id = z;
		this.url = newURL;
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y ) {
		this.y = y;
	}
	
	public int getXCoord() {
		return this.x;
	}
	
	public int getYCoord() {
		return this.y;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + " | URL: " + this.url + " | X: " + this.x + " Y: " + this.y + " //";
	}
	
}
