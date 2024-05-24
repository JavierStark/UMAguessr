package org.umaguessr.frontend;

import org.umaguessr.backend.Image;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.ScoreService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

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

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, zoomableImagePanel, hideablePanel);
		splitPane.setDividerSize(10);
		splitPane.setDividerLocation(200);

		JButton signalButton = new JButton("Send Signal");

		// Add an ActionListener to the button
		signalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Marker.getPreviousMarker().getRealX()<0 && Marker.getPreviousMarker().getRealY()<0) {

				}else {
					//To implement
					//sendData(new Point2D.Double(Marker.getPreviousMarker().getRealX(), Marker.getPreviousMarker().getRealY()));
					System.out.println(new Point2D.Double(Marker.getPreviousMarker().getRealX(), Marker.getPreviousMarker().getRealY()));
				}
			}
		});

		signalButton.setVisible(true);
		signalButton.setPreferredSize(new Dimension(200, 100));

		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		content.add(splitPane, BorderLayout.CENTER);
		content.add(signalButton, BorderLayout.SOUTH);

		setContentPane(content);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(800, 600));
		pack();
		setVisible(true);
	}
}