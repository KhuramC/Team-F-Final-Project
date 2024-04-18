package connect4Menu.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Connect4MenuView extends JFrame{
	
	private JPanel contentPane = new JPanel();

	public Connect4MenuView() {
		setTitle("Connect 4");
		setBounds(100,100,700,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(null);
	}

}
