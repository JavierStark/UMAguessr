package org.umaguessr.frontend;

import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.ScoreService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageService imageService = new ImageService();
            ScoreService scoreService = new ScoreService(imageService, null);
            try {
                new UI(imageService, scoreService);
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
