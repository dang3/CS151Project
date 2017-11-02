import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class MancalaBoard {
	private Rectangle2D boardOutline;
	
	public MancalaBoard() {
		boardOutline = new Rectangle2D.Double(100,100,50,50);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.draw(boardOutline);
	}
	
}
