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
import javax.swing.GroupLayout.*;
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
		final int defaultSize = GroupLayout.DEFAULT_SIZE;
		final int preferredSize = GroupLayout.PREFERRED_SIZE;
		final GroupLayout.Alignment leadingAlignment = GroupLayout.Alignment.LEADING;
		final GroupLayout.Alignment trailingAlignment = GroupLayout.Alignment.TRAILING;
		final short maxValue = Short.MAX_VALUE;
		final LayoutStyle.ComponentPlacement relatedPlacement = LayoutStyle.ComponentPlacement.RELATED;
		final GroupLayout.Alignment baselineAlignment = GroupLayout.Alignment.BASELINE;
		
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
		
		//Sliders:
		
		JSlider volumeSlider = new JSlider();
		
		//TextFields:
		
		JPasswordField passwordTextField = new JPasswordField();
		
		//Panels:
		
		JPanel titlePanel;
		JPanel mainPanel  = new JPanel();
		JPanel backgroundPanel;
		
		//Group Layouts:
		
		GroupLayout overlayPanelLayout = new GroupLayout(overlayPanel);
		GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
		GroupLayout layout = new GroupLayout(getContentPane());
		
		
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

		namesLabel.setText("Names");
		namesLabel.setOpaque(true);
		
		gameDescriptionLabel.setText("Game description");
		gameDescriptionLabel.setOpaque(true);
		
		darkModeLabel.setText("· DARK MODE:");
		
		settingsLabel.setText("SETTINGS");
		
		volumeLabel.setText("· VOLUME:");
		
		usernameLabel.setText("· USERNAME:");
		
		passwordLabel.setText("· PASSWORD:");

		registerLabel.setText("REGISTER"); 

		
		//Code for the Sliders:
		
		volumeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				//Make the slider change the music volume.
			}
		});
		
		
		//Code for the Panels:
		
			//Overlay Panel:
		
		overlayPanel.setVisible(false);
		overlayPanel.setLayout(overlayPanelLayout);
		
		
		overlayPanelLayout.setHorizontalGroup(
				overlayPanelLayout.createParallelGroup(leadingAlignment)
				.addGroup(overlayPanelLayout.createSequentialGroup()
						.addGap(29, 29, 29)
						.addGroup(overlayPanelLayout.createParallelGroup(leadingAlignment)
								.addGroup(overlayPanelLayout.createSequentialGroup()
										.addComponent(volumeLabel, preferredSize, 80, preferredSize)
										.addPreferredGap(relatedPlacement, defaultSize, maxValue)
										.addComponent(volumeSlider, preferredSize, 270, preferredSize))
								.addGroup(overlayPanelLayout.createSequentialGroup()
										.addGroup(overlayPanelLayout.createParallelGroup(leadingAlignment)
												.addComponent(darkModeLabel, preferredSize, 111, preferredSize)
												.addComponent(passwordLabel, preferredSize, 80, preferredSize)
												.addComponent(usernameLabel, preferredSize, 80, preferredSize))
										.addPreferredGap(relatedPlacement)
										.addGroup(overlayPanelLayout.createParallelGroup(leadingAlignment)
												.addComponent(darkModeToggleButton, preferredSize, 45, preferredSize)
												.addComponent(passwordTextField, trailingAlignment, preferredSize, 115, preferredSize)
												.addComponent(usernameField, trailingAlignment, preferredSize, 115, preferredSize))))
						.addGap(31, 31, 31))
				.addGroup(trailingAlignment, overlayPanelLayout.createSequentialGroup()
						.addContainerGap(defaultSize, maxValue)
						.addGroup(overlayPanelLayout.createParallelGroup(leadingAlignment)
								.addComponent(settingsLabel, trailingAlignment)
								.addComponent(registerLabel, trailingAlignment))
						.addGap(172, 172, 172))
				.addGroup(overlayPanelLayout.createSequentialGroup()
						.addGap(132, 132, 132)
						.addComponent(submitButton, preferredSize, 138, preferredSize)
						.addContainerGap(defaultSize, maxValue))
				);
		
		overlayPanelLayout.setVerticalGroup(
				overlayPanelLayout.createParallelGroup(leadingAlignment)
				.addGroup(overlayPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(settingsLabel, preferredSize, 30, preferredSize)
						.addGap(33, 33, 33)
						.addGroup(overlayPanelLayout.createParallelGroup(leadingAlignment, false)
								.addComponent(volumeLabel, defaultSize, defaultSize, maxValue)
								.addComponent(volumeSlider, preferredSize, 45, preferredSize))
						.addGap(15, 15, 15)
						.addGroup(overlayPanelLayout.createParallelGroup(baselineAlignment)
								.addComponent(darkModeLabel, preferredSize, 45, preferredSize)
								.addComponent(darkModeToggleButton, preferredSize, 45, preferredSize))
						.addPreferredGap(relatedPlacement)
						.addComponent(registerLabel, preferredSize, 30, preferredSize)
						.addPreferredGap(relatedPlacement)
						.addGroup(overlayPanelLayout.createParallelGroup(baselineAlignment)
								.addComponent(usernameLabel, defaultSize, 82, maxValue)
								.addComponent(usernameField, preferredSize, defaultSize, preferredSize))
						.addPreferredGap(relatedPlacement)
						.addGroup(overlayPanelLayout.createParallelGroup(baselineAlignment)
								.addComponent(passwordLabel, defaultSize, defaultSize, maxValue)
								.addComponent(passwordTextField, preferredSize, defaultSize, preferredSize))
						.addGap(26, 26, 26)
						.addComponent(submitButton, preferredSize, 42, preferredSize)
						.addGap(43, 43, 43))
				);
		
		
			//Title Panel:

		titlePanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			private BufferedImage titleImage;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					titleImage = ImageIO.read(new File("src/main/resources/UMAGUESSR.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (titleImage != null) {
					BufferedImage filteredImage = filterImageByColor(titleImage, Color.RED);
					g.drawImage(filteredImage, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		
		titlePanel.setPreferredSize(new Dimension(690, 100));
		titlePanel.setOpaque(false);
		
		
		    //Main Panel:
		
		mainPanel.setBackground(mainPanelColor);
		mainPanel.setLayout(mainPanelLayout);
		mainPanel.setOpaque(false);
		
				//Horizontal Layouts:
		
		ParallelGroup horizontalModes = mainPanelLayout.createParallelGroup(trailingAlignment, false)
				.addComponent(normalModeButton, defaultSize, 250, maxValue)
				.addComponent(namesLabel, preferredSize, 250, preferredSize)
				.addComponent(easyModeButton, defaultSize, defaultSize, maxValue)
				.addComponent(hardModeButton, defaultSize, defaultSize, maxValue);
		
		SequentialGroup horizontalScreenBottom = mainPanelLayout.createSequentialGroup()
				.addGroup(horizontalModes)
				.addGap(232, 232, 232)
				.addComponent(gameDescriptionLabel, defaultSize, 284, preferredSize);
		
		ParallelGroup horizontalTitleAndBottom = mainPanelLayout.createParallelGroup(leadingAlignment)
				.addGroup(horizontalScreenBottom)
				.addComponent(titlePanel, preferredSize, 800, preferredSize);
		
		SequentialGroup horizontalFullScreen = mainPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(configurationButton, preferredSize, 50, maxValue)
				.addGap(18, 18, 18)
				.addGroup(horizontalTitleAndBottom)
				.addContainerGap(defaultSize, maxValue);
		
		mainPanelLayout.setHorizontalGroup(horizontalFullScreen);
		
			        //Vertical Layouts:
		
		SequentialGroup verticalModes = mainPanelLayout.createSequentialGroup()
			.addGap(49, 49, 49)
			.addComponent(easyModeButton, preferredSize, 62, preferredSize)
			.addGap(39, 39, 39)
			.addComponent(normalModeButton, preferredSize, 62, preferredSize)
			.addGap(37, 37, 37)
			.addComponent(hardModeButton, preferredSize, 62, preferredSize)
			.addPreferredGap(relatedPlacement, 49, maxValue)
			.addComponent(namesLabel, preferredSize, 65, preferredSize);
		
		SequentialGroup verticalDescription = mainPanelLayout.createSequentialGroup()
				.addGap(33, 33, 33)
				.addComponent(gameDescriptionLabel, defaultSize, defaultSize, maxValue);
		
		ParallelGroup verticalScreenBottom = mainPanelLayout.createParallelGroup(leadingAlignment)
				.addGroup(verticalModes)
				.addGroup(verticalDescription);
		
		ParallelGroup verticalScreenTop = mainPanelLayout.createParallelGroup(leadingAlignment)
				.addComponent(titlePanel, defaultSize, 132, maxValue)
				.addComponent(configurationButton, preferredSize, 50, preferredSize); 
		
		
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(verticalScreenTop)
						.addGroup(verticalScreenBottom)
						.addGap(37, 37, 37)
				);
			//Background Panel:

		backgroundPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon icon = new ImageIcon("C:\\Users\\pedro\\Pictures\\Saved Pictures\\Star Landing.jpg");
				Image image = icon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		backgroundPanel.setLayout(null);
		backgroundPanel.add(mainPanel);
		
			//Group Layout:	
		
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(leadingAlignment)
				.addComponent(mainPanel, defaultSize, defaultSize, maxValue)
				.addComponent(backgroundPanel)
				.addGroup(layout.createParallelGroup(leadingAlignment)
						.addGroup(trailingAlignment, layout.createSequentialGroup()
								.addGap(0, 478, maxValue)
								.addComponent(overlayPanel, preferredSize, defaultSize, preferredSize)))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(leadingAlignment)
				.addComponent(mainPanel, defaultSize, defaultSize, maxValue)
				.addComponent(backgroundPanel)
				.addGroup(layout.createParallelGroup(leadingAlignment)
						.addGroup(trailingAlignment, layout.createSequentialGroup()
								.addGap(0, 150, 150)
								.addComponent(overlayPanel, preferredSize, defaultSize, preferredSize))
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