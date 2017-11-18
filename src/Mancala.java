import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Mancala{
	ArrayList<Ellipse2D.Double> pockets;
	ArrayList<Pocket> pocket_int;
	JPanel label = new JPanel();
	int startX = 150;
	int startY = 100;
	Rectangle2D.Double outline = new Rectangle2D.Double(startX, startY, 600, 180);
	//Rectangle2D.Double mancala1 = new Rectangle2D.Double(startX + 20, startY + 20, 60, 140);
	//Rectangle2D.Double mancala2 = new Rectangle2D.Double(startX + 520, startY + 20, 60, 140);
	Ellipse2D.Double mancala1 = new Ellipse2D.Double(startX + 20, startY + 20, 60, 140);
	Ellipse2D.Double mancala2 = new Ellipse2D.Double(startX + 520, startY + 20, 60, 140);
	//create pocket class
	//change mancala class
	
	public Mancala(){
		pockets = new ArrayList<>();
		pocket_int = new ArrayList<>();
	}

	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		int dx = startX+30;
		int dy = startY+105;
		for (int i = 0; i < 12; i++) {
			//Pocket p = new Pocket(i);
			//pocket_int.add(p);
			dx += 70;
			if (i == 6) {
				dx -= 70;
				dy -= 75;
				pockets.add(mancala1);
			}
			if (i > 6) {
				dx -= 140;
				dy -= 75;
			}
			Ellipse2D.Double pocket = new Ellipse2D.Double(dx, dy, 50, 50);
			pockets.add(pocket);
			dy = startY + 105;
		}
		pockets.add(mancala2);
		for (int i = 0; i < pockets.size(); i++){
			g2.draw(pockets.get(i));
		}
		g2.draw(outline);

	}
}
	
	
