package org.umaguessr.frontend;

import javax.swing.UIManager;

public class MainLoop {
	
	public static void main(String[] args) {
        try {
        	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        	e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
        	public void run() {
                StartingMenu frame = new StartingMenu();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
	
}