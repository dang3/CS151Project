import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GameBoardPanel extends JPanel implements ChangeListener {
	private Mancala gameBoard;
	private Model model;
	boolean isPlayerA;
	
	public GameBoardPanel(Model model) {
		this.model = model;
		gameBoard = new Mancala();
		repaint();
		addMouseListener(new Listener());
		isPlayerA=true;
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
			
			
			//check if correct player's pits
			int index = gameBoard.pockets.indexOf(pocket);
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
