import java.awt.Color;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {

	//does it need to be arrayList? I chose array because its a set number of pits
	private int playerAPits[] = new int[6];
	private int playerBPits[] = new int[6];
	private int playerAMancala;
	private int playerBMancala;
	private int initNumStones;
	private int styleType;
	private Style style;
	Color cBoard;
	Color cPocket;
	
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Constructs model, initiates ArrayList s and listeners
	 */
	public Model() {
		playerAMancala = 0;
		playerBMancala = 0;
		listeners = new ArrayList<ChangeListener>();
		//cBoard = Color.black;
	}
		
	public void setInitNumStones(int val) {
		initNumStones = val;
		for (int i = 0; i<6;i++) {
			playerAPits[i] = initNumStones;
			playerBPits[i] = initNumStones;
		}
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
	}

	public void setStyle(int val){
		styleType = val;
		if (val == 1){
			System.out.println("stylejuan");
			style = new MancalaStyle();
		}
		else if (val == 2){
			style = new MancalaStyle1();
		}
		cBoard = style.colorOfBoard();
		System.out.println(cBoard);
		cPocket = style.colorOfPockets();
		System.out.println(cPocket);
		for (ChangeListener listener : listeners){
			listener.stateChanged(new ChangeEvent(this));
		}
	}
	public Color getBoardColor(){
		return cBoard;
	}
	public Color getPocketColor(){
		return cPocket;
	}

	public int getInitNumStones() {
		return initNumStones;
	}
	
	
	public int[] getPlayerAPits() {
		return playerAPits;
	}
	
	public int[] getPlayerBPits() {
		return playerBPits;
	}
	
	public int getPlayerAMancala(){
		return playerAMancala;
	}
	
	public int getPlayerBMancala(){
		return playerBMancala;
	}
	
	//attaches to view listener which calls calls getmethods when stateChanged
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
			playerAPits[index]++;
		}
		else if (index==6) {
			playerAMancala++;
		}
		else if (index>=7 && index<=12) {
			playerBPits[index-7]++;
		}
		else {//index==13
			playerBMancala++;
		}
		
		// let the views know about the change so that they update
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
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
	
	public boolean sideAEmpty() {
		return (getStoneNumber(0)==0)&&(getStoneNumber(1)==0)&&(getStoneNumber(2)==0)&&(getStoneNumber(3)==0)&&(getStoneNumber(4)==0)&&(getStoneNumber(5)==0);
	}
	
	public boolean sideBEmpty() {
		return (getStoneNumber(7)==0)&&(getStoneNumber(8)==0)&&(getStoneNumber(9)==0)&&(getStoneNumber(10)==0)&&(getStoneNumber(11)==0)&&(getStoneNumber(12)==0);
	}
	
	public void sideAIntoA() {
		for (int i = 0; i<=5; i++) {
			playerAMancala = playerAMancala + getStoneNumber(i);
			playerAPits[i] = 0;
		}
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
	}
	
	public void sideBIntoB() {
		for (int i = 7; i<=12; i++) {
			playerBMancala = playerBMancala + getStoneNumber(i);
			playerBPits[i-7] =0;
		}
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
	}
	
	public boolean isSideA(int index) {
		if (index<6) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void toZero(int index) {
		if (index<6) {
			playerAPits[index] = 0;
		}
		else {
			playerBPits[index-7] = 0;
		}
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
	}
	
	public void mancalaNewValue(boolean isA, int value) {
		if (isA) {
			playerAMancala = value;
			
		}
		else {
			playerBMancala = value;
		}
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
	}


}
