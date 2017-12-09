import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Displayer extends JFrame implements ChangeListener {
	private final int WIDTH = 800;
	private final int HEIGHT = 500;
	private PlayerBox playerA;
	private PlayerBox playerB;
	private Model model;
	private MancalaPanel mancalaPanel;
	private StartMenu startMenu;
	private JButton undoButton;
	
	public Displayer() {
		initComponents();
		initFrame();
		startMenu = new StartMenu(model);
		model.attach(mancalaPanel);
		model.attach(this);
		playerA.startTurn();
		

	}
	
	private void initFrame() {
		setLayout(null);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Team Mango - Mancala");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		undoButton.setBounds(mancalaPanel.getWidth()/2+60,320,80,30);
		add(undoButton);
		addListenerToButton();
		
		mancalaPanel.setBounds(WIDTH/2 - mancalaPanel.getWidth()/2 , HEIGHT/8, mancalaPanel.getWidth()+1, mancalaPanel.getHeight()+1);
		add(mancalaPanel);	// add the game board panel
		
		playerA.setBounds((WIDTH - mancalaPanel.getWidth())/2 + 100, 300, playerA.getWidth()+1, playerA.getHeight()+1);
		add(playerA);
		
		playerB.setBounds( playerA.getX() + 280, 300, playerB.getWidth()+1, playerB.getHeight()+1 );
		add(playerB);
		setVisible(true);		
	}
	
	private void addListenerToButton() {
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(model.getUndoCount() != 3) 
					model.updateUndo();
			}
		});
		
	}
	
	private void initComponents() {
		playerA = new PlayerBox("Player A");
		playerB = new PlayerBox("Player B");
		model = new Model();
		mancalaPanel = new MancalaPanel(model);
		undoButton = new JButton("Undo");
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(model.getIsPlayerATurn()) {
			playerA.startTurn();
			playerB.endTurn();
		}
		else {
			playerA.endTurn();
			playerB.startTurn();
		}
		
		playerA.setScore( model.getPlayerAMancala() );
		playerB.setScore( model.getPlayerBMancala() );
	}
}



