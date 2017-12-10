
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Mancala Board of game, including displaying pockets and stones
 * Dennis Dang, Vera Wong, Marissa Xiong
 * Ver 1.1
 * 12/9/2017
 */

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
	
	
	/**
	 * constructor of Mancala Panel
	 * @param model a model that initializes the panel
	 */
	public MancalaPanel(Model model) {
		this.model = model;
		repaint();
		addMouseListener(new Listener());

		pocketList = new ArrayList<>();
		setSize(600,180);
		cBoard = model.getBoardColor();
		cPocket = model.getPocketColor();
		//winner();
	}

	/**
	 * 
	 */
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
	
	
	/**
	 * Controller Class that takes in information
	 * 
	 *
	 */
	private class Listener extends MouseAdapter {
		/**
		 * when mouse is pressed, game is played
		 */
		public void mousePressed(MouseEvent event) {
			model.setPREVisPlayerATurn(model.getIsPlayerATurn());
			//System.out.println("mousePressed");
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
							model.setIsPlayerATurn(!model.getIsPlayerATurn()); //change player turn back to a
							//if (model.getUndoCount() > 0){ //if undo has been used
								//model.setIsPlayerATurn(model.getIsPlayerATurn());//change so it gets changed back to A at the end
								//System.out.println("change back to a");
							//}
							System.out.println("Player A, Take another turn. ");
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
							model.setIsPlayerATurn(!model.getIsPlayerATurn());//change so it gets changed back to A at the end
							//if (model.getUndoCount() > 0){ //if undo has been used
								//model.setIsPlayerATurn(!model.getIsPlayerATurn());//change so it gets changed back to A at the end
							//	System.out.println("change back to b");
							//}
							System.out.println("Player B, Take another turn. ");
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
						if (model.getIsPlayerATurn() && model.isSideA(nextPitIndex)) { //if a's turn and index is on a's side
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
			}		
			
			//change player at end
			//isPlayerA = !isPlayerA;

			model.setIsPlayerATurn( !model.getIsPlayerATurn() );
			System.out.println("playerATurn: " + model.getIsPlayerATurn());
			System.out.println("PREVplayerATurn: " + model.getPREVisPlayerATurn());
			
		
		}
	}

	/**
	 * indicates the winner, player a or b
	 */
	private void winner(){
		JFrame winnerPopUp = new JFrame();
		winnerPopUp.setSize(300,150);
		winnerPopUp.setLocationRelativeTo(null);
		winnerPopUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel winnerPanel = new JPanel();
		JLabel winnerLabel = new JLabel();
		winnerLabel.setFont(new Font("Arial", Font.BOLD, 25));
		winnerLabel.setForeground(Color.RED);
		JButton okButton = new JButton("Ok");
		winnerPanel.setLayout(null);
		winnerPopUp.add(winnerPanel);
		winnerPanel.add(winnerLabel);
		winnerPanel.add(okButton);
		
	
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		int AMancala = model.getPlayerAMancala();
		int BMancala = model.getPlayerBMancala(); 
		System.out.println("A: " + AMancala);
		System.out.println("B: " + BMancala);
		if (AMancala>BMancala) {
			winnerLabel.setText("Player A wins!!!!!");
		}
		else if (AMancala<BMancala) {
			winnerLabel.setText("Player B wins!!!!!");
		}
		else { //tie
			winnerLabel.setText("Nobody wins!!!!!");
		}
		
		winnerLabel.setBounds( (winnerPopUp.getWidth() - winnerLabel.getPreferredSize().width)/2 , winnerPopUp.getHeight()/7, winnerLabel.getPreferredSize().width, winnerLabel.getPreferredSize().height);
		okButton.setBounds( (winnerPopUp.getWidth() - okButton.getPreferredSize().width)/2, 3*winnerPopUp.getHeight()/7, okButton.getPreferredSize().width, okButton.getPreferredSize().height );
		winnerPopUp.setVisible(true);
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
