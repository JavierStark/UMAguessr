package org.umaguessr.frontend;

import org.umaguessr.backend.Image;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.ScoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URISyntaxException;

public class UI extends JFrame {
	ImageService imageService;
	ScoreService scoreService;



	public UI(ImageService imageService, ScoreService scoreService) throws IOException, URISyntaxException {
		super();

		this.imageService = imageService;
		this.scoreService = scoreService;

		String imageID = this.imageService.getRandomUnplayedImageId();
		Image image = this.imageService.getImageData(imageID);

		setTitle("UmaGuessr");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBackground(java.awt.Color.WHITE);

		ZoomableImagePanel zoomableImagePanel = new ZoomableImagePanel(1.2, imageService.readImageFromURL(image.getURL()));
		zoomableImagePanel.setSize(new Dimension(800, 600));
		zoomableImagePanel.setVisible(true);

		HideablePanel hideablePanel = new HideablePanel(imageService.readImageFromURL("https://i.imgur.com/FZm22X0.png"));
		hideablePanel.setSize(new Dimension(800, 200));
		hideablePanel.setVisible(true);

		JButton signalButton = getSignalButton(imageID);

		ScorePanel scorePanel = new ScorePanel(signalButton);
		scorePanel.setSize(new Dimension(75, 30));
		scorePanel.setVisible(true);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, zoomableImagePanel, hideablePanel);
		splitPane.setDividerSize(10);
		splitPane.setDividerLocation(450);

		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(splitPane, BorderLayout.CENTER);
		content.add(scorePanel, BorderLayout.NORTH);
		
		setContentPane(content);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		pack();
		setVisible(true);
	}

	private JButton getSignalButton(String imageID) {
		JButton signalButton = new JButton("Make Guess");

		// Add an ActionListener to the button
		signalButton.addActionListener(e -> {
			Marker previousMarker = Marker.getPreviousMarker();
			if (previousMarker.getRealX() >= 0 || previousMarker.getRealY() >= 0) {
				Point2D.Double marker = new Point2D.Double(previousMarker.getRealX(), previousMarker.getRealY());
				System.out.println("Score: ");
				System.out.println(this.scoreService.calculateScore(imageID, (int) marker.getX(), (int) marker.getY()));
				System.out.println(previousMarker.getRealX() + " " + previousMarker.getRealY());
			}
		});

		signalButton.setVisible(true);
		signalButton.setPreferredSize(new Dimension(150, 50));
		return signalButton;
	}


}