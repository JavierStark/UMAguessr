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
    ZoomableImagePanel zoomableImagePanel;

    public UI(ImageService imageService, ScoreService scoreService) throws IOException, URISyntaxException {
        super();

        this.imageService = imageService;
        this.scoreService = scoreService;

        String imageID = this.imageService.getRandomUnplayedImageId();
        Image image = this.imageService.getImageData(imageID);

        setTitle("UmaGuessr");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.WHITE);

        zoomableImagePanel = new ZoomableImagePanel(1.2, imageService.readImageFromURL(image.getURL()));
        zoomableImagePanel.setSize(new Dimension(800, 600));
        zoomableImagePanel.setVisible(true);

        HideablePanel hideablePanel = new HideablePanel(imageService.readImageFromURL("https://i.imgur.com/FZm22X0.png"));
        hideablePanel.setSize(new Dimension(800, 200));
        hideablePanel.setVisible(true);

        ScorePanel scorePanel = new ScorePanel();
        scorePanel.setSize(new Dimension(75, 30));
        scorePanel.setVisible(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, zoomableImagePanel, hideablePanel);
        splitPane.setDividerSize(10);
        splitPane.setDividerLocation(200);


        JButton signalButton = getSignalButton(imageID, scorePanel);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(splitPane, BorderLayout.CENTER);
        content.add(signalButton, BorderLayout.SOUTH);
        content.add(scorePanel, BorderLayout.NORTH);

        setContentPane(content);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setVisible(true);
    }

    private JButton getSignalButton(String imageID, ScorePanel scorePanel) {
        JButton signalButton = new JButton("Send Signal");

        // Add an ActionListener to the button
        signalButton.addActionListener(e -> {
            Marker previousMarker = Marker.getPreviousMarker();
            if (previousMarker.getRealX() >= 0 || previousMarker.getRealY() >= 0) {
                Point2D.Double marker = new Point2D.Double(previousMarker.getRealX(), previousMarker.getRealY());
                System.out.println("Score: ");
                System.out.println(this.scoreService.calculateScore(imageID, (int) marker.getX(), (int) marker.getY()));
                System.out.println(previousMarker.getRealX() + " " + previousMarker.getRealY());
            }
            scorePanel.setScore(this.scoreService.getFinalScore());
            scorePanel.nextRound();
            String imageID2 = this.imageService.getRandomUnplayedImageId();
            Image image2 = this.imageService.getImageData(imageID2);
            changeImage(image2);
        });

        signalButton.setVisible(true);
        signalButton.setPreferredSize(new Dimension(200, 100));
        return signalButton;
    }
    
	private void changeImage(Image image) {
        try {
			zoomableImagePanel.setImage(imageService.readImageFromURL(image.getURL()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}