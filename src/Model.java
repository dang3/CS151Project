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
		
		// let the views know about the change so that they update
		for(ChangeListener listener : listeners) {
			listener.stateChanged( new ChangeEvent(this) );
		}
	}
}
