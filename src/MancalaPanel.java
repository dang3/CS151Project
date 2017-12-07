import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaPanel extends JPanel implements ChangeListener {
	private ArrayList<Pocket> pocketList;
	int startX = 0; //150
	int startY = 0;	//100
	
	Color cBoard;
	Color cPocket;
	private LineBorder border;
	Ellipse2D.Double mancala1 = new Ellipse2D.Double();
	Ellipse2D.Double mancala2 = new Ellipse2D.Double(startX + 520, startY + 20, 60, 140);
	//create pocket class
	//change mancala class
	private Model model;
	
	public MancalaPanel(Model model) {
		this.model = model;
		repaint();
		addMouseListener(new Listener());

		pocketList = new ArrayList<>();
		setSize(600,180);
		cBoard = model.getBoardColor();
		cPocket = model.getPocketColor();
	}

	
	
	public void paintComponent(Graphics g) {
		//System.out.println("actualboard: " + cBoard);
		border = new LineBorder(cBoard, 3);
		setBorder(border);
		super.paintComponent(g);
		int dx = startX+30;
		int dy = startY+105;
		for (int i = 0; i < 12; i++) {
			dx += 70;
			if (i == 6) {
				dx -= 70;
				dy -= 75;
				// draw first mancala
				pocketList.add(new Pocket(pocketList.size(),startX + 520, startY + 20, 60, 140));
			}
			if (i > 6) {
				dx -= 140;
				dy -= 75;
			}
			Pocket pocket = new Pocket(pocketList.size(), dx, dy, 50);
			pocketList.add(pocket);
			dy = startY + 105;
			//pocket.setNumStones(model.getStoneNumber(i)); //?
		}
		// draw second mancala
		pocketList.add(new Pocket(pocketList.size(),startX + 20, startY + 20, 60, 140));
		for (int i = 0; i < pocketList.size(); i++){
			pocketList.get(i).draw(g, cPocket);
		}
		
	}
	
	
	// Controller
	private class Listener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			System.out.println("mousePressed");
			Point mousePoint = event.getPoint();
			Pocket pocket = null;
			
			for(Pocket p : pocketList) {
				Ellipse2D.Double temp = p.getOutline();
				if(temp.contains(mousePoint)) {					
					pocket = p;
					break;
				}
			}
			
			if (pocket==null) {
				System.out.println("Please select a pit. ");
				return;
			}
			
			//check if correct player's pits
			int index = pocketList.indexOf(pocket);
			if (model.getIsPlayerATurn()) {
				if (index>5) {
					System.out.println("You are Player A, please choose pits on your side. ");
					return;//exit the method? want to skip actual update part
					//make sure doesn't mess up undo method
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
			model.toZero(index);//set chosen pit to zero stones
			System.out.println(stoneNumber);
			if (stoneNumber==0) {
				System.out.println("Please pick a pit with stones inside. ");
				return;//keep same player
			}
			for (; stoneNumber>0; stoneNumber-- ) {//get method for stoneNumber in that pit?
				
				if (nextPitIndex==14) { //back to beginning of loop
					nextPitIndex=0;
					stoneNumber = stoneNumber+1;
				}
				else if(nextPitIndex==6){ 
					if (model.getIsPlayerATurn()) {
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
					nextPitIndex++;
				}
				else if (nextPitIndex==13) {
					if (!model.getIsPlayerATurn()) {
						model.updateModel(nextPitIndex);
						if (stoneNumber==1) { //last stone
							//free turn case again
						}
					}
					else { //skip
						stoneNumber=stoneNumber+1;
					}
					nextPitIndex++;
				}
				else {
					if ((stoneNumber==1)&& (model.getStoneNumber(nextPitIndex)==0)){ //last stone and empty pit
						//dont updateModel yet, check special case
						int across = 12-nextPitIndex;
						int value;
						if (model.getIsPlayerATurn() && model.isSideA(nextPitIndex)) {
							value = model.getStoneNumber(across); //get other side 
							model.toZero(across); //toZero other side
							value = value + 1 + model.getPlayerAMancala();
							model.mancalaNewValue(true, value);
							}
						else if ((!model.getIsPlayerATurn()) && (!model.isSideA(nextPitIndex))) {
							value = model.getStoneNumber(across); //get other side 
							model.toZero(across); //toZero other side
							value = value + 1 + model.getPlayerBMancala();
							model.mancalaNewValue(false, value);
						}
						else {
							model.updateModel(nextPitIndex);
						}
					}
					else {
						model.updateModel(nextPitIndex);
					}
					nextPitIndex++;
				}
				
			}
			//model.toZero(index);//set chosen pit to zero stones
			
			//check if all pits on a side are empty 
			if (model.sideAEmpty())  {
				model.sideBIntoB();
				//print end of game
				//pop up window of winner?
				//System.exit(0); //end
				winner();
			}
			else if (model.sideBEmpty()) {
				model.sideAIntoA();
				//print end of game
				//System.exit(0); //end
				winner();
			}		
			
			//change player at end
			//isPlayerA = !isPlayerA;
			model.setIsPlayerATurn( !model.getIsPlayerATurn() );
		}
	}

	private void winner(){
		int AMancala = model.getPlayerAMancala();
		int BMancala = model.getPlayerBMancala(); 
		System.out.println("A: " + AMancala);
		System.out.println("B: " + BMancala);
		if (AMancala>BMancala) {
			System.out.println("Player A is the winner. ");
		}
		else if (AMancala<BMancala) {
			System.out.println("Player B is the winner. ");
		}
		else { //tie
			System.out.println("There is a tie! Both players win!");
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// Mancala gameBoard reads from the Model how many seeds per pocket,
		// then the View redraws
		cBoard = model.getBoardColor();
		cPocket = model.getPocketColor();
		for (int i = 0; i<14; i++) {
			int stonesNum = model.getStoneNumber(i);
				Pocket p = pocketList.get(i);
				p.setNumStones(stonesNum);
		}
		//revalidate();
		repaint(); //? does this work
	}
}
