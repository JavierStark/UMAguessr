package org.umaguessr.frontend;
import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int score;
	private int round;
	private final int maxRound;
	private JLabel roundLabel;
	private JLabel scoreLabel;
	
	public ScorePanel(int initialRound, int maxRound) {
		score = 0;
		round = initialRound;
		this.maxRound = maxRound;
		
		setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
				
		roundLabel = new JLabel("Round: " + round + "/" +maxRound, SwingConstants.CENTER);
		roundLabel.setVerticalAlignment(SwingConstants.CENTER);
		scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
		roundLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		roundLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		
		if(StartingMenu.white) {
			setBackground(Color.WHITE);
			roundLabel.setForeground(Color.BLACK);
			scoreLabel.setForeground(Color.BLACK);
		} else {
			setBackground(StartingMenu.myBlack);
			roundLabel.setForeground(Color.WHITE);
			scoreLabel.setForeground(Color.WHITE);
		}
		
		add(roundLabel, BorderLayout.WEST);
		add(scoreLabel, BorderLayout.WEST);
	}

	public void addSignalButton(JButton signalButton) {
		add(signalButton, BorderLayout.EAST);
	}
	
	public void setScore(int newScore) {
		score = newScore;
		scoreLabel.setText("Score: " + score);
	}
	
	public void nextRound() {
		round ++;
		roundLabel.setText("Round: " + round+ "/" + maxRound);
	}
}
