package mainMenu.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import mainMenu.model.GameType;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MainMenuStartView extends JFrame {
	
	private JPanel contentPane = new JPanel();
	private JButton chooseGameButton;
	private JComboBox gameChoicesBox;

	public MainMenuStartView() {
		setTitle("Game Pack(NAME TBD)"); // pick a better name
		setBounds(100,100,700,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		this.chooseGameButton = new JButton("Choose Game");
		chooseGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chooseGameButton.setSize(188, 50);
		chooseGameButton.setLocation(232, 301);
		contentPane.add(chooseGameButton);
		
		this.gameChoicesBox = new JComboBox();
		gameChoicesBox.setModel(new DefaultComboBoxModel(GameType.values()));
		gameChoicesBox.setMaximumRowCount(4);
		gameChoicesBox.setBounds(268, 156, 125, 25);
		contentPane.add(gameChoicesBox);
		
		
		JTextPane txtpnGamePackChoose = new JTextPane();
		txtpnGamePackChoose.setEditable(false);
		txtpnGamePackChoose.setText("Game Pack. Choose from the available games below.");
		txtpnGamePackChoose.setBounds(252, 38, 141, 108);
		StyledDocument documentStyle = txtpnGamePackChoose.getStyledDocument();
		SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
		documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
		contentPane.add(txtpnGamePackChoose);
		
		
	}
	
	
	public void addListenertoChooseGameButton(ActionListener listener) {
		chooseGameButton.addActionListener(listener);
	}

	public JButton getChooseGameButton() {
		return chooseGameButton;
	}
	
	public GameType getGameChoicesBoxChoice() {
		return (GameType) gameChoicesBox.getSelectedItem();
	}
	
}
