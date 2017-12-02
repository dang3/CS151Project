import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Displayer extends JFrame implements ChangeListener {
	private final int WIDTH = 800;
	private final int HEIGHT = 500;
	private PlayerBox player1;
	private PlayerBox player2;
	private Model model;
	private MancalaPanel mancalaPanel;
	private StartMenu startMenu;
	
	public Displayer() {
		initComponents();
		initFrame();
		startMenu = new StartMenu(model);
		model.attach(mancalaPanel);
		model.attach(this);
		player1.startTurn();
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
		
		player1.setBounds((WIDTH - mancalaPanel.getWidth())/2 + 100, 300, player1.getWidth()+1, player1.getHeight()+1);
		add(player1);
		
		player2.setBounds( player1.getX() + 280, 300, player2.getWidth()+1, player2.getHeight()+1 );
		add(player2);
		setVisible(true);		
	}
	
	private void initComponents() {
		player1 = new PlayerBox("Player 1");
		player2 = new PlayerBox("Player 2");
		model = new Model();
		mancalaPanel = new MancalaPanel(model);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		System.out.println(mancalaPanel.getIsPlayerA());
		if(mancalaPanel.getIsPlayerA()) {
			player1.startTurn();
		}
		else
			player1.endTurn();
		
		player1.setScore( model.getPlayerAMancala() );
		player2.setScore( model.getPlayerBMancala() );


	}
}



