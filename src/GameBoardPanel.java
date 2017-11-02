import java.awt.Graphics;

import javax.swing.JPanel;

public class GameBoardPanel extends JPanel {
	private MancalaBoard board = new MancalaBoard();
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.draw(g);
	
	}
	
}
