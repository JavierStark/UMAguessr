package org.umaguessr.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class HideablePanel extends JPanel {
    public HideablePanel(){
        super();
        setLayout(null);
        setBackground(java.awt.Color.LIGHT_GRAY);

        ZoomableImagePanel zoomableImagePanel = new ZoomableImagePanel(1.1);
        zoomableImagePanel.setBounds(0, 0, 800, 100);
        add(zoomableImagePanel);
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                System.out.println("Mouse entered");
                zoomableImagePanel.setBounds(0, 0, 800, 250);
                setSize(new Dimension(800, 300));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse entered");

                Rectangle r = e.getComponent().getBounds();
                Point p = e.getPoint();

                if (p.x < 0 || p.x >= r.width
                        ||  p.y < 0 || p.y >= r.height) {
                    zoomableImagePanel.setBounds(0, 0, 800, 150);
                    setSize(new Dimension(800, 200));
                }
            }
        });
        setSize(new Dimension(800, 200));
        setVisible(true);
    }
}
