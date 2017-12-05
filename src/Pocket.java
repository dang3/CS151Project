import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Pocket {
	private int numStones;
	private int xPos;
	private int yPos;
	private int index;
	private int sizeX;
	private int sizeY;
	private Ellipse2D.Double outline;
	Color color;

	// this constructor is called when creating new mancala
	public Pocket(int index, int xPos, int yPos, int sizeX, int sizeY) {
		this.index = index;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		numStones = 0;
	}

	// called when creating regular pocket
	public Pocket(int index, int xPos, int yPos, int size) {
		this(index, xPos, yPos, size, size);
		numStones = 3;
	}

	public void draw(Graphics g, Color c) {
		Graphics2D g2 = (Graphics2D) g;
		outline = new Ellipse2D.Double(xPos, yPos, sizeX, sizeY); // draw
																	// outline
																	// of pocket
		g2.setColor(c);
		color = c;
		g2.draw(outline);
		drawStones(g2);
	}

	private void drawStones(Graphics2D g2) {
		// draw number of stones in each pocket
		
		color = setPitColor();
		g2.setColor(color);
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

	public Ellipse2D.Double getOutline() {
		return outline;
	}

	public void setNumStones(int val) {
		numStones = val;
	}

	public int getNumStones() {
		return numStones;
	}

	public Color setPitColor() {
		if (color == Color.black){
			color = Color.blue;
		}
		else color = Color.black;
		return color;
	}

	/** IGNORE THIS **/
	// //methods to implement
	// public void draw(){
	//
	// }
	//
	// public boolean contains(){
	//
	// }
	//
	// public boolean isSelected(){
	//
	// }
	//
	// // ??????
	// public void setSelected(){
	//
	// }
	//
	// public void drawSelected(){
	//
	// }

}
