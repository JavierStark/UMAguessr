package org.umaguessr.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HideablePanel extends JPanel {
    public HideablePanel(){
        super();
        setBackground(java.awt.Color.LIGHT_GRAY);
        setSize(new Dimension(800, 500));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        ZoomableImagePanel zoomableImagePanel = new ZoomableImagePanel(1.2);
        zoomableImagePanel.setSize(new Dimension(800, 600));
        zoomableImagePanel.setVisible(true);
        zoomableImagePanel.addMarkers();
        add(zoomableImagePanel);
        setVisible(true);
    }
}
