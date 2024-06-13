package org.umaguessr.frontend;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Marker extends JLabel{
	
	private static final long serialVersionUID = 1L;
	
	private double realX;
	private double realY;
	private static Marker previousMarker = new Marker(-100, -100);
	static final String dir = "src/main/resources/marker.png";
	private boolean first=true;
	private Image image;

	public Marker(double realX, double realY) {
		if(first) {
			try {
				image = ImageIO.read(new File(dir)).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
				ImageIcon  i = new ImageIcon(image);
				this.setIcon(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
			first = false;
		}
		this.realX = realX;
		this.realY = realY;
		setVisible(true);
		this.setBounds((int) realX-15, (int) realY-30, 30, 30);
	}
	
	public static Marker getPreviousMarker() {
		return previousMarker;
	}
	
	public static void setPreviousMarker(Marker newPreviousMarker) {
		Marker.previousMarker = newPreviousMarker;
	}
	
	public double getRealX() {
		return realX;
	}
	
	public double getRealY() {
		return realY;
	}

}
