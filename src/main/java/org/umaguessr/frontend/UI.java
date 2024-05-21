package org.umaguessr.frontend;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    JPanel panel = new JPanel();

    public UI() {
        super();

        setTitle("UmaGuessr");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.WHITE);


        add(new HideablePanel());
        add(new ZoomableImagePanel(1.1));


        setVisible(true);

        pack();
        setSize(800, 600);

    }
}
