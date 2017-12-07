import java.awt.BorderLayout;
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
		
		mancalaPanel.setBounds(WIDTH/2 - mancalaPanel.getWidth()/2 , HEIGHT/8, mancalaPanel.getWidth()+1, mancalaPanel.getHeight()+1);
		add(mancalaPanel);	// add the game board panel
		
		playerA.setBounds((WIDTH - mancalaPanel.getWidth())/2 + 100, 300, playerA.getWidth()+1, playerA.getHeight()+1);
		add(playerA);
		
		playerB.setBounds( playerA.getX() + 280, 300, playerB.getWidth()+1, playerB.getHeight()+1 );
		add(playerB);
		setVisible(true);		
	}
	
	private void initComponents() {
		playerA = new PlayerBox("Player A");
		playerB = new PlayerBox("Player B");
		model = new Model();
		mancalaPanel = new MancalaPanel(model);
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



