import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaPanel extends JPanel implements ChangeListener {
	ArrayList<Ellipse2D.Double> pockets;
	ArrayList<Pocket> pocket_int;
	int startX = 0; //150
	int startY = 0;	//100
	
	
	private LineBorder border;
	//Rectangle2D.Double mancala1 = new Rectangle2D.Double(startX + 20, startY + 20, 60, 140);
	//Rectangle2D.Double mancala2 = new Rectangle2D.Double(startX + 520, startY + 20, 60, 140);
	Ellipse2D.Double mancala1 = new Ellipse2D.Double(startX + 20, startY + 20, 60, 140);
	Ellipse2D.Double mancala2 = new Ellipse2D.Double(startX + 520, startY + 20, 60, 140);
	//create pocket class
	//change mancala class
	private Model model;
	boolean isPlayerA;
	
	public MancalaPanel(Model model) {
		this.model = model;
		repaint();
		addMouseListener(new Listener());
		isPlayerA=true;
		pockets = new ArrayList<>();
		pocket_int = new ArrayList<>();
		setSize(600,180);
		border = new LineBorder(Color.BLACK, 3);
		setBorder(border);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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
	}
	
	
	// Controller
	private class Listener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			Point mousePoint = event.getPoint();
			Ellipse2D.Double pocket = null;
			
			System.out.println("Width: " + getWidth());
			System.out.println("height: " + getHeight());
			
			for(Ellipse2D.Double temp : pockets) {
				if(temp.contains(mousePoint)) {
					pocket = temp;
					break;
				}
			}
			
			// tells you index of the pocket that was clicked
			if(pocket != null) {
				// model.updateModel(gameBoard.pockets.indexOf(pocket));
				System.out.println(pockets.indexOf(pocket));
			}

			
			//check if correct player's pits
			int index = pockets.indexOf(pocket);
			if (isPlayerA) {
				if (index>5) {
					System.out.println("You are Player A, please choose pits on your side. ");
					//exit the method? want to skip actual update part
				}
			}
			else{
				if (index<7 || index>12){
					System.out.println("You are Player B, please choose pits on your side. ");
					//exit the method
				}
			}
			
			
			//for loop until #of stones runs out
			int nextPitIndex = index + 1;
			int stoneNumber = model.getStoneNumber(index);
			for (; stoneNumber>0; stoneNumber-- ) {//get method for stoneNumber in that pit?
				
				if (nextPitIndex==14) { //back to beginning of loop
					nextPitIndex=0;
				}
				else if(nextPitIndex==6){ 
					if (isPlayerA) {
						model.updateModel(nextPitIndex);
						if (stoneNumber==1) { //last stone
							//free turn
							//keep status the same for isPlayerA and in undo methods later
							//update and 
						}
					}
					else { //skip because player B
						stoneNumber=stoneNumber+1; //add stone back to counter?
					}
				}
				else if (nextPitIndex==13) {
					if (!isPlayerA) {
						model.updateModel(nextPitIndex);
						if (stoneNumber==1) { //last stone
							//free turn case again
						}
					}
					else { //skip
						stoneNumber=stoneNumber+1;
					}
				}
				else {
					if (stoneNumber==1) { //last stone
						//dont updateModel yet
						int across = 12-nextPitIndex;
						int value;
						if (isPlayerA && model.isSideA(nextPitIndex)) {
							value = model.getStoneNumber(across); //get other side 
							model.toZero(across); //toZero other side
							value = value + 1 + model.getPlayerAMancala();
							model.mancalaNewValue(true, value);
							}
						else if ((!isPlayerA) && (!model.isSideA(nextPitIndex))) {
							value = model.getStoneNumber(across); //get other side 
							model.toZero(across); //toZero other side
							value = value + 1 + model.getPlayerBMancala();
							model.mancalaNewValue(false, value);
						}
					}
					else {
						model.updateModel(nextPitIndex);
					}
				}
				nextPitIndex++;
			}
			
			//check if all pits on a side are empty 
			if (model.sideAEmpty())  {
				model.sideBIntoB();
				//end?
			}
			else if (model.sideBEmpty()) {
				model.sideAIntoA();
				//end
			}		
			
			//change player at end
			isPlayerA = !isPlayerA;
		}
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		// Mancala gameBoard reads from the Model how many seeds per pocket,
		// then the View redraws
		
		for (int i = 0; i<14; i++) {
			model.getStoneNumber(i); //connect to Mancala? for drawing how many stones
		}
	}
}
