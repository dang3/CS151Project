import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//import javax.swing.event.*;

public class Mancala extends JPanel{
	int height;
	int width;

	public Mancala(int w, int h) {
		height = h;
		width = w;
	}

	public Mancala(Model model) {

		Icon mIcon = new Icon() {

			public int getIconHeight() {
				return height;
			}

			public int getIconWidth() {
				return width;
			}

			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g;
				int startX = 250;
				int startY = 100;
				Rectangle2D.Double outline = new Rectangle2D.Double(startX, startY, 600, 180);
				Rectangle2D.Double pocket1 = new Rectangle2D.Double(startX + 20, startY + 20, 60, 140);
				Rectangle2D.Double pocket2 = new Rectangle2D.Double(startX + 520, startY + 20, 60, 140);

				int dx = startX + 30;
				int dy = startY + 30;
				for (int i = 0; i < 12; i++) {
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
					g2.draw(pocket);
					dy = startY + 30;
				}
				g2.draw(outline);
				g2.draw(pocket1);
				g2.draw(pocket2);
			}

		};
		add(new JLabel(mIcon));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//pack();
		setVisible(true);
	}

}

/**
 * public Mancala(int w, int h){ height = h; width = w; }
 * 
 * public int getIconHeight() { return height; }
 * 
 * public int getIconWidth() { return width; }
 * 
 * public void paintIcon(Component c, Graphics g, int x, int y) { Graphics2D g2
 * = (Graphics2D) g; int startX = 250; int startY = 100; Rectangle2D.Double
 * outline = new Rectangle2D.Double(startX, startY, 600, 180);
 * Rectangle2D.Double pocket1 = new Rectangle2D.Double(startX + 20, startY + 20,
 * 60, 140); Rectangle2D.Double pocket2 = new Rectangle2D.Double(startX + 520,
 * startY + 20, 60, 140);
 * 
 * int dx = startX+30; int dy = startY+30; for (int i = 0; i < 12; i++){ dx +=
 * 70; if (i == 6){ dx -= 70; dy += 75; } if (i > 6){ dx -= 140; dy += 75; }
 * Ellipse2D.Double pit = new Ellipse2D.Double(dx, dy, 50, 50); g2.draw(pit); dy
 * = startY+30; } g2.draw(outline); g2.draw(pocket1); g2.draw(pocket2); }
 */
