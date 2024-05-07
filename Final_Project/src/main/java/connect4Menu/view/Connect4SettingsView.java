package connect4Menu.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.model.player.IPlayerColors;
import connect4Menu.model.player.Player1Colors;
import connect4Menu.model.player.Player2Colors;

import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Component;

/**
 * A View from the MVC architecture used for choosing the settings of the
 * Connect4 game. The View observes the Connect4SettingsModel.
 * 
 * @author Khuram C.
 */
public class Connect4SettingsView extends JFrame implements Observer {

	private JPanel contentPane = new JPanel();
	private final ButtonGroup sizeButtonGroup = new ButtonGroup();
	private JToggleButton timerToggleButton;
	private JSlider timerSlider;
	private JTextField timerTextField;
	private ArrayList<JComboBox<IPlayerColors>> playerColorsComboBoxes = new ArrayList<>(2);
	private JButton startGameButton;
	//for JUnit testing.
	protected JLabel errorLabel;

	/**
	 * Default constructor for the Connect4SettingsMenuView. Constructs the View and
	 * the associated objects necessary for the View. Because it should look the
	 * same every time, there is only the default constructor.
	 * 
	 * @author Khuram C.
	 */
	public Connect4SettingsView() {
		// general settings//
		setTitle("Connect 4 Settings"); 
		int windowWidth = 900;
		int windowHeight = 400;
		setBounds(300, 100, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(new Color(255, 215, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		
		
		int middleX = windowWidth/2;
		//Title
		int titleLabelWidth = 500;
		int titleLabelHeight = 40;
		int titleLabelY = 10;
		JLabel titleLabel = new JLabel();
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setOpaque(false);
		titleLabel.setForeground(new Color(255, 215, 0));
		titleLabel.setFont(new Font("Snap ITC", Font.ITALIC, 29));
		titleLabel.setText("CONNECT4 SETTINGS");
		titleLabel.setSize(titleLabelWidth, titleLabelHeight);
		titleLabel.setLocation(middleX- titleLabelWidth/2, titleLabelY);
		contentPane.add(titleLabel);
		

		int rulesTextPaneWidth = 500;
		int rulesTextPaneHeight = 200;
		int rulesTextPaneY = titleLabelHeight+titleLabelY+10;
		JTextPane rulesTextPane = new JTextPane();
		rulesTextPane.setSize(rulesTextPaneWidth,rulesTextPaneHeight);
		rulesTextPane.setLocation(middleX-rulesTextPaneWidth/2, rulesTextPaneY);
		rulesTextPane.setEditable(false);
		rulesTextPane.setOpaque(false);
		rulesTextPane.setForeground(new Color(255, 215, 0));
		rulesTextPane.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
		StyledDocument documentStyle = rulesTextPane.getStyledDocument();
		SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
		documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);
		rulesTextPane.setText("Welcome to Connect4! The rules are simple:\n"
				+ "Two players play turns in succession to try to get\n"
				+ "four circles in a row\n"
				+ "horizontally, vertically, or diagonally.\n"
				+ "One's circle has to be played on top of another circle\n"
				+ "if it is not going to the bottom row.\n"
				+ "After a full column has been played on,\n"
				+ "no more circles can be played on it.\n"
				+ "Many different board sizes are available to choose from.\n"
				+ "A timer can also be selected to force a turn\n"
				+ " to be taken within 15 to 60 seconds.\n "
				+ "Lastly, each player can configure what color they want.\n"
				+ "Press 'Play Connect4' to start!");
		
		contentPane.add(rulesTextPane);
		
		int startGameButtonWidth = 200;
		int startGameButtonHeight = 50;
		startGameButton = new JButton("Play Connect4");
		startGameButton.setSize(startGameButtonWidth, startGameButtonHeight);
		startGameButton.setLocation(middleX-startGameButtonWidth/2, rulesTextPaneY+rulesTextPaneHeight + 20);
		startGameButton.setForeground(new Color(255, 215, 0));
		startGameButton.setBackground(Color.BLACK);
		startGameButton.setFont(new Font("Britannic Bold", Font.PLAIN, 23));
		startGameButton.setToolTipText("Click this to start a round of Connect4!");
		contentPane.add(startGameButton);
		
		
		int leftMiddleX = 115;
		//boardSize//
		//sizeLabel
		int sizeSettingsLabelWidth = 175;
		int sizeSettingsLabelHeight = 20;
		int sizeSettingsLabelY = 38;
		JLabel sizeSettingsLabel = new JLabel("Size Settings (RowxCol)");
		sizeSettingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sizeSettingsLabel.setForeground(new Color(255, 215, 0));
		sizeSettingsLabel.setFont(new Font("Britannic Bold", Font.ITALIC, 14));
		sizeSettingsLabel.setSize(sizeSettingsLabelWidth,sizeSettingsLabelHeight);
		sizeSettingsLabel.setLocation(leftMiddleX-sizeSettingsLabelWidth/2, sizeSettingsLabelY);
		contentPane.add(sizeSettingsLabel);
		
		//RadioButtons//
		String[] sizes = {"4x5","5x6","6x7(Standard)","7x8","8x8","7x9"};
		int startingRadioButtonY = sizeSettingsLabelHeight+sizeSettingsLabelY + 5;
		int radioButtonWidth = 130;
		int radioButtonHeight= 20;
		int finalRadioButtonY = 0;
		for(int i = 0; i < sizes.length; i++) {
			int radioButtonY = startingRadioButtonY+(radioButtonHeight*i);
			UIManager.put("RadioButton.focus",new Color(255, 215, 0));
			JRadioButton button = new JRadioButton(sizes[i]);
			if(sizes[i].equals("6x7(Standard)")){
				button.setSelected(true);
			}
			button.setOpaque(false);
			button.setForeground(new Color(255, 215, 0));
			button.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
			sizeButtonGroup.add(button);
			button.setSize(radioButtonWidth, radioButtonHeight);
			button.setLocation(leftMiddleX-radioButtonWidth/2, radioButtonY);
			finalRadioButtonY = radioButtonY;
			contentPane.add(button);
		}
		
		//Timer
		//TimerToggleButton
		int timerToggleButtonWidth = 150;
		int timerToggleButtonHeight = 30;
		int timerToggleButtonY = finalRadioButtonY + 25;
		UIManager.put("ToggleButton.select", new Color(255, 215, 0));
		timerToggleButton = new JToggleButton("Enable Timer");
		timerToggleButton.setForeground(new Color(255, 215, 0));
		timerToggleButton.setBackground(Color.BLACK);
		timerToggleButton.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		timerToggleButton.setToolTipText("Click this to start a round of Connect4!");
		timerToggleButton.setSize(timerToggleButtonWidth, timerToggleButtonHeight);
		timerToggleButton.setLocation(leftMiddleX-timerToggleButtonWidth/2, timerToggleButtonY);
		contentPane.add(timerToggleButton);

		
		//TimerSlider
		int timerSliderWidth = 175;
		int timerSliderHeight = 25;
		int timerSliderY = timerToggleButtonY+50;
		timerSlider = new JSlider();
		timerSlider.setEnabled(false);
		timerSlider.setVisible(false);
		timerSlider.setBounds(10, 282, 200, 26);
		timerSlider.setEnabled(false);
		timerSlider.setOpaque(false);
		timerSlider.setForeground(new Color(255, 215, 0));
		timerSlider.setMaximum(Connect4SettingsModel.maxTimerTime);
		timerSlider.setMinimum(Connect4SettingsModel.minTimerTime);
		timerSlider.setValue(Connect4SettingsModel.minTimerTime);
		timerSlider.setSize(timerSliderWidth,timerSliderHeight);
		timerSlider.setLocation(leftMiddleX-timerSliderWidth/2, timerSliderY);
		contentPane.add(timerSlider);

		//TimerTextField
		int timerTextFieldWidth = 25;
		int timerTextFieldHeight = 20;
		int timerTextFieldY = timerSliderY + 25;
		timerTextField = new JTextField();
		timerTextField.setText(Integer.toString(Connect4SettingsModel.minTimerTime));
		timerTextField.setEnabled(false);
		timerTextField.setVisible(false);
		timerTextField.setFont(new Font("Britannic Bold", Font.PLAIN, 13));
		timerTextField.setSize(timerTextFieldWidth, timerTextFieldHeight);
		timerTextField.setLocation(leftMiddleX-timerTextFieldWidth/2, timerTextFieldY);
		contentPane.add(timerTextField);
		timerTextField.setColumns(10);

		//ErrorLabel
		int errorLabelWidth = 200;
		int errorLabelHeight = 15;
		int errorLabelY = timerTextFieldY + 35;
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Britannic Bold", Font.ITALIC, 13));
		errorLabel.setForeground(getForeground());
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setSize(errorLabelWidth, errorLabelHeight);
		errorLabel.setLocation(leftMiddleX-errorLabelWidth/2, errorLabelY);
		contentPane.add(errorLabel);
		
		
		
		int rightMiddleX = windowWidth - 115;
		//Colors
		//Player1Label
		int playerLabelWidth = 100;
		int playerLabelHeight = 15;
		int player1LabelY = 100; 
		JLabel player1Label = new JLabel("Player 1 Colors");
		player1Label.setFont(new Font("Britannic Bold", Font.ITALIC, 13));
		player1Label.setForeground(getForeground());
		player1Label.setHorizontalAlignment(SwingConstants.CENTER);
		player1Label.setSize(playerLabelWidth, playerLabelHeight);
		player1Label.setLocation(rightMiddleX-playerLabelWidth/2, player1LabelY);
		contentPane.add(player1Label);

		//Player1ComboBox
		int comboBoxWidth = 100;
		int comboBoxHeight = 25;
		int player1ComboBoxY = player1LabelY+25;
		JComboBox<IPlayerColors> player1ColorsComboBox = new JComboBox<>();
		player1ColorsComboBox.setModel(new DefaultComboBoxModel<>(Player1Colors.values()));
		player1ColorsComboBox.setBackground(new Color(0, 0, 0));
		player1ColorsComboBox.setForeground(new Color(255, 215, 0));
		player1ColorsComboBox.setFocusable(false);
		player1ColorsComboBox.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		player1ColorsComboBox.setSize(comboBoxWidth, comboBoxHeight);
		player1ColorsComboBox.setLocation(rightMiddleX-comboBoxWidth/2, player1ComboBoxY);
		playerColorsComboBoxes.add(player1ColorsComboBox);
		contentPane.add(player1ColorsComboBox);

		//Player2Label
		int player2LabelY = player1ComboBoxY+100; 
		JLabel player2Label = new JLabel("Player 2 Colors");
		player2Label.setFont(new Font("Britannic Bold", Font.ITALIC, 13));
		player2Label.setForeground(getForeground());
		player2Label.setHorizontalAlignment(SwingConstants.CENTER);
		player2Label.setSize(playerLabelWidth, playerLabelHeight);
		player2Label.setLocation(rightMiddleX-playerLabelWidth/2, player2LabelY);
		contentPane.add(player2Label);

		//Player2ComboBox
		int player2ComboBoxY = player2LabelY+25;
		JComboBox<IPlayerColors> player2ColorsComboBox = new JComboBox<>();
		player2ColorsComboBox.setModel(new DefaultComboBoxModel<>(Player2Colors.values()));
		player2ColorsComboBox.setBackground(new Color(0, 0, 0));
		player2ColorsComboBox.setForeground(new Color(255, 215, 0));
		player2ColorsComboBox.setFocusable(false);
		player2ColorsComboBox.setFont(new Font("Britannic Bold", Font.PLAIN, 15));
		player2ColorsComboBox.setSize(comboBoxWidth, comboBoxHeight);
		player2ColorsComboBox.setLocation(rightMiddleX-comboBoxWidth/2, player2ComboBoxY);
		playerColorsComboBoxes.add(player2ColorsComboBox);
		contentPane.add(player2ColorsComboBox);
	}

	/**
	 * Adds a listener to the timerToggleButton for whenever it is pressed.
	 * 
	 * @param listener to listen to the timerToggleButton.
	 * @author Khuram C.
	 */
	public void addListenertoTimerToggleButton(ActionListener listener) {
		timerToggleButton.addActionListener(listener);
	}

	/**
	 * Adds a listener to the timerSlider for whenever it is changed.
	 * 
	 * @param listener to listen to timerSlider.
	 * @author Khuram C.
	 */
	public void addListenertoTimerSlider(ChangeListener listener) {
		timerSlider.addChangeListener(listener);
	}

	/**
	 * Adds a listener to the timerTextField for whenever something has been typed.
	 * 
	 * @param listener to listen to the timerTextField.
	 * @author Khuram C.
	 */
	public void addListenertoTimerTextField(ActionListener listener) {
		timerTextField.addActionListener(listener);
	}
	
	/**
	 * Adds a listener to the playerColorsComboBoxes for whenever a color has been chosen.
	 * @param listener to listen to the playerColorsCombBoxes.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean addListenertoPlayerColorsComboBoxes(ActionListener listener) {
	
		for(JComboBox<IPlayerColors> comboBox : playerColorsComboBoxes) {
			comboBox.addActionListener(listener);
		}
		return true;

	}

	/**
	 * Adds the same ActionListener to all of the radioButtons in the buttonGroup.
	 * 
	 * @param listener to add to the radioButtons.
	 * @author Khuram C.
	 */
	public void addListenertoRadioButtons(ActionListener listener) {
		// based on documentation of enumeration since the getElements method returns an
		// enumeration//
		// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Enumeration.html
		for (Enumeration<AbstractButton> radioButtons = sizeButtonGroup.getElements(); radioButtons
				.hasMoreElements();) {
			JRadioButton b = (JRadioButton) radioButtons.nextElement();
			b.addActionListener(listener);
		}
	}

	/**
	 * Adds a listener to the startGameButton for whenever one wants the game to
	 * start.
	 * 
	 * @param listener to listen to the startGameButton.
	 * @author Khuram C.
	 */
	public void addListenertoStartGameButton(ActionListener listener) {
		startGameButton.addActionListener(listener);
	}

	/**
	 * Returns the text currently displayed in the timerTextField.
	 * 
	 * @return the string displayed in the timerTextField.
	 * @author Khuram C.
	 */
	public String getTimerTextFieldValue() {
		return timerTextField.getText();
	}

	/**
	 * Returns the current timerSliderValue.
	 * 
	 * @return int of timerSliderValue.
	 * @author Khuram C.
	 */
	public int getTimerSliderValue() {
		return timerSlider.getValue();
	}

	/**
	 * Sets the value of the timerSlider and timerTextField based on the input.
	 * @param value to set timer objects to.
	 * @return boolean detailing success
	 * @author Khuram C.
	 */
	private boolean setTimerVisualValue(int value) {
		timerSlider.setValue(value);
		timerTextField.setText(Integer.toString(value));
		return true;
	}
	/**
	 * Either makes the timerSlider, timerTextField, and errorLabel visible or
	 * invisible based on the input. Also changes the text/color on the timerToggleButton
	 * based on whether it is pressed or not.
	 * 
	 * @param bool to determine whether to make visible or invisible.
	 * @author Khuram C.
	 */
	private void changeTimerViewVisibility(boolean bool) {
		timerSlider.setEnabled(bool);
		timerSlider.setVisible(bool);
		timerTextField.setEnabled(bool);
		timerTextField.setVisible(bool);
		errorLabel.setVisible(bool);
		if (bool) {
			timerToggleButton.setText("Disable Timer");
			timerToggleButton.setForeground(Color.BLACK);
		} else {
			timerToggleButton.setText("Enable Timer");
			timerToggleButton.setForeground(new Color(255, 215, 0));
		}
	}

	/**
	 * Changes the text of the errorLabel to what is provided.
	 * 
	 * @param text to show on errorLabel.
	 * @author Khuram C.
	 */
	public void changeErrorLabelText(String text) {
		errorLabel.setText(text);
	}
	
	/**
	 * Returns the text held in the errorLabel.
	 * @return String in errorLabel.
	 * @author Khuram C.
	 */
	public String getErrorLabelText() {
		return errorLabel.getText();
	}

	/**
	 * Update method for when the timer is toggled, or whenever the time set for the timer has been changed.
	 * @author Khuram C.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Boolean) {
			Boolean isTimer = (Boolean) arg;
			changeTimerViewVisibility(isTimer);
		}else {
			int timerTime = (int) arg;
			setTimerVisualValue(timerTime);
		}
	}

}
