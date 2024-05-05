package connect4Menu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import connect4Menu.model.Connect4GameModel;
import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.view.Connect4GameView;
import mvcinterfaces.MenuController;

/**
 * A Controller from the MVC architecture to actually play a round of Connect4.
 * @author Khuram C.
 */
public class Connect4GameController implements MenuController {

	private Connect4GameModel gameModel;
	private Connect4GameView gameView;

	/**
	 * A parameterized Constructor is required for this controller due the Connect4GameModel needing it. Creates
	 * the associated Model and View, adds the View as an observer to the Model,
	 * as well as adding both as observers to the CountDownTimer if enabled. Lastly listeners are also added to the View.
	 * @param settingsModel to get Connect4 settings from.
	 * @author Khuram C.
	 */
	public Connect4GameController(Connect4SettingsModel settingsModel) {
		gameModel = new Connect4GameModel(settingsModel);
		gameView = new Connect4GameView(settingsModel.getRowNum(), settingsModel.getColNum(), settingsModel.getTimerTime());
		if (settingsModel.isTimer()) {
			gameModel.addObservertoTimer(gameModel);
			gameModel.addObservertoTimer(gameView);
		}

		gameView.setTimerLabelVisibility(settingsModel.isTimer());
		gameView.addListenertoSelectionButtons(new SelectionButtonListener());
		gameModel.registerStartedTurnObserver(gameView);
		gameModel.registerSquarePlayedObserver(gameView);
		gameModel.registerEndGameObserver(gameView);
		gameModel.registerInvalidColObserver(gameView);
	}

	/**
	 * A subclass that listens to the SelectionButtons and selects the corresponding column number in the model.
	 * @author Khuram C.
	 */
	public class SelectionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			parseForColNum(b);
		}
		
		/**
		 * Parses the button for the column number to set in the model.
		 * @param b JButton to parse.
		 * @return boolean detailing successful parsing.
		 * @author Khuram C.
		 */
		public boolean parseForColNum(JButton b) {
			int colNum = Integer.parseInt(b.getText(), 4, 5, 10) - 1;
			gameModel.select(colNum);
			return true;
		}
	}

	/**
	 * Makes the view visible to the user, and then starts Player 1's turn.
	 */
	@Override
	public boolean initiate() {
		gameView.setVisible(true);
		gameModel.startTurn();
		return true;
	}

}
