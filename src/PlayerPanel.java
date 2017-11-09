import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	private JLabel playerLabel = new JLabel();
	private JLabel scoreLabel = new JLabel();
	private int score;
	
	public PlayerPanel(String str) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		playerLabel.setText(str);
		score = 0;
		scoreLabel.setText( "Score: " + Integer.toString(score) );
		add(playerLabel);
		add(scoreLabel);
	}

}
