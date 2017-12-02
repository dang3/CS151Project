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
	
	// this constructor is called when creating new mancala
	public Pocket(int index, int xPos, int yPos, int sizeX, int sizeY){
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
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		outline = new Ellipse2D.Double(xPos, yPos, sizeX,sizeY);	// draw outline of pocket
		g2.setColor(Color.WHITE);
		g2.fill(outline);
		g2.setColor(Color.BLACK);
		g2.draw(outline);
		drawStones(g2);
	}
	
	private void drawStones(Graphics2D g2) {
		// draw number of stones in each pocket
		g2.setColor(Color.RED);
		for(int i = 0; i < numStones; i++) {
			Ellipse2D stone = new Ellipse2D.Double(xPos+10 + (i*10), yPos + sizeY/2, 5,5);
			g2.fill(stone);
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
	
	
	
	/** IGNORE THIS **/
//	//methods to implement
//	public void draw(){
//		
//	}
//	
//	public boolean contains(){
//		
//	}
//	
//	public boolean isSelected(){
//		
//	}
//	
//	// ??????
//	public void setSelected(){
//		
//	}
//	
//	public void drawSelected(){
//		
//	}

}
