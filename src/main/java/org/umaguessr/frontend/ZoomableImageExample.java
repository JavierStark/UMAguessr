package GPTTest;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ZoomableImageExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Zoomable Image Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.add(new ZoomableImagePanel(1.4));
                frame.setVisible(true);
            }
        });
    }
}
