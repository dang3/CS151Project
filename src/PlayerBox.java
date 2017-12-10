
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

/**
 * Panel/Box that displays Players
 * Dennis Dang, Vera Wong, Marissa Xiong
 * Ver 1.1
 * 12/9/2017
 */

public class PlayerBox extends JPanel {
	private JLabel playerLabel;
	private JLabel scoreLabel;
	private LineBorder border;
	private Color defaultColor;
	
	/**
	 * constructs the player box
	 * @param str string to put into player box
	 */
	public PlayerBox(String str) {
		initPanel();
		initComponents(str);
		placeComponents();
	}
	
	/**
	 * aligns the text into the player box label
	 */
	private void placeComponents() {
		playerLabel.setFont( new Font("Arial", Font.BOLD, 25) );
		scoreLabel.setFont( new Font("Arial", Font.PLAIN, 15) );
		playerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		scoreLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(playerLabel);
		add(scoreLabel);
	}
	
	/**
	 * initializes the panel
	 */
	private void initPanel() {
		setSize(120,70);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		defaultColor = getBackground();
	}
	
	/**
	 * initializes the components of the box (string)
	 * @param str string to be put into the box
	 */
	private void initComponents(String str) {
		playerLabel = new JLabel();
		scoreLabel = new JLabel();
		playerLabel.setText(str);
		scoreLabel.setText("Score: 0");
		border = new LineBorder(Color.BLACK, 2);
		setBorder(border);
	}
	
	/**
	 * sets the color of panel to yellow if it is player's turn
	 */
	public void startTurn() {
		setBackground(Color.YELLOW);
	}
	/**
	 * changes color back to default color if player's turn is over
	 */
	public void endTurn() {
		setBackground(defaultColor);
	}
	/**
	 * adds or subtracts to the score to be displayed
	 * @param score score to be displayed
	 */
	public void setScore(int score) {
		scoreLabel.setText( "Score: " + Integer.toString(score) );
	}
}
