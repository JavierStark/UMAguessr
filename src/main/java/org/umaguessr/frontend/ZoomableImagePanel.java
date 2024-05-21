package org.umaguessr.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ZoomableImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private double scale = 1.0;
    private final double scaleMultiplier = 1.1;
    private Image image;
    private double translateX = 0;
    private double translateY = 0;

    public ZoomableImagePanel(double scaleMultiplier) {
        super();

        try {
            image = ImageIO.read(new File("src/main/java/org/umaguessr/frontend/map.jpg")); // Load your image here
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("X: " + e.getX() + " Y:" + e.getY());
            }
        });

        addMouseWheelListener(e -> {
            double oldScale = scale;
            if (e.getPreciseWheelRotation() < 0) {
                scale *= scaleMultiplier;  // Zoom in
            } else {
                scale /= scaleMultiplier;  // Zoom out
            }
            // Translate so that the zoom focuses on the mouse cursor
            double mouseX = e.getX();
            double mouseY = e.getY();
            System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
            double inImageCoordX = (mouseX-translateX)/oldScale;
            double inImageCoordY = (mouseY-translateY)/oldScale;
            System.out.println("Real X: " + inImageCoordX + " Real Y: " + inImageCoordY);
            System.out.println("Scale: " + scale);
            translateX = (-inImageCoordX*scale+mouseX);
            translateY = (-inImageCoordY*scale+mouseY);
            System.out.println("Translate X: " + translateX + " Translate Y: " + translateY+"\n");

            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        // Apply translation and scaling transformation
        g2d.translate(translateX, translateY);
        g2d.scale(scale, scale);

        // Draw the image
        if (image != null) {
            g2d.drawImage(image, 0, 0, this);
        }
    }
}