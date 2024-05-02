package mainMenu.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import mainMenu.model.GameType;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Font;

/**
 * A View from the MVC architecture for the main menu to select a game to play.
 * @author Khuram C.
 */
public class MainMenuStartView extends JFrame implements Observer {
	
	private JPanel contentPane = new JPanel();
	private JButton runGameButton;
	private JComboBox<GameType> gameChoicesBox;
	private JTextPane gameSummaryTextPane;

	/**
	 * Default constructor for the MainMenuStartView. Constructs the View and the associated objects necessary for the View.
	 * Because it should look the same every time, there is only the default constructor.
	 * @author Khuram C.
	 */
	public MainMenuStartView() {
		
		//general view//
		int windowWidth = 700;
		int windowHeight = 600;
		setTitle("Strategy Game Pack"); 
		setBounds(400,100,windowWidth,windowHeight);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		//GameButton//
		int runGameButtonWidth = 200;
		int runGameButtonHeight = 50;
		this.runGameButton = new JButton("Start Game");
		runGameButton.setForeground(new Color(255, 215, 0));
		runGameButton.setBackground(Color.BLACK);
		runGameButton.setFont(new Font("Britannic Bold", Font.PLAIN, 23));
		runGameButton.setToolTipText("Click this to start your game!");
		runGameButton.setSize(runGameButtonWidth, runGameButtonHeight);
		runGameButton.setLocation((windowWidth-runGameButtonWidth)/2, windowHeight-200);
		contentPane.add(runGameButton);
		
		//gameChoicesBox//
		int gameChoicesBoxWidth = 140;
		int gameChoicesBoxHeight = 25;
		int gameChoicesBoxY = windowHeight - 300;
		this.gameChoicesBox = new JComboBox<>();
		gameChoicesBox.setBackground(new Color(0, 0, 0));
		gameChoicesBox.setForeground(new Color(255, 215, 0));
		gameChoicesBox.setFocusable(false);
		gameChoicesBox.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		gameChoicesBox.setToolTipText("Select the game you want to play!");
		gameChoicesBox.setModel(new DefaultComboBoxModel<>(GameType.values()));
		gameChoicesBox.setMaximumRowCount(4);
		gameChoicesBox.setSize(gameChoicesBoxWidth, gameChoicesBoxHeight);
		gameChoicesBox.setLocation((windowWidth-gameChoicesBoxWidth)/2,gameChoicesBoxY);
		contentPane.add(gameChoicesBox);
		
		
		//TextPane//
		int gamePackInfoTextPaneWidth = 300;
		int gamePackInfotTextPaneHeight = 200;
		JTextPane gamePackInfoTextPane = new JTextPane();
		gamePackInfoTextPane.setFont(new Font("Snap ITC", Font.ITALIC, 29));
		gamePackInfoTextPane.setEditable(false);
		gamePackInfoTextPane.setOpaque(false);
		gamePackInfoTextPane.setForeground(new Color(255, 215, 0));
		gamePackInfoTextPane.setText("Choose any game from the four two-player strategy games below to play!");
		gamePackInfoTextPane.setSize(gamePackInfoTextPaneWidth, gamePackInfotTextPaneHeight);
		gamePackInfoTextPane.setLocation((windowWidth-gamePackInfoTextPaneWidth)/2, 38);
		StyledDocument documentStyle = gamePackInfoTextPane.getStyledDocument();
		SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
		documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
		contentPane.add(gamePackInfoTextPane);
		
		//gameSummaryTextPane//
		int gameSumTPWidth = 160;
		int gameSumTPHeight = 500;
		this.gameSummaryTextPane = new JTextPane();
		gameSummaryTextPane.setFont(new Font("Snap ITC", Font.ITALIC, 15));
		gameSummaryTextPane.setEditable(false);
		gameSummaryTextPane.setOpaque(false);
		gameSummaryTextPane.setForeground(new Color(255, 215, 0));
		gameSummaryTextPane.setSize(gameSumTPWidth, gameSumTPHeight);
		gameSummaryTextPane.setLocation((windowWidth-gameSumTPWidth)*9/10, 
				(gameChoicesBoxY+gameChoicesBoxHeight/2 - gameSumTPHeight/4));
		StyledDocument gameSumdocumentStyle = gamePackInfoTextPane.getStyledDocument();
		SimpleAttributeSet gameSumcenterAttribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(gameSumcenterAttribute, StyleConstants.ALIGN_CENTER);
		documentStyle.setParagraphAttributes(0, gameSumdocumentStyle.getLength(), gameSumcenterAttribute, false);
		contentPane.add(gameSummaryTextPane);
		
		
	}
	
	/**
	 * Adds a listener to the gameChoicesBox for whenever a new game has been chosen.
	 * @param listener to listen to gameChoicesBox.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean addListenertoGameChoicesBox(ActionListener listener) {
		gameChoicesBox.addActionListener(listener);
		return true;
	}
	
	/**
	 * Adds a listener to the startGameButton for whenever it gets pressed.
	 * @param listener to listen to startGameButton.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean addListenertoStartGameButton(ActionListener listener) {
		runGameButton.addActionListener(listener);
		return true;
	}


	/**
	 * Returns the chosen GameType enum from the gameChoicesBox.
	 * @return the GameType selected.
	 * @author Khuram C.
	 */
	public GameType getGameChoicesBoxChoice() {
		return (GameType) gameChoicesBox.getSelectedItem();
	}
	
	/**
	 * Changes the text of the gameSummaryTextPane.
	 * @param s new string for gameSummaryTextPane.
	 * @return boolean returning success.
	 * @author Khuram C.
	 */
	public boolean changeGameSummaryTextPaneText(String s) {
		gameSummaryTextPane.setText(s);
		return true;
	}
	
	/**
	 * Updates the gameSummaryTextPane's text based on the gameType when updated.
	 * @author Khuram C.
	 */
	@Override
	public void update(Observable o, Object arg) {
		GameType game = (GameType) arg;
		changeGameSummaryTextPaneText(game.getGameDescription());
	}
}
