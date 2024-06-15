package org.umaguessr.frontend;

import org.umaguessr.backend.GameService;
import org.umaguessr.backend.Image;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.ScoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URISyntaxException;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	ImageService imageService;
	ScoreService scoreService;
	GameService gameService;
	ZoomableImagePanel zoomableImagePanel;
	JPanel content;
	private Image currentImage;
	private StartingMenu startingMenu;

	public UI(ImageService imageService, ScoreService scoreService,
			GameService gameService) throws IOException, URISyntaxException {
		super();

		this.imageService = imageService;
		this.scoreService = scoreService;
		this.gameService = gameService;

		String imageID = this.imageService.getRandomUnplayedImageId();


		setTitle("UmaGuessr");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBackground(java.awt.Color.BLACK);


		Image imageData = imageService.getImageData(imageService.getRandomUnplayedImageId());
		zoomableImagePanel = new ZoomableImagePanel(1.2, imageService.readImageFromURL(imageData.getURL()));
		zoomableImagePanel.setSize(new Dimension(800, 600));
		zoomableImagePanel.setVisible(true);

		changeImage(this.imageService.getImageData(imageID));

		HideablePanel hideablePanel = new HideablePanel(imageService.readImageFromURL("https://i.imgur.com/FZm22X0.png"));
		hideablePanel.setSize(new Dimension(800, 200));
		hideablePanel.setVisible(true);


		ScorePanel scorePanel = new ScorePanel(gameService.getDailyAttempt(), gameService.getMaxAttempts());
		scorePanel.setSize(new Dimension(75, 30));
		scorePanel.setVisible(true);

		JButton signalButton = getSignalButton(scorePanel);
		scorePanel.addSignalButton(signalButton);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, zoomableImagePanel, hideablePanel);
		splitPane.setDividerSize(10);
		splitPane.setDividerLocation(450);



		content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(splitPane, BorderLayout.CENTER);
		content.add(scorePanel, BorderLayout.NORTH);

		setContentPane(content);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		setVisible(true);

		//----------------------------------------------------------------------------------------------------------new
		content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escPressed");
		content.getActionMap().put("escPressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				//Press the ESC key to return to main menu.
			}
		});
		//----------------------------------------------------------------------------------------------------------new
		pack();
	}
	
	public UI(ImageService imageService, ScoreService scoreService,
			GameService gameService, StartingMenu startingMenu1) throws IOException, URISyntaxException {
		this(imageService, scoreService, gameService);
		this.startingMenu = startingMenu1;
		content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "escPressed");
		content.getActionMap().put("escPressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				startingMenu.showAgain();
				hideThis();
			}
		});
	}

	private void hideThis() {
		this.setVisible(false);
	}
	
	
	private JButton getSignalButton(ScorePanel scorePanel) {
		JButton signalButton = new JButton("Make Guess");

		signalButton.addActionListener(e -> {
			Point2D.Double marker = new Point2D.Double(Marker.getPreviousMarker().getRealX(), Marker.getPreviousMarker().getRealY());
			scoreService.calculateScore(currentImage.getId(), (int) marker.getX(), (int) marker.getY(), gameService.getDailyAttempt());
			
			scorePanel.setScore(scoreService.getCurrentScore());
			
			if(gameService.continuePlaying()){
				scorePanel.nextRound();
				Image newImage = imageService.getImageData(imageService.getRandomUnplayedImageId());
				changeImage(newImage);
			}else{
				hideThis();
				startingMenu.showAgain();
			}
		});

		signalButton.setVisible(true);
		signalButton.setPreferredSize(new Dimension(150, 50));
		return signalButton;
	}

	private void changeImage(Image image) {
		currentImage = image;
		try {
			zoomableImagePanel.setImage(imageService.readImageFromURL(image.getURL()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}