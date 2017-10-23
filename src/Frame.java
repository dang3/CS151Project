import javax.swing.JFrame;

public class Frame extends JFrame {
	private final int WIDTH = 800;
	private final int HEIGHT = 800;
	private Panel panel = new Panel();
	
	public Frame() {
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Team Mango - Mancala");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		setVisible(true);
	}
}
