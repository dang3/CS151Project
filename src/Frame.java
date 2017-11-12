import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Frame extends JFrame {
	private final int WIDTH = 1000;
	private final int HEIGHT = 800;
	private PlayerPanel playerPanel1;
	private PlayerPanel playerPanel2;
	private Model model;
	private GameBoardPanel panel;
	
	public Frame() {
		initComponents();
		initFrame();
	}
	
	private void initFrame() {
		setLayout(new BorderLayout());
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Team Mango - Mancala");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(panel, BorderLayout.CENTER);	// add the game board panel
		add(playerPanel1, BorderLayout.WEST);
		add(playerPanel2, BorderLayout.EAST);
		setVisible(true);
	}
	
	private void initComponents() {
		playerPanel1 = new PlayerPanel("Player 1");
		playerPanel2 = new PlayerPanel("Player 2");
		model = new Model();
		panel = new GameBoardPanel(model);
	}
	
	
}



