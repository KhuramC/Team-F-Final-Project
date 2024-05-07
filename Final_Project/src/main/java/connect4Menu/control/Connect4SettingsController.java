package connect4Menu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import connect4Menu.exceptions.InvalidTimerTimeException;
import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.model.player.IPlayerColors;
import connect4Menu.view.Connect4SettingsView;
import music.MusicLocations;
import music.MusicPlayer;
import mvcinterfaces.MenuController;

/**
 * A Controller from the MVC architecture for the Connect4 menus to play a game
 * of Connect4.
 * 
 * @author Khuram C.
 */
public class Connect4SettingsController implements MenuController {

	private Connect4SettingsModel model;
	private Connect4SettingsView settingsView;

	/**
	 * A default constructor for the controller. Creates the associated Model and View for the settings and adds it as an
	 * Observer to the Model. The associated listeners for the View are also added.
	 * 
	 * @author Khuram C.
	 */
	public Connect4SettingsController() {
		this.model = new Connect4SettingsModel();
		this.settingsView = new Connect4SettingsView();
		
		model.addObserver(settingsView);
		settingsView.addListenertoTimerToggleButton(new TimerToggleButtonListener());
		settingsView.addListenertoTimerSlider(new TimerSliderListener());
		settingsView.addListenertoTimerTextField(new TimerTextFieldListener());
		settingsView.addListenertoPlayerColorsComboBoxes(new PlayerColorsComboBoxListener());
		settingsView.addListenertoRadioButtons(new SizeRadioButtonListener());
		settingsView.addListenertoStartGameButton(new StartGameButtonListener());
	}

	/**
	 * A subclass that listens to the timerToggleButton and sets its status in the model.
	 * 
	 * @author Khuram C.
	 */
	public class TimerToggleButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.toggleTimer();
		}
	}

	/**
	 * A subclass that listens to the timerSlider and gets/sets the timer time chosen.
	 * @author Khuram C.
	 */
	public class TimerSliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			int timerSliderValue = settingsView.getTimerSliderValue();
			model.setTimerTime(timerSliderValue);
		}
	}

	/**
	 * A subclass that listens to the timerTextField and gets/sets the timer time chosen. 
	 * Exceptions can occur if a non-integer value is passed, or if a integer not within the range is passed.
	 * 
	 * @author Khuram C.
	 */
	public class TimerTextFieldListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField textField = (JTextField) e.getSource();
			parseForTimerTime(textField.getText());	
		}
		
		/**
		 * Parses the String given to see if it's an integer and if it's within range. If so, it'll change the value in the model.
		 * Otherwise, it will throw an exception. 
		 * @param timerTextFieldText text from timerTextField.
		 * @return String of errorLabel.
		 * @author Khuram C.
		 */
		public String parseForTimerTime(String timerTextFieldText){
			try {
				int timerTextFieldValue = Integer.parseInt(timerTextFieldText);
				if (timerTextFieldValue > Connect4SettingsModel.maxTimerTime
						|| timerTextFieldValue < Connect4SettingsModel.minTimerTime) {
					throw new InvalidTimerTimeException(Connect4SettingsModel.minTimerTime, Connect4SettingsModel.maxTimerTime);
				}
				model.setTimerTime(timerTextFieldValue);
				settingsView.changeErrorLabelText("");
				
			} catch (NumberFormatException exc) {
				settingsView.changeErrorLabelText("Put in an integer!");
			} catch (InvalidTimerTimeException exc) {
				settingsView.changeErrorLabelText("Put in an integer from " + Integer.toString(Connect4SettingsModel.minTimerTime) 
				+ " to " + Integer.toString(Connect4SettingsModel.maxTimerTime) + "s.");
			}
			return settingsView.getErrorLabelText();
		}
	}

	
	
	/**
	 * A subclass that listens to the playerColorsComboBox and changes the color
	 * for the associated player.
	 * 
	 * @author Khuram C.
	 */
	public class PlayerColorsComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			changeColor((JComboBox<IPlayerColors>) e.getSource());
		}
		
		/**
		 * Changes the color of the player associated with the playerBox.
		 * @param comboBox to get color from.
		 * @return boolean detailing success.
		 * @author Khuram C.
		 */
		public boolean changeColor(JComboBox<IPlayerColors> comboBox) {
			IPlayerColors color = (IPlayerColors) comboBox.getSelectedItem();
			model.changePlayerColor(color.getAllowedPlayer(), color);
			return true;
		}
	}

	/**
	 * A subclass that listens to the startGameButton, closes the settingsView, and starts the game.
	 * 
	 * @author Khuram C.
	 */
	public class StartGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
			startConnect4Round();
		}
		
		/**
		 * Starts a round of Connect4 by calling initiate in the gameController.
		 * @return boolean detailing success.
		 * @author Khuram C.
		 */
		public boolean startConnect4Round() {
			MusicPlayer.getInstance().pauseMusic();
			Connect4GameController gameController = new Connect4GameController(model);
			gameController.initiate();
			settingsView.dispatchEvent(new WindowEvent(settingsView, WindowEvent.WINDOW_CLOSING));
			
			return true;
			
		}
	}

	/**
	 * A subclass that listens to the RadioButtons and changes the size of the board
	 * based on the selection.
	 * 
	 * @author Khuram C.
	 */
	public class SizeRadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton b = (JRadioButton) e.getSource();
			parseButtontoSetBoardSize(b);
		}
		/**
		 * Parses a button's text (in the form of 'rowNumxcolNum' to get the board size and set it.
		 * @param button to parse.
		 * @return boolean detailing success of setting board size.
		 * @author Khuram C.
		 */
		public boolean parseButtontoSetBoardSize(JRadioButton b) {
			String text = b.getText();
			int index = text.indexOf('x');
			int rowNum = Integer.parseInt(text, 0, index, 10);
			int colNum = Integer.parseInt(text, index + 1, index + 2, 10);
			return model.setBoardSize(rowNum, colNum);
		}

	}

	/**
	 * 'Starts' the application by making the view visible to the user.
	 * 
	 * @author Khuram C.
	 */
	public boolean initiate() {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MusicPlayer.getInstance().playMusic(MusicLocations.CONNECT4SETTINGS.getMusicFilePath());
				settingsView.setVisible(true);
			}
		});
		return true;
		
		
	}

}
