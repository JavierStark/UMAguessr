package org.umaguessr.frontend;

import javax.swing.UIManager;

public class MainLoop {
	
	public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
        	public void run() {
                StartingMenu frame = new StartingMenu();
                frame.setVisible(true);
            }
        });
    }
	
}