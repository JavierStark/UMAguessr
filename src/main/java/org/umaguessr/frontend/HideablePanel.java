package org.umaguessr.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class HideablePanel extends JPanel {
    public HideablePanel(){
        super();
        setVisible(true);
        setBackground(java.awt.Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(800, 100));

        JLabel label = new JLabel("This is a hideable panel");
        JButton button = new JButton("Hide me");
        add(label);
        add(button);

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                System.out.println("Mouse entered");
                setSize(new Dimension(800, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Mouse exited");
                setSize(new Dimension(800, 100));
            }
        });
    }
}
