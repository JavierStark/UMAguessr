package org.umaguessr.frontend;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    JPanel panel = new JPanel();

    public UI() {
        super();

        setTitle("UmaGuessr");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setBackground(java.awt.Color.WHITE);


        HideablePanel hideablePanel = new HideablePanel();
        panel.add(hideablePanel);
        getContentPane().add(panel);

        pack();
        setSize(800, 600);
    }
}
