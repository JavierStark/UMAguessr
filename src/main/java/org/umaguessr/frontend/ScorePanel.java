package org.umaguessr.frontend;
import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
	
	private int score;
	private int round;
	private JLabel roundLabel;
	private JLabel scoreLabel;
	
	public ScorePanel() {
		score = 0;
		round = 1;
		
		setLayout(new GridLayout(2, 1));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(Color.WHITE);
		
		roundLabel = new JLabel("Round: " + round, SwingConstants.CENTER);
		roundLabel.setVerticalAlignment(SwingConstants.CENTER);
		scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
		scoreLabel.setVerticalAlignment(SwingConstants.CENTER);

		
		add(roundLabel);
		add(scoreLabel);
	}
	
	public void setScore(int newScore) {
		score = newScore;
		scoreLabel.setText("Score: " + score);
	}
	
	public void nextRound() {
		round ++;
		roundLabel.setText("Round: " + round);
	}
}
