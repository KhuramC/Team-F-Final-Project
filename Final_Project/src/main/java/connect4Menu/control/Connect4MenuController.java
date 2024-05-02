package connect4Menu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import connect4Menu.exceptions.InvalidTimerTimeException;
import connect4Menu.model.Connect4GameModel;
import connect4Menu.model.Connect4MenuModel;
import connect4Menu.model.Player;
import connect4Menu.model.PlayerColors;
import connect4Menu.view.Connect4GameView;
import connect4Menu.view.Connect4SettingsMenuView;
import mvcinterfaces.MenuController;

/**
 * A Controller from the MVC architecture for the Connect4 menus to play a game
 * of Connect4.
 * 
 * @author Khuram C.
 */
public class Connect4MenuController implements MenuController {

	private Connect4MenuModel model;
	private Connect4SettingsMenuView settingsView;
	private Connect4GameController gameController;

	/**
	 * A parameterized constructor for the controller. There is no need for a
	 * default constructor since the parameters and controller should always be
	 * created at the same time.
	 * 
	 * @param model     to hold the data.
	 * @param startView to show to the user.
	 * @author Khuram C.
	 */
	public Connect4MenuController(Connect4MenuModel model, Connect4SettingsMenuView view) {
		this.model = model;
		this.settingsView = view;
		this.settingsView.addListenertoTimerToggleButton(new TimerToggleButtonListener());
		this.settingsView.addListenertoTimerSlider(new TimerSliderListener());
		this.settingsView.addListenertoTimerTextField(new TimerTextFieldListener());
		this.settingsView.addListenertoPlayer1ColorsComboBox(new Player1ColorsComboBoxListener());
		this.settingsView.addListenertoPlayer2ColorsComboBox(new Player2ColorsComboBoxListener());
		this.settingsView.addListenertoRadioButtons(new SizeRadioButtonListener());
		this.settingsView.addListenertoStartGameButton(new StartGameButtonListener());
	}

	/**
	 * A subclass that listens to the timerToggleButton, gets whether the timer is
	 * being used or not, and adjusts the visibility of the timer settings
	 * accordingly.
	 * 
	 * @author Khuram C.
	 */
	public class TimerToggleButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			changeTimerVisibility(!model.isTimer());
		}

		/**
		 * Official feature method Main feature method. Turns the timer on/off and
		 * changes the visibility of the timer.
		 * 
		 * @param bool for determining visibility of timer.
		 * @author Khuram C.
		 */
		private void changeTimerVisibility(boolean bool) {
			model.setTimer(bool);
			settingsView.changeTimerViewVisibility(bool);
		}
	}

	/**
	 * A subclass that listens to the timerSlider, gets the timer time chosen from
	 * it, and sets the timerTextField value to it.
	 * 
	 * @author Khuram C.
	 */
	public class TimerSliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			int timerSliderValue = settingsView.getTimerSliderValue();
			model.setTimerTime(timerSliderValue);
			settingsView.setTimerTextFieldValue(Integer.toString(timerSliderValue));
		}
	}

	/**
	 * A subclass that listens to the timerTextField, gets the timer time chosen
	 * from it, and sets the timerSlider value to it. Exceptions can occur if a
	 * non-integer value is passed, or if a integer not within the values is passed.
	 * 
	 * @author Khuram C.
	 */
	public class TimerTextFieldListener implements ActionListener {
		// Note: this never directly changes the timer value within the model. Since we
		// change the slider,
		// the TimerSliderListener picks it up having been updated to the new value, and
		// sets the timer time itself.

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int timerTextFieldValue = Integer.parseInt(settingsView.getTimerTextFieldValue());
				if (timerTextFieldValue > Connect4MenuModel.maxTimerTime
						|| timerTextFieldValue < Connect4MenuModel.minTimerTime) {
					throw new InvalidTimerTimeException(Connect4MenuModel.minTimerTime, Connect4MenuModel.maxTimerTime);
				}
				settingsView.setTimerSliderValue(timerTextFieldValue);
				settingsView.changeErrorLabelText("");
			} catch (NumberFormatException exc) {
				settingsView.changeErrorLabelText("Put in an integer!");
			} catch (InvalidTimerTimeException exc) {
				settingsView.changeErrorLabelText(
						"Put in an integer from " + Integer.toString(Connect4MenuModel.minTimerTime) + " to "
								+ Integer.toString(Connect4MenuModel.maxTimerTime) + "s.");
			}
		}
	}

	/**
	 * A subclass that listens to the player1ColorsComboBox and changes the color
	 * for Player 1.
	 * 
	 * @author Khuram C.
	 */
	public class Player1ColorsComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerColors color = settingsView.getPlayerColorsComboBoxChoice(Connect4MenuModel.player1Num);
			changePlayerColor(model.getP1(), color);
		}
	}

	/**
	 * A subclass that listens to the player2ColorsComboBox and changes the color
	 * for Player 2.
	 * 
	 * @author Khuram C.
	 */
	public class Player2ColorsComboBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerColors color = settingsView.getPlayerColorsComboBoxChoice(Connect4MenuModel.player2Num);
			changePlayerColor(model.getP2(), color);
		}
	}

	/**
	 * Official feature method. Changes the given player's color to the given color.
	 * 
	 * @param p     player to have color changed.
	 * @param color to change to.
	 * @author Khuram C.
	 */
	public void changePlayerColor(Player p, PlayerColors color) {
		p.setColor(color);
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
			String text = b.getText();
			int index = text.indexOf('x');
			int rowNum = Integer.parseInt(text, 0, index, 10);
			int colNum = Integer.parseInt(text, index + 1, index + 2, 10);
			changeBoardSize(rowNum, colNum);
		}

		/**
		 * Official feature method. Changes the board size based on the input.
		 * 
		 * @param rowNum or y value.
		 * @param colNum or x value.
		 * @author Khuram C.
		 */
		private void changeBoardSize(int rowNum, int colNum) {
			model.setRowNum(rowNum);
			model.setColNum(colNum);
		}
	}

	/**
	 * 'Starts' the application by making the view visible to the user.
	 * 
	 * @author Khuram C.
	 */
	public void initiate() {
		settingsView.setVisible(true);
	}

}
