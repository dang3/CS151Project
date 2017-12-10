
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

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
	private int lastZeroIndex;
	
	private Style style;
	private Color cBoard;
	private Color cPocket;
	private boolean isPlayerATurn = true;
	private boolean PREVisPlayerATurn = false;
	
	private ArrayList<ChangeListener> listeners;
	
	public void setPREVisPlayerATurn(boolean b) {
		PREVisPlayerATurn = b;
	}
	
	public boolean getPREVisPlayerATurn() {
		return PREVisPlayerATurn;
	}
	
	/**
	 * Constructs model, initiates ArrayList s and listeners
	 */
	public Model() {
		playerAMancala = 0;
		playerBMancala = 0;
		listeners = new ArrayList<ChangeListener>();
		undoCount = 0;
		lastZeroIndex=-1;
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

//	public int getInitNumStones() {
//		return initNumStones;
//	}
//	
//	public int[] getPlayerAPits() {
//		return playerAPits;
//	}
//	
//	public int[] getPlayerBPits() {
//		return playerBPits;
//	}
	
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
		if (index>=0 && index<=5) {
			PREVplayerAPits[index] = playerAPits[index];
			playerAPits[index]++;
		}
		else if (index==6) {
			PREVplayerAMancala = playerAMancala;
			playerAMancala++;
		}
		else if (index>=7 && index<=12) {
			PREVplayerBPits[index-7] = playerBPits[index-7];
			playerBPits[index-7]++;
		}
		else {//index==13
			PREVplayerBMancala = playerBMancala;
			playerBMancala++;
		}
		
		// let the views know about the change so that they update
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
			PREVplayerAMancala = playerAMancala;
			PREVplayerAPits = playerAPits;
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
			PREVplayerBMancala = playerBMancala;
			PREVplayerBPits = playerBPits;
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
		//for undo method
		if(lastZeroIndex>0) {
			if (lastZeroIndex<6) {
				PREVplayerAPits[lastZeroIndex] = 0;
			}
			else {
				PREVplayerBPits[lastZeroIndex-7] = 0;
			}
		}
		
		if (index<6) {
			PREVplayerAPits[index] = playerAPits[index];
			playerAPits[index] = 0;
		}
		else {
			PREVplayerBPits[index-7] = playerBPits[index-7];
			playerBPits[index-7] = 0;
		}
		lastZeroIndex = index;
		notifyListeners();
	}
	
	/**
	 * puts stones into either mancala's
	 * @param isA true if mancala to put stones in is A, false if B
	 * @param value number of stones to put into mancala
	 */
	public void mancalaNewValue(boolean isA, int value) {
		if (isA) {
			PREVplayerAMancala = playerAMancala;
			playerAMancala = value;	
		}
		else {
			PREVplayerBMancala = playerBMancala;
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
	 * returns who's turn it is
	 * @return true if it is player a's turn, false if it is b's
	 */
	public boolean getIsPlayerATurn() {
		return isPlayerATurn;
	}

	/**
	 * notifies program when undo button is pressed
	 */
	public void updateUndo() {
		undoCount++;
		boolean aturn = getIsPlayerATurn();
		System.out.print("Player A Pits (before): ");
		for (int i = 0; i < playerAPits.length; i++){
			int pit = playerAPits[i];
			System.out.print(pit+", ");
		}
		System.out.println();
		System.out.print("Player B Pits (before): ");
		for (int i = 0; i < playerBPits.length; i++){
			int pit = playerBPits[i];
			System.out.print(pit+", ");
		}
		System.out.println();
		//System.out.println("playerAPits: "+ playerAPits);
		//System.out.println("playerBPits: "+ playerBPits);
		//System.out.println("playerAMancala: "+ playerAMancala);
		//System.out.println("playerBMancala: "+ playerBMancala);

//		System.out.println("playerAPits: "+ playerAPits);
//		System.out.println("playerBPits: "+ playerBPits);
//		System.out.println("playerAMancala: "+ playerAMancala);
//		System.out.println("playerBMancala: "+ playerBMancala);

		//if(Arrays.equals(playerAPits, PREVplayerAPits))
		//	System.out.println("\n\n\nequal\n\n\n\n");
		

		
		playerAPits = PREVplayerAPits;
		playerAMancala = PREVplayerAMancala;
		playerBPits = PREVplayerBPits;
		playerBMancala = PREVplayerBMancala;
		isPlayerATurn = PREVisPlayerATurn;

//		System.out.print("Player A Pits: ");
//		for (int i = 0; i < playerAPits.length; i++){
//			int pit = playerAPits[i];
//			System.out.print(pit+", ");
//		}
//		//notifyListeners(); //a's turn again
//		System.out.println();
//		if (aturn){
//			System.out.println("still a's turn");
//			setIsPlayerATurn(!aturn); //if it was a's turn, keep it a's turn
//			System.out.println("undocount: "+ undoCount);
//		}
//		else {
//			setIsPlayerATurn(!aturn);
//			undoCount = 0;
//			setUndoCount(0);
//			System.out.println("set undo count to zero");
//		}
//		//listeners.get(1).stateChanged(new ChangeEvent(this));
//		System.out.print("Player A Pits (after): ");
//		for (int i = 0; i < playerAPits.length; i++){
//			int pit = playerAPits[i];
//			System.out.print(pit+", ");
//		}
		//PREVplayerAPits = playerAPits;
		//System.out.println("undo");
//		System.out.print("Player A Pits: ");
//		for (int i = 0; i < playerAPits.length; i++){
//			int pit = playerAPits[i];
//			System.out.print(pit+", ");
//		}
		notifyListeners();
		//PREVplayerAPits = playerAPits;
	}
}
