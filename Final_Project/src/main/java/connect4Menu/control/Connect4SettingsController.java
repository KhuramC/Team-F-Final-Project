package connect4Menu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import connect4Menu.exceptions.InvalidTimerTimeException;
import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.model.Player;
import connect4Menu.model.PlayerColors;
import connect4Menu.view.Connect4SettingsView;
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
	private Connect4GameController gameController;

	/**
	 * A default constructor for the controller.  
	 * The associated listeners for the view are also added. The view is also added as an observer to the model.
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
			try {
				int timerTextFieldValue = Integer.parseInt(settingsView.getTimerTextFieldValue());
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
			changeColor((JComboBox<PlayerColors>) e.getSource());
		}
		
		public boolean changeColor(JComboBox<PlayerColors> comboBox) {
			PlayerColors color = (PlayerColors) comboBox.getSelectedItem();
			model.changePlayerColor(color.getAllowedPlayer(), color);
			return true;
		}
	}

	/**
	 * A subclass that listens to the startGameButton, closes the settingsView, and
	 * opens the gameView.
	 * 
	 * @author Khuram C.
	 */
	public class StartGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameController = new Connect4GameController(model);
			gameController.initiate();
			settingsView.dispatchEvent(new WindowEvent(settingsView, WindowEvent.WINDOW_CLOSING));
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
	public void initiate() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				settingsView.setVisible(true);
			}
		});
		
	}

}
