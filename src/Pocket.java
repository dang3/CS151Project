import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


/**
 * Draws pockets and stones to be put into Mancala Panel
 * Dennis Dang, Vera Wong, Marissa Xiong
 * Ver 1.1
 * 12/9/2017
 */

public class Pocket {
	private int numStones;
	private int xPos;
	private int yPos;
	private int index;
	private int sizeX;
	private int sizeY;
	private Ellipse2D.Double outline;
	Color color;

	/**
	 * this constructor is called when creating new mancala
	 * @param index index of the mancala
	 * @param xPos x position of the mancala
	 * @param yPos y position of the mancala
	 * @param sizeX width of mancala
	 * @param sizeY height of mancal
	 */
	public Pocket(int index, int xPos, int yPos, int sizeX, int sizeY) {
		this.index = index;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		numStones = 0;
	}

	/**
	 * this constructor is called when creating regular pocket
	 * @param index index of the pocket
	 * @param xPos x postion of the pocket
	 * @param yPos y position of the pocket
	 * @param size size of the pocket
	 */
	public Pocket(int index, int xPos, int yPos, int size) {
		this(index, xPos, yPos, size, size);
		numStones = 0;
	}

	/**
	 * draw the pocket
	 * @param g graphics
	 * @param c color of the pocket
	 */
	public void draw(Graphics g, Color c) {
		Graphics2D g2 = (Graphics2D) g;
		outline = new Ellipse2D.Double(xPos, yPos, sizeX, sizeY); // draw outline of pocket
		color = c;
		g2.setColor(c);
		g2.draw(outline);
		g2.setColor(stoneColor());
		drawStones(g2);
	}
	/**
	 * draw stones into the pockt
	 * @param g2 graphic
	 */
	private void drawStones(Graphics2D g2) {
		// draw number of stones in each pocket

		int xCount = 0;
		int yCount = 0;
		for (int i = 0; i < numStones; i++) {
			int xCoord = xPos + 10 + (xCount * 10);
			int yCoord = yPos + sizeY / 3 + yCount;
			int length = 5;
			if (!outline.contains(xCoord, yCoord, length, length)) {
				xCount = 0;
				yCount = yCount + 7;
			}
			xCoord = xPos + 10 + (xCount * 10);
			yCoord = yPos + sizeY / 3 + yCount;
			Ellipse2D stone = new Ellipse2D.Double(xCoord, yCoord, length, length);
			
			g2.fill(stone);
			xCount++;
		}
	}

	/**
	 * gets the outline of the pocket or mancala
	 * @return outline of shape
	 */
	public Ellipse2D.Double getOutline() {
		return outline;
	}
	
	/**
	 * sets the number of stones in pocket or mancala
	 * @param val number to set pocket/mancala with
	 */
	public void setNumStones(int val) {
		numStones = val;
	}

	/**
	 * gets the number of stones
	 * @return the number of stones in mancala or pocket
	 */
	public int getNumStones() {
		return numStones;
	}
	
	/**
	 * returns a stone color
	 * @return the color of stone
	 */
	public Color stoneColor() {
		if (color == Color.black){
			color = Color.blue;
		}
		else color = Color.black;
		return color;
	}
}
