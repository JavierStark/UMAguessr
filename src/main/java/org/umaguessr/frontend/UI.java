package org.umaguessr.frontend;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    public UI() {
        super();

        setTitle("UmaGuessr");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.WHITE);


        ZoomableImagePanel zoomableImagePanel = new ZoomableImagePanel(1.2);
        zoomableImagePanel.setSize(new Dimension(800, 600));
        zoomableImagePanel.setVisible(true);

        HideablePanel hideablePanel = new HideablePanel();
        hideablePanel.setSize(new Dimension(800, 200));
        hideablePanel.setVisible(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, zoomableImagePanel, hideablePanel);
        splitPane.setDividerSize(10);

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(splitPane, BorderLayout.CENTER);

        setContentPane(content);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setVisible(true);
    }
}
