package org.umaguessr.frontend;

import org.umaguessr.backend.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class HideablePanel extends JPanel {
    public HideablePanel(BufferedImage image){
    	super();
    	final int width = 800;
    	final int height = 600;
        setBackground(java.awt.Color.LIGHT_GRAY);
        setSize(new Dimension(width, height-100));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        ZoomableImagePanel zoomableImagePanel = new ZoomableImagePanel(1.2, image);
        zoomableImagePanel.setSize(new Dimension(width, height));
        zoomableImagePanel.setVisible(true);
        zoomableImagePanel.addMarkers();
        add(zoomableImagePanel);
        setVisible(true);
    }
}
