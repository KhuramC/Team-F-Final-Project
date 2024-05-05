package connect4Menu.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import connect4Menu.model.observableinterfaces.*;
import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.IEndGameObserver;
import connect4Menu.view.observerinterfaces.IInvalidColsObserver;
import connect4Menu.view.observerinterfaces.ISquarePlayedObserver;
import connect4Menu.view.observerinterfaces.IStartedTurnObserver;

/**
 * A Model from the MVC architecture for actually playing a round of Connect4. The model observes the CountDownTimer
 * and is observed by the Connect4GameView. 
 * @author Khuram C.
 */
public class Connect4GameModel
		implements Observer, IStartedTurnObservable, ISquarePlayedObservable, IEndGameObservable, IInvalidColsObservable {

	private Player p1;
	private Player p2;
	private final int[][] board;
	private boolean isTimer;
	private CountDownTimer timer;
	private int[] selected = new int[2];
	private ArrayList<IStartedTurnObserver> startedTurnObservers = new ArrayList<>();
	private ArrayList<ISquarePlayedObserver> squarePlayedObservers = new ArrayList<>();
	private ArrayList<IEndGameObserver> endGameObservers = new ArrayList<>();
	private ArrayList<IInvalidColsObserver> invalidColsObservers = new ArrayList<>();

	/**
	 * A parameterized Constructor. The gameModel requires values from the menuModel. It also starts
	 * Player 1's turn.
	 * @param settingsModel to get setting values from.
	 * @author Khuram C.
	 */
	public Connect4GameModel(Connect4SettingsModel settingsModel) {
		isTimer = settingsModel.isTimer();
		if (isTimer) {
			timer = new CountDownTimer(settingsModel.getTimerTime());
		}
		p1 = settingsModel.getPlayer(1);
		p2 = settingsModel.getPlayer(2);
		int rowNum = settingsModel.getRowNum();
		int colNum = settingsModel.getColNum();
		board = new int[rowNum][colNum];
		p1.setTurn(true);
	}

	/**
	 * Check's if it is p1's turn. If not, it is p2's turn. There is no point in which both or neither are in their turn,
	 * so this should always work as expected.
	 * @return player whose turn it is.
	 * @author Khuram C.
	 */
	public Player whoseTurn() {
		if (p1.isTurn()) {
			return p1;
		}
		return p2;
	}
	
	/**
	 * Changes the turns of both players.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	private boolean changeTurns() {
		p1.setTurn(!p1.isTurn());
		p2.setTurn(!p2.isTurn());
		return true;
	}

	/**
	 * Checks if the given colNum can be selected for a turn.
	 * @param colNum to check.
	 * @return boolean detailing selection availability.
	 * @author Khuram C.
	 */
	public boolean isValidSelection(int colNum) {
		return (board[0][colNum] == 0);
	}


	/**
	 * Starts whoever's turn it is by notifying the observers that their turn has started, and 
	 * starting the timer if it is enabled.
	 * @return boolean detailing successful start of turn.
	 * @author Khuram C.
	 */
	public boolean startTurn() {
		notifyStartedTurnObservers(whoseTurn());
		if (isTimer) {
			timer.startTimer();
		}
		return true;
	}
	

	/**
	 * Gets the correct rowNum given a selected column. This method runs under the assumption that only valid selectedColNums
	 * are picked, which is checked by other methods.
	 * @param selectedColNum colNum picked.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean select(int selectedColNum) {
		selected[1] = selectedColNum;
		for (int row = board.length - 1; row >= 0; row--) {
			if (board[row][selectedColNum] == 0) {
				selected[0] = row;
				if (row == 0) {
					notifyInvalidColObservers(selectedColNum);
				}
				break;
			}
		}
		playTurn();
		return true;
	}
	
	/**
	 * Returns the selected cell. The first element corresponds with the rowNum, the second the colNum.
	 * @return int array representing cell location.
	 * @author Khuram C.
	 */
	public int[] getSelection() {
		return selected;
	}
	
	/**
	 * Determines if there is a valid turn available (i.e. if there are any empty spots that have not been
	 * played on yet).
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean isTurnAvailable() {
		int colNum = board[0].length;
		for (int col = 0; col < colNum; col++) {
			if(isValidSelection(col)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Plays a turn, given that a selection has been made. After, it notifies observers that a cell has been played,
	 * and then checks if the game has ended because of it.
	 * @return boolean detailing successful turn played.
	 * @author Khuram C.
	 */
	private boolean playTurn() {
		int[] choice = getSelection();
		int rowNum = choice[0];
		int colNum = choice[1];
		board[rowNum][colNum] = whoseTurn().getPlayerNum();
		notifySquarePlayedObservers(whoseTurn());
		checkEndGame();
		return true;
	}
	
	/**
	 * Checks if the game has won by the player's latest move. If so, it'll pause the timer if used, and then
	 * notify observers that the game has ended due to that player winning. If not, it'll check for a potential tie. If there
	 * is, it'll notify the observers and pause the timer if used. Lastly, if the game is still going,
	 * just change turns and start the next player's turn.
	 * @return detailing successful check.
	 * @author Khuram C.
	 */
	private boolean checkEndGame() {
		if (WinnerAlgoSingleton.getInstance().isWinner(whoseTurn().getPlayerNum(),board)) {
			if (isTimer) {
				timer.pauseTimer();
			}
			notifyEndGameObservers(whoseTurn());
		} else if (!isTurnAvailable()) {
			notifyEndGameObservers(null);
			if (isTimer) {
				timer.pauseTimer();
			}
		} else {
			changeTurns();
			startTurn();
		}
		return true;
	}

	/**
	 * Helper method for selectRandom. Determines the valid colNums that can be played.
	 * @return ArrayList of integers(colNums).
	 * @author Khuram C.
	 */
	private ArrayList<Integer> getValidCols() {
		ArrayList<Integer> validColsList = new ArrayList<>();
		for(int col = 0; col < board[0].length;col++) {
			if(isValidSelection(col)) {
				validColsList.add(col);
			}
		}
		return validColsList;	
	}
	
	/**
	 * Selects a random valid column. Meant for whenever the timer runs out and a player still has not chosen.
	 * @return boolean detailing successful selection of random column.
	 * @author Khuram C.
	 */
	private boolean selectRandom() {
		Random rand = new Random();
		ArrayList<Integer> validColNums = getValidCols();
		int randomValidColChoice = validColNums.get(rand.nextInt(validColNums.size()));
		select(randomValidColChoice);
		return true;
	}
	

	/**
	 * If the timer has reached the end of its count, then it selects randomly for the player.
	 * @author Khuram C.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			selectRandom();
		}
	}

	/**
	 * Adds an observer to the timer.
	 * @param o observer to observe timer.
	 * @return boolean detailing successful adding.
	 * @author Khuram C.
	 */
	public boolean addObservertoTimer(Observer o) {
		timer.addObserver(o);
		return true;
	}

	@Override
	public boolean registerStartedTurnObserver(IStartedTurnObserver o) {
		startedTurnObservers.add(o);
		return true;
	}

	@Override
	public boolean notifyStartedTurnObservers(Player p) {
		for (IStartedTurnObserver o : startedTurnObservers) {
			o.updatePlayerTurnText(p);
		}
		return true;
	}

	@Override
	public boolean registerSquarePlayedObserver(ISquarePlayedObserver o) {
		squarePlayedObservers.add(o);
		return true;
	}

	@Override
	public boolean notifySquarePlayedObservers(Player p) {
		for (ISquarePlayedObserver o : squarePlayedObservers) {
			o.updateBoard(p, selected);
		}
		return true;
	}

	@Override
	public boolean registerEndGameObserver(IEndGameObserver o) {
		endGameObservers.add(o);
		return true;
	}

	@Override
	public boolean notifyEndGameObservers(Player p) {
		for (IEndGameObserver o : endGameObservers) {
			o.updateTextWithWinner(p);
		}
		return true;
	}

	@Override
	public boolean registerInvalidColObserver(IInvalidColsObserver o) {
		invalidColsObservers.add(o);
		return true;
	}

	@Override
	public boolean notifyInvalidColObservers(int colNum) {
		for (IInvalidColsObserver o : invalidColsObservers) {
			o.updateButton(colNum);
		}
		return true;
	}
}
