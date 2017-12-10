import java.awt.Color;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * model class of Mancala
 * Dennis Dang, Vera Wong, Marissa Xiong
 * Ver 1.1
 * 12/9/2017
 */

public class Model {
	private int playerAPits[] = new int[6];
	private int playerBPits[] = new int[6];
	private int playerAMancala;
	private int playerBMancala;
	private int initNumStones;	

	private int PREVplayerAPits[] = new int[6];
	private int PREVplayerBPits[] = new int[6];
	private int PREVplayerAMancala;
	private int PREVplayerBMancala;
	
	private int undoCount;
	
	private Style style;
	private Color cBoard;
	private Color cPocket;
	private boolean isPlayerATurn = true;
	private boolean PREVisPlayerATurn = false;
	
	private ArrayList<ChangeListener> listeners;

	/**
	 * Constructs model, initiates ArrayList s and listeners
	 */
	public Model() {
		playerAMancala = 0;
		playerBMancala = 0;
		listeners = new ArrayList<ChangeListener>();
		undoCount = 0;
	}
	
	
	/**
	 * initializes the number of stones in the game (3 or 4)
	 * @param val the number to initialize each pocket with
	 */
	public void setInitNumStones(int val) {
		initNumStones = val;
		for (int i = 0; i<6;i++) {
			playerAPits[i] = initNumStones;
			playerBPits[i] = initNumStones;
			PREVplayerAPits[i] = initNumStones;
			PREVplayerBPits[i] = initNumStones;
		}
		notifyListeners();
	}
	
	/**
	 * sets the undo count
	 * @param c number to set undo count to
	 */
	public void setUndoCount(int c) {
		undoCount = c;
	}
	
	/**
	 * returns the number of times undo is clicked during a turn
	 * @return then number of times undo is clicked
	 */
	public int getUndoCount() {
		return undoCount;
	}
	/**
	 * sets the style of the board depending on the style the user chooses
	 * @param val the number (1 or 2) the user uses to set the style
	 */
	public void setStyle(int val){
		if (val == 1){
			style = new MancalaStyle1();
		}
		else if (val == 2){
			style = new MancalaStyle2();
		}
		cBoard = style.colorOfBoard();
		cPocket = style.colorOfPockets();

		notifyListeners();
	}
	/**
	 * gets the board color
	 * @return board color
	 */
	public Color getBoardColor(){
		return cBoard;
	}
	/**
	 * returns the pocket color
	 * @return pocket color
	 */
	public Color getPocketColor(){
		return cPocket;
	}
	
	/**
	 * gets the number of stones in player a's mancala
	 * @return the number of stones in player a's mancala
	 */
	public int getPlayerAMancala(){
		return playerAMancala;
	}
	
	/**
	 * gets the number of stones in b's mancala
	 * @return the number of stones in player b's mancala
	 */
	public int getPlayerBMancala(){
		return playerBMancala;
	}
	
	/**
	 * attaches to view listener which calls calls getmethods when stateChanged
	 * @param listener listener to add to list of ChangeListeners
	 */
	public void attach(ChangeListener listener) {
		listeners.add(listener);
	}
	
	/**
	 *  this method is called by the controller to update the arrays
	 * @param index Index of the pit that was clicked
	 */
	public void updateModel(int index) {
		// update the arrays here
		if (index>=0 && index<=5) 
			playerAPits[index]++;
		else if (index==6) 
			playerAMancala++;
		
		else if (index>=7 && index<=12) 
			playerBPits[index-7]++;
		else if (index == 13)//index==13
			playerBMancala++;
		notifyListeners();
	}
	
	//for easy access to indices
	public int getStoneNumber(int index) {
		if (index>=0 && index<=5) {
			return playerAPits[index];
		}
		else if (index==6) {
			return playerAMancala;
		}
		else if (index>=7 && index<=12) {
			return playerBPits[index-7];
		}
		else {//index==13
			return playerBMancala;
		}
	}
	
	/**
	 * check if side A is all empty
	 * @return true if there are no more stones on side a
	 */
	public boolean sideAEmpty() {
		return (getStoneNumber(0)==0)&&(getStoneNumber(1)==0)&&(getStoneNumber(2)==0)&&(getStoneNumber(3)==0)&&(getStoneNumber(4)==0)&&(getStoneNumber(5)==0);
	}
	
	/**
	 * check if side B is all empty
	 * @return true if there are no more stones on side b
	 */
	public boolean sideBEmpty() {
		return (getStoneNumber(7)==0)&&(getStoneNumber(8)==0)&&(getStoneNumber(9)==0)&&(getStoneNumber(10)==0)&&(getStoneNumber(11)==0)&&(getStoneNumber(12)==0);
	}
	
	/**
	 * puts all stones on a's side into a's mancala
	 */
	public void sideAIntoA() {
		for (int i = 0; i<=5; i++) {
			playerAMancala = playerAMancala + getStoneNumber(i);
			playerAPits[i] = 0;
		}
		notifyListeners();
	}
	
	/**
	 * puts all stones on b's side into b's mancala
	 */
	public void sideBIntoB() {
		for (int i = 7; i<=12; i++) {
			playerBMancala = playerBMancala + getStoneNumber(i);
			playerBPits[i-7] =0;
		}
		notifyListeners();
	}
	
	/**
	 * checks if the index is on a's side
	 * @param index index of pocket
	 * @return true if index is on a's side (pocket) and false if it is on b's
	 */
	public boolean isSideA(int index) {
		if (index<6) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * sets pocket's stones to zero
	 * @param index index of pocket
	 */
	public void toZero(int index) {		
		if (index<6) {
			playerAPits[index] = 0;
		}
		else {
			playerBPits[index-7] = 0;
		}
		notifyListeners();
	}
	
	/**
	 * puts stones into either mancala's
	 * @param isA true if mancala to put stones in is A, false if B
	 * @param value number of stones to put into mancala
	 */
	public void mancalaNewValue(boolean isA, int value) {
		if (isA) {
			playerAMancala = value;	
		}
		else {
			playerBMancala = value;
		}
		notifyListeners();
	}
	
	/**
	 * notify Change Listeners
	 */
	public void notifyListeners() {
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
	}
	
	/**
	 * set the turn to player a or b
	 * @param b if true then set player to a, if false then b
	 */
	public void setIsPlayerATurn(boolean b) {
		isPlayerATurn = b;
		notifyListeners();
	}
	
	/**
	 * returns true if it is player a's turn
	 * @return true if it is player a's turn, false if it is b's
	 */
	public boolean getIsPlayerATurn() {
		return isPlayerATurn;
	}
	
	public boolean getPREVisPlayerATurn() {
		return PREVisPlayerATurn;
	}

	/**
	 * returns true if the previous turn was player a's
	 * @return true if previous player was a, false if it was b
	 */
	public boolean getPREVisPlayerATurn() {
		return PREVisPlayerATurn;
	}

	/**
	 * saves the previous state of the mancala (for undo)
	 */
	public void savePrevState() {
		PREVplayerAMancala = playerAMancala;
		PREVplayerBMancala = playerBMancala;
		System.arraycopy(playerAPits , 0, PREVplayerAPits, 0, PREVplayerAPits.length);
		System.arraycopy(playerBPits, 0, PREVplayerBPits, 0, PREVplayerBPits.length);
		PREVisPlayerATurn = isPlayerATurn;
	}
	
	/**
	 * notifies program when undo button is pressed
	 */
	public void updateUndo() {
		if (undoCount < 2) {
			// restore pits to previous
			playerAMancala = PREVplayerAMancala;
			playerBMancala = PREVplayerBMancala;
			System.arraycopy(PREVplayerAPits, 0, playerAPits, 0, playerAPits.length);
			System.arraycopy(PREVplayerBPits, 0, playerBPits, 0, playerBPits.length);
			isPlayerATurn = PREVisPlayerATurn;

			undoCount++;
			notifyListeners();
		}
	}

}