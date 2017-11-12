import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameBoardPanel extends JPanel implements ChangeListener {
	private Mancala gameBoard;
	private Model model;
	
	public GameBoardPanel(Model model) {
		this.model = model;
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
			
			// tells you index of the pocket that was clicked
			if(pocket != null) {
				// model.updateModel(gameBoard.pockets.indexOf(pocket));
				System.out.println(gameBoard.pockets.indexOf(pocket));
			}
		}
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		// Mancala gameBoard reads from the Model how many seeds per pocket,
		// then the View redraws
		
	}
}
