package mainMenu.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import mainMenu.model.GameType;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * A View from the MVC architecture for the main menu to select a game to play.
 * @author Khuram C.
 */
public class MainMenuStartView extends JFrame {
	
	private JPanel contentPane = new JPanel();
	private JButton startGameButton;
	private JComboBox gameChoicesBox;

	/**
	 * Default constructor for the MainMenuStartView. Constructs the View and the associated objects necessary for the View.
	 * Because it should look the same every time, there is only the default constructor.
	 * @author Khuram C.
	 */
	public MainMenuStartView() {
		setTitle("Game Pack"); // pick a better name
		setBounds(100,100,700,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		this.startGameButton = new JButton("Start Game");
		startGameButton.setSize(188, 50);
		startGameButton.setLocation(232, 301);
		contentPane.add(startGameButton);
		
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
	
	/**
	 * Adds a listener to the gameChoicesBox for whenever a new game has been chosen.
	 * @param listener to listen to gameChoicesBox.
	 * @author Khuram C.
	 */
	public void addListenertoGameChoicesBox(ActionListener listener) {
		gameChoicesBox.addActionListener(listener);
	}
	
	/**
	 * Adds a listener to the startGameButton for whenever it gets pressed.
	 * @param listener to listen to startGameButton.
	 * @author Khuram C.
	 */
	public void addListenertoStartGameButton(ActionListener listener) {
		startGameButton.addActionListener(listener);
	}


	/**
	 * Returns the chosen GameType enum from the gameChoicesBox.
	 * @return the GameType selected.
	 * @author Khuram C.
	 */
	public GameType getGameChoicesBoxChoice() {
		return (GameType) gameChoicesBox.getSelectedItem();
	}
	
}
