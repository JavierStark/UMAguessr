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

    private Point lastDragPoint = null;

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

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                double oldScale = scale;
                double minScale = Math.max((double) getWidth() / image.getWidth(null), (double) getHeight() / image.getHeight(null));
                if (e.getPreciseWheelRotation() < 0 && scale <= 8.0) {
                    scale *= scaleMultiplier;  // Zoom in
                } else if (scale > minScale) {
                    scale /= scaleMultiplier;  // Zoom out
                } else {
                    scale = minScale;
                }
                // Translate so that the zoom focuses on the mouse cursor
                double mouseX = e.getX();
                double mouseY = e.getY();
                System.out.println("Mouse X: " + mouseX + " Mouse Y: " + mouseY);
                double inImageCoordX = (mouseX-translateX)/oldScale;
                double inImageCoordY = (mouseY-translateY)/oldScale;
                System.out.println("Real X: " + inImageCoordX + " Real Y: " + inImageCoordY);
                System.out.println("Scale: " + scale);
                translateX = Math.min(0, Math.max(getWidth() - image.getWidth(null) * scale, -inImageCoordX * scale + mouseX));
                translateY = Math.min(0, Math.max(getHeight() - image.getHeight(null) * scale, -inImageCoordY * scale + mouseY));
                System.out.println("Translate X: " + translateX + " Translate Y: " + translateY+"\n");

                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastDragPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lastDragPoint = null;
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (lastDragPoint != null) {
                    Point currentDragPoint = e.getPoint();
                    double deltaX = (currentDragPoint.getX() - lastDragPoint.getX());
                    double deltaY = (currentDragPoint.getY() - lastDragPoint.getY());

                    translateX += deltaX;
                    translateY += deltaY;
                    lastDragPoint = currentDragPoint;
                    repaint();
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateScaleAndTranslation();
			}
		});
    }

    private void updateScaleAndTranslation() {
        if (image != null) {
            double minScale = Math.max((double) getWidth() / image.getWidth(null), (double) getHeight() / image.getHeight(null));
            scale = minScale;
            translateX = 0;
            translateY = 0;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double imageWidth = image.getWidth(null)*scale;
		double imageHeight = image.getHeight(null)*scale;
		if (translateX < getWidth() - imageWidth)
			translateX = getWidth() - imageWidth;
		if (translateY < getHeight() - imageHeight)
			translateY = getHeight() - imageHeight;
		
		// Apply translation and scaling transformation
		if(translateX > 0)
			translateX = 0;

		if (translateY > 0)
			translateY = 0;

        // Apply translation and scaling transformation
        g2d.translate(translateX, translateY);
        g2d.scale(scale, scale);

        // Draw the image
        if (image != null) {
            g2d.drawImage(image, 0, 0, this);
        }

        getParent().repaint();
    }
}