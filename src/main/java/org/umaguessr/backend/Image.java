package org.umaguessr.backend;

public class Image {

	private int x;
	private int y;
	
	public Image() {}
	
	public Image(String a, String b) {
		this.x = Integer.parseInt(a);
		this.y = Integer.parseInt(b);
	}
	
	public Image(int x, int y) {
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
}
