package org.umaguessr.frontend;

import org.umaguessr.backend.Image;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.ScoreService;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    ImageService imageService;
    ScoreService scoreService;
    public UI(ImageService imageService, ScoreService scoreService) {
        super();

        this.imageService = imageService;
        this.scoreService = scoreService;

        Image image = this.imageService.getImageData(this.imageService.getRandomUnplayedImageId());

        setTitle("UmaGuessr");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.WHITE);

        ZoomableImagePanel zoomableImagePanel = new ZoomableImagePanel(1.2, image);
        zoomableImagePanel.setSize(new Dimension(800, 600));
        zoomableImagePanel.setVisible(true);

        HideablePanel hideablePanel = new HideablePanel(image);
        hideablePanel.setSize(new Dimension(800, 200));
        hideablePanel.setVisible(true);
        
        ScorePanel scorePanel = new ScorePanel();
        scorePanel.setSize(new Dimension(75, 30));
        scorePanel.setVisible(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, zoomableImagePanel, hideablePanel);
        splitPane.setDividerSize(10);

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
}
