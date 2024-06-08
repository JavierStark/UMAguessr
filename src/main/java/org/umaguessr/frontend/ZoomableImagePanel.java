package org.umaguessr.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ZoomableImagePanel extends JLayeredPane {

	private double scale = 1.0;
	private final double scaleMultiplier;
	private Image image;
	private Point2D.Double translation;
	private Point2D.Double inImageCoord;

	public double getTranslateX() {
		return translation.x;
	}

	public double getTranslateY() {
		return translation.y;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		updateScaleAndTranslation();
	}
	
	private Point lastDragPoint = null;

	public ZoomableImagePanel(double scaleMultiplier, BufferedImage image) {
		super();

		this.scaleMultiplier = scaleMultiplier;

		this.image = image;

        initializeListeners();
	}

	private void initializeListeners() {
		addMouseWheelListener(new ZoomableImageMouseEventListener());
		addMouseListener(new ZoomableImageMouseEventListener());
		addMouseMotionListener(new ZoomableImageMouseEventListener());

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateScaleAndTranslation();
			}
		});
	}


	private void updateScaleAndTranslation() {
		if (image == null) return;

		scale = Math.max(
				(double) getWidth() / image.getWidth(null),
				(double) getHeight() / image.getHeight(null));
		translation = new Point2D.Double(0,0);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double imageWidth = image.getWidth(null)*scale;
		double imageHeight = image.getHeight(null)*scale;
		translation.x = Math.max(translation.x, getWidth() - imageWidth);
		translation.y = Math.max(translation.y, getHeight() - imageHeight);

		// Apply translation and scaling transformation
		translation.x = Math.min(0, Math.max(getWidth() - imageWidth, translation.x));
		translation.y = Math.min(0, Math.max(getHeight() - imageHeight, translation.y));

		// Apply translation and scaling transformation
		g2d.translate(translation.x, translation.y);
		g2d.scale(scale, scale);

		// Draw the image
		if (image != null) {
			g2d.drawImage(image, 0, 0, this);
		}

		getParent().repaint();
	}

	private class ZoomableImageMouseEventListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			inImageCoord = getRealPoint(e, scale);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			lastDragPoint = e.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			lastDragPoint = null;
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (lastDragPoint == null) return;

			Point currentDragPoint = e.getPoint();
			double deltaX = (currentDragPoint.getX() - lastDragPoint.getX());
			double deltaY = (currentDragPoint.getY() - lastDragPoint.getY());

			translation.x += deltaX;
			translation.y += deltaY;
			lastDragPoint = currentDragPoint;
			repaint();
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			double oldScale = scale;
			double minScale = Math.max(
					(double) ZoomableImagePanel.this.getWidth() / image.getWidth(null),
					(double) ZoomableImagePanel.this.getHeight() / image.getHeight(null));

			if (e.getUnitsToScroll() < 0 && scale <= 8.0) {
				scale *= scaleMultiplier;  // Zoom in
			} else if (scale > minScale) {
				scale /= scaleMultiplier;  // Zoom out
			} else {
				scale = minScale;
			}

			inImageCoord = getRealPoint(e, oldScale);
			translation.x = Math.min(0, Math.max(ZoomableImagePanel.this.getWidth() - image.getWidth(null) * scale, -inImageCoord.x * scale + e.getX()));
			translation.y = Math.min(0, Math.max(ZoomableImagePanel.this.getHeight() - image.getHeight(null) * scale, -inImageCoord.y * scale + e.getY()));
			
			ZoomableImagePanel.this.repaint();
		}
	}
	private Point2D.Double getRealPoint(MouseEvent e, double scale) {
		Point2D.Double mouse = new Point2D.Double(e.getX(), e.getY());
		return new Point2D.Double(
				(mouse.getX() -translation.x)/scale,
				(mouse.getY() -translation.y)/scale);
	}

	public void addMarkers() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point2D.Double p = getRealPoint(e, scale);
				remove(Marker.getPreviousMarker());
				Marker marker = new Marker(p.getX(), p.getY());
				add(marker, 0);
				Marker.setPreviousMarker(marker);

				getParent().repaint();
			}

		});
	}
}