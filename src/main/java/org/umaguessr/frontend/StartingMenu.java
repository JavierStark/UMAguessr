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
	private JPanel mainPanel = new JPanel();
	private JPanel overlayPanel = new JPanel();
	
	static final int defaultSize = GroupLayout.DEFAULT_SIZE;
	static final int preferredSize = GroupLayout.PREFERRED_SIZE;
	static final GroupLayout.Alignment leadingAlignment = GroupLayout.Alignment.LEADING;
	static final GroupLayout.Alignment trailingAlignment = GroupLayout.Alignment.TRAILING;
	static final short maxValue = Short.MAX_VALUE;
	static final LayoutStyle.ComponentPlacement relatedPlacement = LayoutStyle.ComponentPlacement.RELATED;
	static final GroupLayout.Alignment baselineAlignment = GroupLayout.Alignment.BASELINE;


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
		
		//Panels:
		
		JPanel titlePanel;
		JPanel backgroundPanel;
		
		//Group Layouts:
		
		GroupLayout overlayPanelLayout = new GroupLayout(overlayPanel);
		GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
		GroupLayout generalLayout = new GroupLayout(getContentPane());
		
		
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
			
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage gearImage = null;
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
		
				//Horizontal Layouts:
		
		SequentialGroup horizontalLowerHalf = generateHorizontalLowerHalf(overlayPanelLayout,
				darkModeToggleButton, darkModeLabel);
		
		SequentialGroup horizontalVolumeControl = generateHorizontalVolumeControl(overlayPanelLayout,
				volumeLabel, volumeSlider);
		
		ParallelGroup horizontalBottomHalf = generateHorizontalBottomHalf(overlayPanelLayout,
				horizontalLowerHalf, horizontalVolumeControl);
		
		ParallelGroup horizontalGeneralSettings = generateHorizontalGeneralSettings(overlayPanelLayout,
				settingsLabel);
		
		SequentialGroup horizontalBottom = generateHorizontalBottom(overlayPanelLayout,
				horizontalBottomHalf);
		
		SequentialGroup horizontalSettings = generateHorizontalSettings(overlayPanelLayout,
				horizontalGeneralSettings);
				
		ParallelGroup horizontalCompleteScreen = generateHorizontalCompleteScreen(overlayPanelLayout,
				horizontalBottom, horizontalSettings);
		
		overlayPanelLayout.setHorizontalGroup(horizontalCompleteScreen);
		
			    //Vertical Layouts:
		
		ParallelGroup verticalVolumeControl = generateVerticalVolumeControl(overlayPanelLayout,
				volumeLabel, volumeSlider);
		
		ParallelGroup verticalDarkMode = generateVerticalDarkMode(overlayPanelLayout,
				darkModeToggleButton, darkModeLabel);
		
		SequentialGroup verticalCompleteScreen =  generateVerticalCompleteScreen(overlayPanelLayout,
				settingsLabel, verticalVolumeControl, verticalDarkMode)
				.addGap(43, 43, 43);
		
		overlayPanelLayout.setVerticalGroup(verticalCompleteScreen);
		
		
			//Title Panel:

		titlePanel = new JPanel() {
			private static final long serialVersionUID = 1L;


			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage titleImage = null;
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
		
		ParallelGroup horizontalModes = generateHorizontalModes(mainPanelLayout, easyModeButton, normalModeButton,
				hardModeButton, namesLabel);
		
		ParallelGroup rightHor = generateRightHorizontal(mainPanelLayout, gameDescriptionLabel, usernameLabel);
		
		SequentialGroup horizontalScreenBottom = generateHorizontalScreenBottom(mainPanelLayout, horizontalModes, rightHor);
		
		ParallelGroup horizontalTitleAndBottom = generateHorizontalTitleAndBottom(mainPanelLayout,
				titlePanel, horizontalScreenBottom);
		
		SequentialGroup horizontalFullScreen = generateHorizontalFullScreen(mainPanelLayout,
				configurationButton, horizontalTitleAndBottom);
		
		mainPanelLayout.setHorizontalGroup(horizontalFullScreen);
		
				//Vertical Layouts:
		
		SequentialGroup verticalModes = generateVerticalModes(mainPanelLayout,
				easyModeButton, normalModeButton, hardModeButton, namesLabel);
		
		SequentialGroup verticalDescription = generateVerticalDescription(mainPanelLayout,
				gameDescriptionLabel, usernameLabel);
		
		ParallelGroup verticalScreenBottom = generateHorizontalBottomHalf(mainPanelLayout,
				verticalDescription, verticalModes);
		
		ParallelGroup verticalScreenTop = generateVerticalScreenTop(mainPanelLayout,
				configurationButton, titlePanel); 
		
		SequentialGroup verticalFullScreen = generateVerticalFullScreen(mainPanelLayout,
				verticalScreenBottom, verticalScreenTop);
		
		mainPanelLayout.setVerticalGroup(verticalFullScreen);
		
		
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
		
			//General Layout:	
		
		getContentPane().setLayout(generalLayout);
		
				//Horizontal Layouts:
		
		SequentialGroup horizontalGeneralOverlay = generateHorizontalGeneralOverlay(generalLayout);
		
		ParallelGroup horizontalGeneralFullScreen = generateHorizontalGeneralFullScreen(generalLayout,
				mainPanel, backgroundPanel, horizontalGeneralOverlay);
		
		generalLayout.setHorizontalGroup(horizontalGeneralFullScreen);
		
		        //Vertical Layouts:
		
		SequentialGroup verticalGeneralOverlay = generateVerticalGeneralOverlay(generalLayout);
		
		ParallelGroup verticalGeneralFullScreen = generateVerticalGeneralFullScreen(generalLayout,
				mainPanel, backgroundPanel, verticalGeneralOverlay);
		
		generalLayout.setVerticalGroup(verticalGeneralFullScreen);

		pack();
	}

	private ParallelGroup generateVerticalGeneralFullScreen(GroupLayout generalLayout, JPanel mainPanel,
			JPanel backgroundPanel, SequentialGroup verticalGeneralOverlay) {
		return generalLayout.createParallelGroup(leadingAlignment)
				.addComponent(mainPanel, defaultSize, defaultSize, maxValue)
				.addComponent(backgroundPanel)
				.addGroup(trailingAlignment,verticalGeneralOverlay);
	}

	private SequentialGroup generateVerticalGeneralOverlay(GroupLayout generalLayout) {
		return generalLayout.createSequentialGroup()
				.addGap(0, 150, 150)
				.addComponent(overlayPanel, preferredSize, defaultSize, preferredSize);
	}

	private ParallelGroup generateHorizontalGeneralFullScreen(GroupLayout generalLayout, JPanel mainPanel,
			JPanel backgroundPanel, SequentialGroup horizontalGeneralOverlay) {
		return generateVerticalGeneralFullScreen(generalLayout, mainPanel, backgroundPanel, horizontalGeneralOverlay);
	}

	private SequentialGroup generateHorizontalGeneralOverlay(GroupLayout generalLayout) {
		return generalLayout.createSequentialGroup()
				.addGap(0, 478, maxValue)
				.addComponent(overlayPanel, preferredSize, defaultSize, preferredSize);
	}

	private BufferedImage filterImageByColor(BufferedImage image, Color filterColor) {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

	    for (int i = 0; i < pixels.length; i++) {
	        Color pixelColor = new Color(pixels[i], true);
	        if (isColorMatch(pixelColor, filterColor)) {
	            pixels[i] = pixelColor.getRGB();
	        } else {
	            pixels[i] = 0x00FFFFFF;
	        }
	    }

	    filteredImage.setRGB(0, 0, width, height, pixels, 0, width);
	    
		return filteredImage;
	}

	private boolean isColorMatch(Color pixelColor, Color filterColor) {
		int tolerance = 50;
		return Math.abs(pixelColor.getRed() - filterColor.getRed()) <= tolerance &&
				Math.abs(pixelColor.getGreen() - filterColor.getGreen()) <= tolerance &&
				Math.abs(pixelColor.getBlue() - filterColor.getBlue()) <= tolerance;
	}
	
	private void startGame(GameService.Difficulty difficulty){
		if(usernameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(mainPanel, "ERROR: \nmust enter username");
			return;
		}

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

	//Verticals
	
	private SequentialGroup generateVerticalCompleteScreen(GroupLayout overlayPanelLayout,
			JLabel settingsLabel, ParallelGroup verticalVolumeControl,
			ParallelGroup verticalDarkMode) {
		return overlayPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(settingsLabel, preferredSize, 30, preferredSize)
				.addGap(33, 33, 33)
				.addGroup(verticalVolumeControl)
				.addGap(15, 15, 15)
				.addGroup(verticalDarkMode)
				.addGap(150, 150, 150);
	}

	private ParallelGroup generateVerticalDarkMode(GroupLayout overlayPanelLayout, JToggleButton darkModeToggleButton,
			JLabel darkModeLabel) {
		return overlayPanelLayout.createParallelGroup(baselineAlignment)
		.addComponent(darkModeLabel, preferredSize, 45, preferredSize)
		.addComponent(darkModeToggleButton, preferredSize, 45, preferredSize);
	}

	private ParallelGroup generateVerticalVolumeControl(GroupLayout overlayPanelLayout, JLabel volumeLabel,
			JSlider volumeSlider) {
		return overlayPanelLayout.createParallelGroup(leadingAlignment, false)
		.addComponent(volumeLabel, defaultSize, defaultSize, maxValue)
		.addComponent(volumeSlider, preferredSize, 45, preferredSize);
	}

	private SequentialGroup generateVerticalFullScreen(GroupLayout mainPanelLayout, ParallelGroup verticalScreenBottom,
			ParallelGroup verticalScreenTop) {
		return mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(verticalScreenTop)
                .addGroup(verticalScreenBottom)
                .addGap(37, 37, 37);
	}

	private ParallelGroup generateVerticalScreenTop(GroupLayout mainPanelLayout, JButton configurationButton,
			JPanel titlePanel) {
		return mainPanelLayout.createParallelGroup(leadingAlignment)
				.addComponent(titlePanel, defaultSize, 132, maxValue)
				.addComponent(configurationButton, preferredSize, 50, preferredSize);
	}

	private SequentialGroup generateVerticalDescription(GroupLayout mainPanelLayout, JLabel gameDescriptionLabel, JLabel usernameLabel) {
		return mainPanelLayout.createSequentialGroup()
				.addGap(33, 33, 33)
				.addComponent(gameDescriptionLabel, defaultSize, defaultSize, maxValue)
				.addPreferredGap(relatedPlacement)
				.addComponent(usernameLabel, defaultSize, 82, maxValue)
				.addComponent(usernameField, preferredSize, defaultSize, preferredSize)
				.addGap(50,50,50);
	}

	private SequentialGroup generateVerticalModes(GroupLayout mainPanelLayout, JButton easyModeButton,
			JButton normalModeButton, JButton hardModeButton, JLabel namesLabel) {
		return mainPanelLayout.createSequentialGroup()
			.addGap(49, 49, 49)
			.addComponent(easyModeButton, preferredSize, 62, preferredSize)
			.addGap(39, 39, 39)
			.addComponent(normalModeButton, preferredSize, 62, preferredSize)
			.addGap(37, 37, 37)
			.addComponent(hardModeButton, preferredSize, 62, preferredSize)
			.addPreferredGap(relatedPlacement, 49, maxValue)
			.addComponent(namesLabel, preferredSize, 65, preferredSize);
	}

	// Horizontals
	
	private ParallelGroup generateHorizontalCompleteScreen(GroupLayout overlayPanelLayout,
			SequentialGroup horizontalBottom, SequentialGroup horizontalSettings) {
		return overlayPanelLayout.createParallelGroup(leadingAlignment)
				.addGroup(trailingAlignment, horizontalBottom)
				.addGroup(horizontalSettings);
	}

	private SequentialGroup generateHorizontalSettings(GroupLayout overlayPanelLayout,
			ParallelGroup horizontalGeneralSettings) {
		return overlayPanelLayout.createSequentialGroup()
		.addContainerGap(defaultSize, maxValue)
		.addGroup(horizontalGeneralSettings)
		.addGap(172, 172, 172);
	}

	private SequentialGroup generateHorizontalBottom(GroupLayout overlayPanelLayout,
			ParallelGroup horizontalBottomHalf) {
		return overlayPanelLayout.createSequentialGroup()
				.addGap(29, 29, 29)
				.addGroup(horizontalBottomHalf)
				.addGap(31, 31, 31);
	}

	private ParallelGroup generateHorizontalGeneralSettings(GroupLayout overlayPanelLayout, JLabel settingsLabel) {
		return overlayPanelLayout.createParallelGroup(leadingAlignment)
				.addComponent(settingsLabel, trailingAlignment);
	}

	private ParallelGroup generateHorizontalBottomHalf(GroupLayout overlayPanelLayout,
			SequentialGroup horizontalLowerHalf, SequentialGroup horizontalVolumeControl) {
		return overlayPanelLayout.createParallelGroup(leadingAlignment)
				.addGroup(horizontalVolumeControl)
				.addGroup(horizontalLowerHalf);
	}

	private SequentialGroup generateHorizontalVolumeControl(GroupLayout overlayPanelLayout, JLabel volumeLabel,
			JSlider volumeSlider) {
		return overlayPanelLayout.createSequentialGroup()
				.addComponent(volumeLabel, preferredSize, 80, preferredSize)
				.addPreferredGap(relatedPlacement, defaultSize, maxValue)
				.addComponent(volumeSlider, preferredSize, 270, preferredSize);
	}

	private SequentialGroup generateHorizontalLowerHalf(GroupLayout overlayPanelLayout, JToggleButton darkModeToggleButton, JLabel darkModeLabel) {
		return overlayPanelLayout.createSequentialGroup()
				.addComponent(darkModeLabel, preferredSize, 111, preferredSize)
				.addPreferredGap(relatedPlacement)
				.addComponent(darkModeToggleButton, preferredSize, 45, preferredSize);
	}

	// MainPanel methods
	
	private SequentialGroup generateHorizontalFullScreen(GroupLayout mainPanelLayout, JButton configurationButton,
			ParallelGroup horizontalTitleAndBottom) {
		return mainPanelLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(configurationButton, preferredSize, 50, maxValue)
				.addGap(18, 18, 18)
				.addGroup(horizontalTitleAndBottom)
				.addContainerGap(defaultSize, maxValue);
	}

	private ParallelGroup generateHorizontalTitleAndBottom(GroupLayout mainPanelLayout, JPanel titlePanel,
			SequentialGroup horizontalScreenBottom) {
		return mainPanelLayout.createParallelGroup(leadingAlignment)
				.addGroup(horizontalScreenBottom)
				.addComponent(titlePanel, preferredSize, 800, preferredSize);
	}

	private SequentialGroup generateHorizontalScreenBottom(GroupLayout mainPanelLayout,	ParallelGroup horizontalModes, ParallelGroup rightHor) {
		return mainPanelLayout.createSequentialGroup()
				.addGroup(horizontalModes)
				.addGap(232, 232, 232)
				.addGroup(rightHor);
				
	}

	private ParallelGroup generateHorizontalModes(GroupLayout mainPanelLayout, JButton easyModeButton, JButton normalModeButton,
			JButton hardModeButton, JLabel namesLabel) {
		return mainPanelLayout.createParallelGroup(trailingAlignment, false)
				.addComponent(normalModeButton, defaultSize, 250, maxValue)
				.addComponent(namesLabel, preferredSize, 250, preferredSize)
				.addComponent(easyModeButton, defaultSize, defaultSize, maxValue)
				.addComponent(hardModeButton, defaultSize, defaultSize, maxValue);
	}
	
	private ParallelGroup generateRightHorizontal(GroupLayout mainPanelLayout, JLabel gameDescriptionLabel, JLabel usernameLabel) {
		return mainPanelLayout.createParallelGroup(trailingAlignment, false)
				.addComponent(gameDescriptionLabel, defaultSize, 284, preferredSize)
				.addComponent(usernameLabel, defaultSize, 82, maxValue)
				.addComponent(usernameField, preferredSize, 115, preferredSize);
	}

}