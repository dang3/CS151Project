import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {

	//does it need to be arrayList? I chose array because its a set number of pits
	private int playerAPits[] = new int[6];
	private int playerBPits[] = new int[6];
	private int playerAMancala;
	private int playerBMancala;
	
	private ArrayList<ChangeListener> listeners;
	
	/**
	 * Constructs model, initiates ArrayList s and listeners
	 */
	public Model() {
		playerAMancala = 0;
		playerBMancala = 0;
		listeners = new ArrayList<ChangeListener>();
	}
	
//	//called in mousePressed, controllerListener
//	public void update(int placingNumber){ //if contains() method (called in mousePressed) returns number for placing/which pit. (or maybe mousePressed would figure out the number with the contains method?) 
//		if (placingNumber> || placingNumber< ) //temporary
//		else if(placingNumber== )
//		else if(placingNumber> || placingNumber< )
//		else 
//			
//			//if player A Pits
//			playerAPits[placingNumber]++; //maybe [placingNumber - 1]
//			//if player A Mancala
//			playerAMancala++;
//			//if player B Pits
//			playerBPits[placingNumber]++;
//			//if player B Mancala
//			playerBMancala++;
//	}
	
	//public void updatePit(){ }
	//public void updateMancala() { }
		
	
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
		if (index>=0 || index<=5) {
			playerAPits[index]++;
		}
		else if (index==6) {
			playerAMancala++;
		}
		else if (index>=7 || index<=12) {
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
		if (index>=0 || index<=5) {
			return playerAPits[index];
		}
		else if (index==6) {
			return playerAMancala;
		}
		else if (index>=7 || index<=12) {
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
	}
	
	public void sideBIntoB() {
		for (int i = 7; i<=12; i++) {
			playerBMancala = playerBMancala + getStoneNumber(i);
			playerBPits[i] =0;
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
			playerBPits[index] = 0;
		}
	}
	
	public void mancalaNewValue(boolean isA, int value) {
		if (isA) {
			playerAMancala = value;
			
		}
		else {
			playerBMancala = value;
		}
	}
}
