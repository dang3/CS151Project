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
	Rectangle2D.Double pocket1 = new Rectangle2D.Double(startX + 20, startY + 20, 60, 140);
	Rectangle2D.Double pocket2 = new Rectangle2D.Double(startX + 520, startY + 20, 60, 140);
	//create pocket class
	//change mancala class
	
	public Mancala(){
		pockets = new ArrayList<>();
		pocket_int = new ArrayList<>();
	}

	public void draw(Graphics g){
		//int index = 0;
		Graphics2D g2 = (Graphics2D) g;
		int dx = startX + 30;
		int dy = startY + 30;
		for (int i = 0; i < 12; i++) {
			Pocket p = new Pocket(i);
			pocket_int.add(p);
			dx += 70;
			if (i == 6) {
				dx -= 70;
				dy += 75;
			}
			if (i > 6) {
				dx -= 140;
				dy += 75;
			}
			
			Ellipse2D.Double pocket = new Ellipse2D.Double(dx, dy, 50, 50);
			pockets.add(pocket);
			dy = startY + 30;
		}
		for (int i = 0; i < pockets.size(); i++){
			g2.draw(pockets.get(i));
		}
		g2.draw(outline);
		g2.draw(pocket1);
		g2.draw(pocket2);
		
		System.out.println(pocket_int.size());
		
	}
}
	
	
