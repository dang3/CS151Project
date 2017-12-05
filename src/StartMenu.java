import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu  {
	private JFrame frame;
	private JPanel panel;
	private JButton startBut;
	private JLabel numStonesLab;
	private JLabel boardStyleLab;
	private JComboBox<String> numStonesBox;
	private JComboBox<String> boardStyleBox;
	private String[] numStones = {"3", "4"};
	private String[] boardStyle = {"Style 1", "Style 2"};
	private GridBagConstraints c;
	private Model model;
	
	public StartMenu(Model model) {
		this.model = model;
		initFrame();
		initComponents();
		addComponents();
		addListeners();
		frame.setVisible(true);
	}
	
	private void initFrame() {
		frame = new JFrame();
		frame.setSize(300, 150);
		frame.setTitle("Start");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	private void addListeners() {
		startBut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String stoneStr = numStonesBox.getSelectedItem().toString();
				model.setInitNumStones( Integer.parseInt(stoneStr) );	// set initial number of stones
				System.out.println(stoneStr);
				
				String styleStr = boardStyleBox.getSelectedItem().toString().substring(6);
				model.setStyle(Integer.parseInt(styleStr));
				System.out.println("Style: " + styleStr);
				
				// need to do same for board style
				frame.dispose();	// close the frame	
			}
		});
	}
	
	
	private void initComponents() {
		panel = new JPanel(new GridBagLayout());
		frame.add(panel);
		startBut = new JButton("Start");
		numStonesLab = new JLabel("Select number of stones:      ");
		boardStyleLab = new JLabel("Select board design:      ");
		c = new GridBagConstraints();
		numStonesBox = new JComboBox<>(numStones);
		boardStyleBox = new JComboBox<>(boardStyle);
	}
	
	private void addComponents() {
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		panel.add(numStonesLab, c);
		c.gridy++;
		panel.add(boardStyleLab, c);

		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		panel.add(numStonesBox, c);
		c.gridy++;
		panel.add(boardStyleBox, c);
		c.gridy++;
		panel.add(startBut, c);	
	}
}
