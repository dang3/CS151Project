import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaPanel extends JPanel implements ChangeListener {
	ArrayList<Pocket> pocketList;
	int startX = 0; //150
	int startY = 0;	//100
	
	
	private LineBorder border;
	//Rectangle2D.Double mancala1 = new Rectangle2D.Double(startX + 20, startY + 20, 60, 140);
	//Rectangle2D.Double mancala2 = new Rectangle2D.Double(startX + 520, startY + 20, 60, 140);
	Ellipse2D.Double mancala1 = new Ellipse2D.Double();
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
		pocketList = new ArrayList<>();
		setSize(600,180);
		border = new LineBorder(Color.BLACK, 3);
		setBorder(border);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int dx = startX+30;
		int dy = startY+105;
		for (int i = 0; i < 12; i++) {
			dx += 70;
			if (i == 6) {
				dx -= 70;
				dy -= 75;
				// draw first mancala
				pocketList.add(new Pocket(pocketList.size(),startX + 20, startY + 20, 60, 140));
			}
			if (i > 6) {
				dx -= 140;
				dy -= 75;
			}
			Pocket pocket = new Pocket(pocketList.size(), dx, dy, 50);
			pocketList.add(pocket);
			dy = startY + 105;
		}
		// draw second mancala
		pocketList.add(new Pocket(pocketList.size(),startX + 520, startY + 20, 60, 140));
		for (int i = 0; i < pocketList.size(); i++){
			pocketList.get(i).draw(g);
		}
	}
	
	
	// Controller
	private class Listener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			Point mousePoint = event.getPoint();
			Ellipse2D.Double pocket = null;
			
			System.out.println("Width: " + getWidth());
			System.out.println("height: " + getHeight());
			
			for(Pocket p : pocketList) {
				Ellipse2D.Double temp = p.getOutline();
				if(temp.contains(mousePoint)) {
					pocket = temp;
					break;
				}
			}
			
			// tells you index of the pocket that was clicked
			if(pocket != null) {
				// model.updateModel(gameBoard.pockets.indexOf(pocket));
				System.out.println(pocketList.indexOf(pocket));
			}
			if (pocket==null) {
				System.out.println("Please select a pit. ");
			}
			
			//check if correct player's pits
			int index = pocketList.indexOf(pocket);
			if (isPlayerA) {
				if (index>5) {
					System.out.println("You are Player A, please choose pits on your side. ");
					return;//exit the method? want to skip actual update part
				}
			}
			else{
				if (index<7 || index>12){
					System.out.println("You are Player B, please choose pits on your side. ");
					return;//exit the method
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
			model.toZero(index);//set chosen pit to zero stones
			
			//check if all pits on a side are empty 
			if (model.sideAEmpty())  {
				model.sideBIntoB();
				System.exit(0); //end
			}
			else if (model.sideBEmpty()) {
				model.sideAIntoA();
				System.exit(0); //end
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
			int stonesNum = model.getStoneNumber(i);
				Pocket p = pocketList.get(i);
				p.setNumStones(stonesNum);
		}
		repaint();
	}
}
