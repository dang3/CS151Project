import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Frame extends JFrame {
	private final int WIDTH = 1000;
	private final int HEIGHT = 800;
	private GameBoardPanel panel = new GameBoardPanel();
	private PlayerPanel playerPanel1 = new PlayerPanel("Player 1");
	private PlayerPanel playerPanel2 = new PlayerPanel("Player 2");
	
	public Frame() {
		setLayout(new BorderLayout());
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Team Mango - Mancala");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(panel, BorderLayout.CENTER);	// add the game board panel
		add(playerPanel1, BorderLayout.WEST);
		add(playerPanel2, BorderLayout.EAST);
		
		setVisible(true);
	}
}
