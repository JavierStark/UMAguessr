package org.umaguessr.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.umaguessr.backend.GameService;
import org.umaguessr.backend.ImageService;
import org.umaguessr.backend.ScoreService;

public class StartingMenu extends JFrame {

	private static final long serialVersionUID = 1L;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private final int WIDTH = screenSize.width/2;
	private final int HEIGHT = screenSize.height/2;
	
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel modLabel;
	private JLabel settLabel;
	private JLabel volLabel;
	
	private JPanel jPanel1;
	private JPanel jPanel3;
	private JPanel overlayPanel;
	
	private GameService gameService = new GameService("pokemaniaco");

	private JToggleButton modeToggleButton;
	private JSlider volumeSlider1;


	protected Color mainPanelColor = new Color(255, 255, 255);

	public StartingMenu() {
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		jButton1 = new JButton();
		jPanel3 = new JPanel();
		jButton2 = new JButton();
		jLabel2 = new JLabel();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jLabel3 = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jPanel3 = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			private BufferedImage image1;

            {
                try {
                    image1 = ImageIO.read(new File("src/main/resources/UMAGUESSR.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
			
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image1 != null) {
                    BufferedImage filteredImage = filterImageByColor(image1, Color.RED);
                    g.drawImage(filteredImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
		};
		
		JPanel backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\pedro\\Pictures\\Saved Pictures\\Star Landing.jpg");
                Image image = icon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
		
        jButton1 = new JButton() {
			private static final long serialVersionUID = 1L;
			
			private BufferedImage image2;

            {
                try {
                    image2 = ImageIO.read(new File("src/main/resources/gear.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
			
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image2 != null) {
                    BufferedImage filteredImage = filterImageByColor(image2, Color.BLACK);
                    g.drawImage(filteredImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
		};

		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				overlayPanel.setVisible(!overlayPanel.isVisible());
			}
		});
		
		//----------------------------------------------------------------------------------------------------------Hidden panel

		overlayPanel = new JPanel();
		
		modLabel = new JLabel();
        modeToggleButton = new JToggleButton();
        volLabel = new JLabel();
        volumeSlider1 = new JSlider();
        settLabel = new JLabel();
        
        /**
		 * Settings panel view configuration.
		 */
//		overlayPanel.setBackground(Color.WHITE);
//		overlayPanel.setOpaque(false);
		overlayPanel.setVisible(false);
		
		modLabel.setText("· DARK MODE:");

        modeToggleButton.setText("X");
        modeToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	//Make the toggle button change the screen lightning.
            }
        });

        volLabel.setText("· VOLUME:");

        volumeSlider1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
            	//Make the slider change the music volume.
            }
        });

        settLabel.setText("SETTINGS");

        GroupLayout overlayPanelLayout = new GroupLayout(overlayPanel);
        overlayPanel.setLayout(overlayPanelLayout);
        overlayPanelLayout.setHorizontalGroup(
            overlayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(overlayPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(overlayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(overlayPanelLayout.createSequentialGroup()
                        .addComponent(modLabel, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modeToggleButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 194, GroupLayout.PREFERRED_SIZE))
                    .addGroup(overlayPanelLayout.createSequentialGroup()
                        .addComponent(volLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(volumeSlider1, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
            .addGroup(GroupLayout.Alignment.TRAILING, overlayPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(settLabel)
                .addGap(172, 172, 172))
        );
        overlayPanelLayout.setVerticalGroup(
            overlayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(overlayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(overlayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(volLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(volumeSlider1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(overlayPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(modLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addComponent(modeToggleButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
        );

		//----------------------------------------------------------------------------------------------------------Hidden panel



		//----------------------------------------------------------------------------------------------------------Panel 3

        jPanel3.setPreferredSize(new Dimension(690, 100));
		jPanel3.setOpaque(false);

		//----------------------------------------------------------------------------------------------------------Panel 3



		//----------------------------------------------------------------------------------------------------------Panel 4

		jLabel2.setText("Names");
		//jLabel2.setBackground(mainPanelColor);
		jLabel2.setOpaque(true);

		//----------------------------------------------------------------------------------------------------------Panel 4



		//----------------------------------------------------------------------------------------------------------Panel 5

		jLabel3.setText("Game description");
		//jLabel3.setBackground(mainPanelColor);
		jLabel3.setOpaque(true);

		//----------------------------------------------------------------------------------------------------------Panel 5



		//----------------------------------------------------------------------------------------------------------Panel 1

		jPanel1.setBackground(mainPanelColor );
		//jPanel1.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		jButton2.setText("Easy Mode");
		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameService.startSession(GameService.Difficulty.Easy);
				setVisible(false);
				// open new window with UI
                try {
					ImageService imageService = new ImageService();
                    UI frame = new UI(imageService, new ScoreService(imageService, "hola"));
                    //frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
		});
		jButton4.setText("Normal Mode");
		jButton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameService.startSession(GameService.Difficulty.Medium);
				setVisible(false);
			}
		});
		jButton3.setText("Hard Mode");
		jButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameService.startSession(GameService.Difficulty.Hard);
				setVisible(false);
			}
		});


		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
												.addComponent(jButton4, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
												.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(232, 232, 232)
										.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 284, GroupLayout.PREFERRED_SIZE))
								.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGap(49, 49, 49)
										.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(39, 39, 39)
										.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(37, 37, 37)
										.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
										.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGap(33, 33, 33)
										.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(37, 37, 37))
				);

		//----------------------------------------------------------------------------------------------------------Panel 1

		jPanel1.setOpaque(false);
		backgroundPanel.setLayout(null);
		backgroundPanel.add(jPanel1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(backgroundPanel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(0, 478, Short.MAX_VALUE)
								.addComponent(overlayPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(backgroundPanel)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGap(0, 150, 150)
								.addComponent(overlayPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						)
				);

		pack();
	}
	
	private BufferedImage filterImageByColor(BufferedImage image, Color filterColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                if (isColorMatch(new Color(pixel, true), filterColor)) {
                    filteredImage.setRGB(x, y, pixel);
                } else {
                    filteredImage.setRGB(x, y, 0x00FFFFFF);
                }
            }
        }
        return filteredImage;
    }

    private boolean isColorMatch(Color pixelColor, Color filterColor) {
        int tolerance = 50;
        return Math.abs(pixelColor.getRed() - filterColor.getRed()) <= tolerance &&
               Math.abs(pixelColor.getGreen() - filterColor.getGreen()) <= tolerance &&
               Math.abs(pixelColor.getBlue() - filterColor.getBlue()) <= tolerance;
    }
	
}