package org.umaguessr.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.WindowConstants;
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
	private JButton jButton5;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private GameService gameService = new GameService("pokemaniaco");

	private JPanel overlayPanel;


	protected Color mainPanelColor = new Color(255, 255, 255);

	public StartingMenu() {
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jButton1 = new JButton();
		jPanel3 = new JPanel();
		jLabel1 = new JLabel();
		jButton2 = new JButton();
		jPanel4 = new JPanel();
		jLabel2 = new JLabel();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jPanel5 = new JPanel();
		jLabel3 = new JLabel();
		jButton5 = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);

		Image image1 = null;
		Image image2 = null;
		Image cross_image = null;
		try {
			image1 = ImageIO.read(new File("src/main/resources/UMAGUESSR.png"));
			image2 = ImageIO.read(new File("src/main/resources/gear.png"));
			cross_image = ImageIO.read(new File("src/main/resources/cross.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}


		//----------------------------------------------------------------------------------------------------------Panel 2

		jPanel2.setPreferredSize(new Dimension(50, 50));
		jPanel2.setBackground(Color.WHITE);

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);

		jPanel2Layout.setHorizontalGroup( //Horizontal group
				jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jButton1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
				);

		jPanel2Layout.setVerticalGroup( //Vertical group
				jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
				);

		jButton1.setIcon(new ImageIcon(image2));

		//----------------------------------------------------------------------------------------------------------Panel 2



		//----------------------------------------------------------------------------------------------------------Hidden panel

		overlayPanel = new JPanel();
		overlayPanel.setBackground(Color.BLACK);
		//overlayPanel.setPreferredSize(new Dimension(400, 300));
		overlayPanel.setVisible(false);

		jButton5.setVisible(false);

		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				overlayPanel.setVisible(!overlayPanel.isVisible());
				jButton1.setVisible(!jButton1.isVisible());
				jButton2.setVisible(!jButton2.isVisible());
				jButton3.setVisible(!jButton3.isVisible());
				jButton4.setVisible(!jButton4.isVisible());
				jButton5.setVisible(!jButton4.isVisible());
			}
		});

		jButton5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				overlayPanel.setVisible(!overlayPanel.isVisible());
				jButton1.setVisible(!jButton1.isVisible());
				jButton2.setVisible(!jButton2.isVisible());
				jButton3.setVisible(!jButton3.isVisible());
				jButton4.setVisible(!jButton4.isVisible());
				jButton5.setVisible(!jButton4.isVisible());
			}
		});

		GroupLayout overlayPanelLayout = new GroupLayout(overlayPanel);
		jPanel3.setLayout(overlayPanelLayout);
		overlayPanelLayout.setHorizontalGroup( //Horizontal group
				overlayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jButton5, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
				);
		overlayPanelLayout.setVerticalGroup( //Vertical group
				overlayPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
				);

		jButton5.setIcon(new ImageIcon(cross_image));

		//----------------------------------------------------------------------------------------------------------Hidden panel



		//----------------------------------------------------------------------------------------------------------Panel 3

		jPanel3.setBackground(new Color(255, 102, 102));
		jPanel3.setPreferredSize(new Dimension(690, 100));

		jLabel1.setIcon(new ImageIcon(image1));
		jPanel3.add(jLabel1);

		GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup( //Horizontal group
				jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, GroupLayout.Alignment.TRAILING)
				);
		jPanel3Layout.setVerticalGroup( //Vertical group
				jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel1, GroupLayout.Alignment.TRAILING)
				);

		//----------------------------------------------------------------------------------------------------------Panel 3



		//----------------------------------------------------------------------------------------------------------Panel 4

		jLabel2.setText("jLabel2");

		GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup( //Horizontal group
				jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
				);
		jPanel4Layout.setVerticalGroup( //Vertical group
				jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
				);

		//----------------------------------------------------------------------------------------------------------Panel 4



		//----------------------------------------------------------------------------------------------------------Panel 5

		jLabel3.setText("jLabel3");

		GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup( //Horizontal group
				jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
				);
		jPanel5Layout.setVerticalGroup( //Vertical group
				jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

		//----------------------------------------------------------------------------------------------------------Panel 5



		//----------------------------------------------------------------------------------------------------------Panel 1

		jPanel1.setBackground(mainPanelColor );
		jPanel1.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		jButton2.setText("Easy Mode");
		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(GameService.Difficulty.Easy);
			}
		});
		jButton4.setText("Normal Mode");
		jButton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(GameService.Difficulty.Medium);
			}
		});
		jButton3.setText("Hard Mode");
		jButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame(GameService.Difficulty.Hard);
			}
		});


		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
												.addComponent(jButton4, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
												.addComponent(jPanel4, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(232, 232, 232)
										.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGap(49, 49, 49)
										.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(39, 39, 39)
										.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addGap(37, 37, 37)
										.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
										.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGap(33, 33, 33)
										.addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGap(37, 37, 37))
				);

		//----------------------------------------------------------------------------------------------------------Panel 1



		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(overlayPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(overlayPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);

		pack();
	}

	private void startGame(GameService.Difficulty difficulty){
		if(!gameService.startSession(difficulty))
			return;
		setVisible(false);
		ImageService imageService = new ImageService();
		ScoreService scoreService = new ScoreService(imageService, "hola");

		try {
			new UI(imageService, scoreService);
		} catch (IOException | URISyntaxException ex) {
			throw new RuntimeException(ex);
		}
	}
}