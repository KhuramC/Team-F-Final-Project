package connect4Menu.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import connect4Menu.model.Connect4MenuModel;
import connect4Menu.model.Player1Colors;
import connect4Menu.model.Player2Colors;
import connect4Menu.model.PlayerColors;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JInternalFrame;



/**
 * A View from the MVC architecture used for choosing the settings of the Connect4 game.
 * @author Khuram C.
 */
public class Connect4SettingsMenuView extends JFrame{
	
	private JPanel contentPane = new JPanel();
	private final ButtonGroup sizeButtonGroup = new ButtonGroup();
	private JToggleButton timerToggleButton;
	private JSlider timerSlider;
	private JTextField timerTextField;
	private JComboBox player1ColorsComboBox;
	private JComboBox player2ColorsComboBox;
	private JButton startGameButton;
	private JLabel errorLabel;
	
	/**
	 * Default constructor for the Connect4SettingsMenuView. Constructs the View and the associated objects necessary for the View.
	 * Because it should look the same every time, there is only the default constructor.
	 * @author Khuram C.
	 */
	public Connect4SettingsMenuView() {
		setTitle("Connect 4 Settings"); //general settings//
		setBounds(100,100,900,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JRadioButton button4x5 = new JRadioButton("4x5"); //radioButtons for board size//
		sizeButtonGroup.add(button4x5);
		button4x5.setBounds(63, 65, 103, 21);
		contentPane.add(button4x5);
		
		JRadioButton button5x6 = new JRadioButton("5x6");
		sizeButtonGroup.add(button5x6);
		button5x6.setBounds(63, 88, 103, 21);
		contentPane.add(button5x6);
		
		JRadioButton button6x7 = new JRadioButton("6x7(Standard)");
		button6x7.setSelected(true);
		sizeButtonGroup.add(button6x7);
		button6x7.setBounds(63, 111, 103, 21);
		contentPane.add(button6x7);
		
		JRadioButton button7x8 = new JRadioButton("7x8");
		sizeButtonGroup.add(button7x8);
		button7x8.setBounds(63, 134, 103, 21);
		contentPane.add(button7x8);
		
		JRadioButton button8x8 = new JRadioButton("8x8");
		sizeButtonGroup.add(button8x8);
		button8x8.setBounds(63, 157, 103, 21);
		contentPane.add(button8x8);
		
		JRadioButton button7x9 = new JRadioButton("7x9");
		sizeButtonGroup.add(button7x9);
		button7x9.setBounds(63, 180, 103, 21);
		contentPane.add(button7x9);
		
		JTextField titleTextField = new JTextField(); 
		titleTextField.setHorizontalAlignment(SwingConstants.CENTER);
		titleTextField.setEditable(false);
		titleTextField.setText("CONNECT4 SETTINGS");
		titleTextField.setBounds(230, 10, 502, 39);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);
		
		timerToggleButton = new JToggleButton("Enable Timer");
		timerToggleButton.setBounds(53, 251, 115, 21);
		contentPane.add(timerToggleButton);
		
		timerSlider = new JSlider();
		timerSlider.setEnabled(false);
		timerSlider.setMaximum(Connect4MenuModel.maxTimerTime);
		timerSlider.setMinimum(Connect4MenuModel.minTimerTime);
		timerSlider.setValue(Connect4MenuModel.minTimerTime);
		timerSlider.setEnabled(false);
		timerSlider.setVisible(false);
		timerSlider.setBounds(10, 282, 200, 26);
		contentPane.add(timerSlider);
		
		timerTextField = new JTextField();
		timerTextField.setText(Integer.toString(Connect4MenuModel.minTimerTime));
		timerTextField.setEnabled(false);
		timerTextField.setVisible(false);
		timerTextField.setBounds(60, 318, 96, 19);
		contentPane.add(timerTextField);
		timerTextField.setColumns(10);
		
		JLabel player1Label = new JLabel("Player 1 Colors");
		player1Label.setBounds(761, 106, 93, 13);
		contentPane.add(player1Label);
		
		JLabel sizeSettingsLabel = new JLabel("Size Settings (RowxCol)");
		sizeSettingsLabel.setBounds(53, 38, 147, 21);
		contentPane.add(sizeSettingsLabel);
		
		player1ColorsComboBox = new JComboBox();
		player1ColorsComboBox.setModel(new DefaultComboBoxModel(Player1Colors.values()));
		player1ColorsComboBox.setBounds(761, 128, 93, 21);
		contentPane.add(player1ColorsComboBox);
		
		JLabel player2Label = new JLabel("Player 2 Colors");
		player2Label.setBounds(761, 210, 93, 13);
		contentPane.add(player2Label);
		
		player2ColorsComboBox = new JComboBox();
		player2ColorsComboBox.setModel(new DefaultComboBoxModel(Player2Colors.values()));
		player2ColorsComboBox.setBounds(761, 232, 93, 21);
		contentPane.add(player2ColorsComboBox);
		
		startGameButton = new JButton("Start Game");
		startGameButton.setBounds(398, 298, 115, 21);
		contentPane.add(startGameButton);	
		
		this.errorLabel = new JLabel("");
		errorLabel.setBounds(10, 347, 200, 16);
		contentPane.add(errorLabel);
		
		JTextArea rulesTextArea = new JTextArea();
		rulesTextArea.setEditable(false);
		rulesTextArea.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		rulesTextArea.setLineWrap(true);
		rulesTextArea.setText("Welcome to Connect4! The rules are simple. Two players play turns in succession to try"
				+ " and get four in a row. One can get four in a row horizontally, vertically, and even diagonally."
				+ " One's circle has to be played on top of another circle if it is not going to the bottom row."
				+ " After a full column has been played on, no more circles can be played on it. Many different board"
				+ " sizes are available to choose from. A timer can also be selected to force a turn to be taken within"
				+ " 15 to 60 seconds of the other player's turn. Lastly, each player can configure what color they want."
				+ " Once the settings have been decided, press 'Start Game' to start!");
		rulesTextArea.setBounds(220, 64, 512, 203);
		contentPane.add(rulesTextArea);
	}
	
	/**
	 * Adds a listener to the timerToggleButton for whenever it is pressed.
	 * @param listener to listen to the timerToggleButton.
	 * @author Khuram C.
	 */
	public void addListenertoTimerToggleButton(ActionListener listener) {
		timerToggleButton.addActionListener(listener);	
	}
	
	/**
	 * Adds a listener to the timerSlider for whenever it is changed.
	 * @param listener to listen to timerSlider.
	 * @author Khuram C.
	 */
	public void addListenertoTimerSlider(ChangeListener listener) {
		timerSlider.addChangeListener(listener);
	}
	
	/**
	 * Adds a listener to the timerTextField for whenever something has been typed.
	 * @param listener to listen to the timerTextField.
	 * @author Khuram C.
	 */
	public void addListenertoTimerTextField(ActionListener listener) {
		timerTextField.addActionListener(listener);
	}
	
	/**
	 * Adds a listener to the player1ColorsComboBox for whenever a color has been chosen.
	 * @param listener to listen to the player1ColorsComboBox.
	 * @author Khuram C.
	 */
	public void addListenertoPlayer1ColorsComboBox(ActionListener listener) {
		player1ColorsComboBox.addActionListener(listener);
	}
	
	/**
	 * Adds a listener to the player2ColorsComboBox for whenever a color has been chosen.
	 * @param listener to listen to the player1ColorsComboBox.
	 * @author Khuram C.
	 */
	public void addListenertoPlayer2ColorsComboBox(ActionListener listener) {
		player2ColorsComboBox.addActionListener(listener);
	}
	
	/**
	 * Adds the same ActionListener to all of the radioButtons in the buttonGroup.
	 * @param listener to add to the radioButtons.
	 * @author Khuram C.
	 */
	public void addListenertoRadioButtons(ActionListener listener) {
		// based on documentation of enumeration since the getElements method returns an enumeration//
		//https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Enumeration.html
		for(Enumeration<AbstractButton> radioButtons = sizeButtonGroup.getElements(); radioButtons.hasMoreElements();) {
			JRadioButton b = (JRadioButton) radioButtons.nextElement();
			b.addActionListener(listener);
		}
	}
	
	/**
	 * Adds a listener to the startGameButton for whenever one wants the game to start.
	 * @param listener to listen to the startGameButton.
	 * @author Khuram C.
	 */
	public void addListenertoStartGameButton(ActionListener listener) {
		startGameButton.addActionListener(listener);
	}
	
	/**
	 * Returns whether the timerToggleButton has been clicked.
	 * @return bool for the status of the timerToggleButton.
	 * @author Khuram C.
	 */
	public boolean getTimerToggleButtonStatus() {
		return timerToggleButton.isSelected();
	}
	
	/**
	 * Returns the text currently displayed in the timerTextField.
	 * @return the string displayed in the timerTextField.
	 * @author Khuram C.
	 */
	public String getTimerTextFieldValue() {
		return timerTextField.getText();
	}
	
	/**
	 * Sets the text to be displayed in the timerTextField.
	 * @param string to be displayed in the timerTextField.
	 * @author Khuram C.
	 */
	public void setTimerTextFieldValue(String string) {
		timerTextField.setText(string);
	}
	
	/**
	 * Returns the current timerSliderValue.
	 * @return int of timerSliderValue.
	 * @author Khuram C.
	 */
	public int getTimerSliderValue() {
		return timerSlider.getValue();
	}
	
	/**
	 * Sets timerSliderValue based on the input.
	 * @param value to set the timerSliderValue to.
	 * @author Khuram C.
	 */
	public void setTimerSliderValue(int value) {
		timerSlider.setValue(value);
	}
	
	/**
	 * Either makes the timerSlider, timerTextField, and errorLabel visible or invisible based on the input. Also changes the 
	 * text on the timerToggleButton based on whether it is pressed or not.
	 * @param bool to determine whether to make visible or invisible.
	 * @author Khuram C.
	 */
	public void changeTimerViewVisibility(boolean bool) {
		timerSlider.setEnabled(bool);
		timerSlider.setVisible(bool);
		timerTextField.setEnabled(bool);
		timerTextField.setVisible(bool);
		errorLabel.setVisible(bool);
		if(bool) {
			timerToggleButton.setText("Disable Timer");
		}else {
			timerToggleButton.setText("Enable Timer");
		}
	}
	
	/**
	 * Gets the color chosen from the specified PlayerColorsComboBox based on the which player is wanted.
	 * @param playerNum of player to get color choice from.
	 * @return color chosen in the comboBoxChoice.
	 * @author Khuram C.
	 */
	public PlayerColors getPlayerColorsComboBoxChoice(int playerNum) {
		if(playerNum == 1) {
			return (PlayerColors) player1ColorsComboBox.getSelectedItem();
		}else{
			return (PlayerColors) player2ColorsComboBox.getSelectedItem();
		}	
	}
	
	/**
	 * Changes the text of the errorLabel to what is provided.
	 * @param text to show on errorLabel.
	 * @author Khuram C.
	 */
	public void changeErrorLabelText(String text) {
		errorLabel.setText(text);
	}

}
