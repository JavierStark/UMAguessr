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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTextField;
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


	private JTextField usernameField = new JTextField();
	private JPanel overlayPanel = new JPanel();


	protected Color mainPanelColor = new Color(255, 255, 255);

	public StartingMenu() {
		initComponents();
	}

	private void initComponents() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//Buttons:
		
		JButton easyModeButton = new JButton();
		JButton normalModeButton = new JButton();
		JButton hardModeButton = new JButton();
		JButton configurationButton;
		JButton submitButton = new JButton();
		JToggleButton darkModeToggleButton = new JToggleButton();
		
		//Labels:
		
		JLabel namesLabel = new JLabel();
		JLabel gameDescriptionLabel = new JLabel();
		JLabel darkModeLabel = new JLabel();
		JLabel settingsLabel = new JLabel();
		JLabel volumeLabel = new JLabel();
		JLabel usernameLabel  = new JLabel();
		JLabel passwordLabel = new JLabel();
		JLabel registerLabel = new JLabel();
		
		//Panels:
		
		JPanel jPanel1  = new JPanel();
		JPanel jPanel2;
		
		//Sliders:
		
		JSlider volumeSlider = new JSlider();
		
		//TextFields:
		
		JPasswordField passwordTextField = new JPasswordField();
		
		
		//Code for the Buttons:
		
			//Easy Mode Button:
		
		easyModeButton.setText("Easy Mode");
		easyModeButton.addActionListener(e -> startGame(GameService.Difficulty.Easy));
		
			//Normal Mode Button:
		
		normalModeButton.setText("Normal Mode");
		normalModeButton.addActionListener(e -> startGame(GameService.Difficulty.Medium));
		
			//Hard Mode Button:
		
		hardModeButton.setText("Hard Mode");
		hardModeButton.addActionListener(e -> startGame(GameService.Difficulty.Hard));
		
			//Configuration Button:
		
		configurationButton = new JButton() {
			private static final long serialVersionUID = 1L;
			
			private BufferedImage gearImage;
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					gearImage = ImageIO.read(new File("src/main/resources/gear.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (gearImage != null) {
					BufferedImage filteredImage = filterImageByColor(gearImage, Color.BLACK);
					g.drawImage(filteredImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
			
		};
		
		configurationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				overlayPanel.setVisible(!overlayPanel.isVisible());
			}
		});
		
			//Submit Button:
		
		submitButton.setText("Submit");
		submitButton.setBounds(150, 100, 100, 30);
		
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = usernameField.getText();
				JOptionPane.showMessageDialog(overlayPanel, "User: " + text);
			}
		});
		
			//Dark Mode Toggle Button:
		
		darkModeToggleButton.setText("X");
		
		darkModeToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//Make the toggle button change the screen lightning.
			}
		});
		
		//Code for the Labels:

		
		settingsLabel.setText("SETTINGS");

		volumeLabel.setText("· VOLUME:");
		
		
		
		jPanel2 = new JPanel() {
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
		
		overlayPanel.setVisible(false);

		

		volumeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				//Make the slider change the music volume.
			}
		});

		darkModeLabel.setText("· DARK MODE:");


		registerLabel.setText("REGISTER"); 

		usernameLabel.setText("· USERNAME:");
		passwordLabel.setText("· PASSWORD:");




		GroupLayout overlayPanelLayout = new GroupLayout(overlayPanel);
		overlayPanel.setLayout(overlayPanelLayout);
		overlayPanelLayout.setHorizontalGroup(
				overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(overlayPanelLayout.createSequentialGroup()
						.addGap(29, 29, 29)
						.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(overlayPanelLayout.createSequentialGroup()
										.addComponent(volumeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(overlayPanelLayout.createSequentialGroup()
										.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(darkModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(darkModeToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(passwordTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(usernameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addGap(31, 31, 31))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, overlayPanelLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(settingsLabel, javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(registerLabel, javax.swing.GroupLayout.Alignment.TRAILING))
						.addGap(172, 172, 172))
				.addGroup(overlayPanelLayout.createSequentialGroup()
						.addGap(132, 132, 132)
						.addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		overlayPanelLayout.setVerticalGroup(
				overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(overlayPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(settingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(33, 33, 33)
						.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(volumeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(15, 15, 15)
						.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(darkModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(darkModeToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(registerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
								.addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(overlayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(26, 26, 26)
						.addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(43, 43, 43))
				);

		//----------------------------------------------------------------------------------------------------------Hidden panel



		//----------------------------------------------------------------------------------------------------------Panel 3

		jPanel2.setPreferredSize(new Dimension(690, 100));
		jPanel2.setOpaque(false);

		//----------------------------------------------------------------------------------------------------------Panel 3



		//----------------------------------------------------------------------------------------------------------Panel 4

		namesLabel.setText("Names");
		//namesLabel.setBackground(mainPanelColor);
		namesLabel.setOpaque(true);

		//----------------------------------------------------------------------------------------------------------Panel 4



		//----------------------------------------------------------------------------------------------------------Panel 5

		gameDescriptionLabel.setText("Game description");
		//gameDescriptionLabel.setBackground(mainPanelColor);
		gameDescriptionLabel.setOpaque(true);

		//----------------------------------------------------------------------------------------------------------Panel 5



		//----------------------------------------------------------------------------------------------------------Panel 1

		jPanel1.setBackground(mainPanelColor );

		

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(configurationButton, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
						.addGap(18, 18, 18)
						//.addContainerGap(18, 1000)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
												.addComponent(normalModeButton, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
												.addComponent(namesLabel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
												.addComponent(easyModeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(hardModeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(232, 232, 232)
										.addComponent(gameDescriptionLabel, GroupLayout.DEFAULT_SIZE, 284, GroupLayout.PREFERRED_SIZE))
								.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(configurationButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGap(49, 49, 49)
										.addComponent(easyModeButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(39, 39, 39)
										.addComponent(normalModeButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(37, 37, 37)
										.addComponent(hardModeButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
										.addComponent(namesLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGap(33, 33, 33)
										.addComponent(gameDescriptionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
	
	private void startGame(GameService.Difficulty difficulty){
		if(usernameField.getText().isEmpty())
			return;

		GameService gameService = new GameService(usernameField.getText());

		if(!gameService.startSession(difficulty)) {
			return;
		}
		setVisible(false);
		ImageService imageService = new ImageService();
		ScoreService scoreService = new ScoreService(imageService, usernameField.getText());

		try {
			UI frame = new UI(imageService, scoreService, gameService);
			frame.setLocationRelativeTo(null);
		} catch (IOException | URISyntaxException ex) {
			throw new RuntimeException(ex);
		}
	}
}