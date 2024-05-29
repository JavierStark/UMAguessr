package org.umaguessr.frontend;
import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
	
	private int score;
	private int round;
	private JLabel roundLabel;
	private JLabel scoreLabel;
	private JButton signalButton;
	
	public ScorePanel(JButton signalButton) {
		score = 0;
		round = 1;
		
		setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		setBackground(Color.WHITE);
				
		roundLabel = new JLabel("Round: " + round, SwingConstants.CENTER);
		roundLabel.setVerticalAlignment(SwingConstants.CENTER);
		scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
		roundLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		roundLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		
		add(roundLabel, BorderLayout.WEST);
		add(scoreLabel, BorderLayout.WEST);
		add(signalButton, BorderLayout.EAST);
	}
	
	public void setScore(int newScore) {
		score = newScore;
		scoreLabel.setText("Score: " + score);
	}
	
	public void sumScore(int newScore) {
		score += newScore;
		scoreLabel.setText("Score: " + score);
	}
	
	public void nextRound() {
		round ++;
		roundLabel.setText("Round: " + round);
	}
}
