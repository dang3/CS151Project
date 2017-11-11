import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class GameBoardPanel extends JPanel {
	private Mancala gameBoard;
	
	public GameBoardPanel() {
		gameBoard = new Mancala();
		repaint();
		addMouseListener(new Listener());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gameBoard.draw(g);
	}
	
	
	// Controller
	private class Listener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			Point mousePoint = event.getPoint();
			Ellipse2D.Double pocket = null;
			
			for(Ellipse2D.Double temp : gameBoard.pockets) {
				if(temp.contains(mousePoint)) {
					pocket = temp;
					break;
				}
			}
			
			if(pocket != null) {
				// tells you index of the pocket that was clicked
				System.out.println(gameBoard.pockets.indexOf(pocket));
			}
		}
	}
}
