import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PlayerBox extends JPanel {
	private JLabel playerLabel;
	private JLabel scoreLabel;
	private LineBorder border;
	private Color defaultColor;
	private int score;
	
	public PlayerBox(String str) {
		initPanel();
		initComponents(str);
		placeComponents();
	}
	
	private void placeComponents() {
		playerLabel.setFont( new Font("Arial", Font.BOLD, 25) );
		scoreLabel.setFont( new Font("Arial", Font.PLAIN, 15) );
		playerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		scoreLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(playerLabel);
		add(scoreLabel);
	}
	
	private void initPanel() {
		setSize(120,70);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		defaultColor = getBackground();
	}
	
	private void initComponents(String str) {
		playerLabel = new JLabel();
		scoreLabel = new JLabel();
		playerLabel.setText(str);
		scoreLabel.setText( "Score: " + Integer.toString(score) );
		border = new LineBorder(Color.BLACK, 2);
		score = 0;
		setBorder(border);
	}
	
	// Call this method to change background color to yellow when it is
	// this player's turn
	public void setTurn() {
		if( getBackground().equals(Color.YELLOW) )
			setBackground(defaultColor);
		else
			setBackground(Color.YELLOW);
	}
	
}
