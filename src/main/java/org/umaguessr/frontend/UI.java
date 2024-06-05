package org.umaguessr.frontend;

import org.umaguessr.backend.GameService;
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
    GameService gameService;
    ZoomableImagePanel zoomableImagePanel;
    private Image currentImage;

    public UI(ImageService imageService, ScoreService scoreService, GameService gameService) throws IOException, URISyntaxException {
        super();

        this.imageService = imageService;
        this.scoreService = scoreService;
        this.gameService = gameService;

        String imageID = this.imageService.getRandomUnplayedImageId();


        setTitle("UmaGuessr");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.WHITE);


        Image imageData = imageService.getImageData(imageService.getRandomUnplayedImageId());
        zoomableImagePanel = new ZoomableImagePanel(1.2, imageService.readImageFromURL(imageData.getURL()));
        zoomableImagePanel.setSize(new Dimension(800, 600));
        zoomableImagePanel.setVisible(true);

        changeImage(this.imageService.getImageData(imageID));

        HideablePanel hideablePanel = new HideablePanel(imageService.readImageFromURL("https://i.imgur.com/FZm22X0.png"));
        hideablePanel.setSize(new Dimension(800, 200));
        hideablePanel.setVisible(true);
        

        ScorePanel scorePanel = new ScorePanel();
        scorePanel.setSize(new Dimension(75, 30));
        scorePanel.setVisible(true);
        
        JButton signalButton = getSignalButton(scorePanel);
        scorePanel.addSignalButton(signalButton);

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


    private JButton getSignalButton(ScorePanel scorePanel) {
        JButton signalButton = new JButton("Make Guess");
               
       signalButton.addActionListener(e -> {
            Marker previousMarker = Marker.getPreviousMarker();

            Point2D.Double marker = new Point2D.Double(previousMarker.getRealX(), previousMarker.getRealY());
            scoreService.calculateScore(currentImage.getId(), (int) marker.getX(), (int) marker.getY(), gameService.getDailyAttempt());
            System.out.println(previousMarker.getRealX() + " " + previousMarker.getRealY());

            scorePanel.setScore(scoreService.getFinalScore());
            if(gameService.continuePlaying()){
                scorePanel.nextRound();
                Image newImage = imageService.getImageData(imageService.getRandomUnplayedImageId());
                changeImage(newImage);
            }
            else{
                // exit UI back to startingmenu
                this.dispose();
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