package org.umaguessr.frontend;

import org.umaguessr.backend.GameService;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.ScoreService;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
            ImageService imageService = new ImageService();
            ScoreService scoreService = new ScoreService(imageService, "pokemaniaco");

            try {
                new UI(imageService, scoreService, new GameService("pokemaniaco"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
