package org.umaguessr.frontend;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UI a = new UI();
                try {
                	final String dir = "C:/Users/sebto/Dropbox/Sebastián/Estudios/Software/Final project/SwingTest/Marker.png";
                	BufferedImage image = ImageIO.read(new File(dir));
                	a.setIconImage(image);
				} catch (Exception e) {
					System.out.println("Error: " + e);
				}
                 
            }
        });
    }
}
