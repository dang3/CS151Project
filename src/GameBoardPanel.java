import java.awt.Graphics;

import javax.swing.JPanel;

public class GameBoardPanel extends JPanel {
	private Mancala gameBoard;
	
	public GameBoardPanel() {
		gameBoard = new Mancala();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gameBoard.paintIcon(this, g, 1, 1);
	}
	
}
