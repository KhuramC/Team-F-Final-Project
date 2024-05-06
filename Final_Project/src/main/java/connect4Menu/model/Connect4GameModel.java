package connect4Menu.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import connect4Menu.model.player.Player;
import connect4Menu.view.ObserverEndGame;
import connect4Menu.view.ObserverInvalidCols;
import connect4Menu.view.ObserverSquarePlayed;
import connect4Menu.view.ObserverStartedTurn;

public class Connect4GameModel
		implements Observer, ObservableStartedTurn, ObservableSquarePlayed, ObservableEndGame, ObservableInvalidCols {

	private Player p1;
	private Player p2;
	private final int[][] board;
	private boolean isTimer;
	private CountDownTimer timer;
	private int[] selected = new int[2];
	private boolean hasSelected = false;
	private ArrayList<ObserverStartedTurn> startedTurnObservers = new ArrayList<>();
	private ArrayList<ObserverSquarePlayed> squarePlayedObservers = new ArrayList<>();
	private ArrayList<ObserverEndGame> endGameObservers = new ArrayList<>();
	private ArrayList<ObserverInvalidCols> invalidColsObservers = new ArrayList<>();

	// need to observe whenever there's a winner or tie(reflect in view)//

	public Connect4GameModel(Connect4SettingsModel menuModel) {
		if (menuModel.isTimer()) {
			this.timer = new CountDownTimer(menuModel.getTimerTime());

		}
		this.isTimer = menuModel.isTimer();
		// timer.addObserver(this);
		
		p1 = menuModel.getPlayer(1);
		p2 = menuModel.getPlayer(2);
		int rowNum = menuModel.getRowNum();
		int colNum = menuModel.getColNum();
		board = new int[rowNum][colNum];
		p1.setTurn(true);

	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setWinner(Player p) {
		p.setWinner();
	}

	public void changeTurns() {
		p1.setTurn(!p1.isTurn());
		p2.setTurn(!p2.isTurn());
	}

	public Player whoseTurn() {
		if (p1.isTurn()) {
			return p1;
		}
		return p2;
	}

	// need to have isSelectionValid and to actualyl determine it.
	// under assuimption that the column selected is valid. this is not checked
	// here.
	public void select(int selection) {
		selected[1] = selection;
		for (int row = board.length - 1; row >= 0; row--) {
			if (board[row][selection] == 0) {
				selected[0] = row;
				if (row == 0) {
					notifyInvalidColObservers(selection);
				}
				break;
			}
		}
		hasSelected = true;
		System.out.println(
				"Row Num = " + Integer.toString(selected[0] + 1) + " Col Num = " + Integer.toString(selected[1] + 1));
		playTurn();

	}

	public boolean isValidSelection(int colNum) {
		if (board[0][colNum] != 0) {
			return false;
		}
		return true;
	}

	public int[] getSelection() {
		return selected;
	}

	public void startTurn() {
		Player p = whoseTurn();
		hasSelected = false;
		notifyStartedTurnObservers(p);
		if (isTimer) {
			System.out.println(p);
			timer.startTimer();
		}

	}

	public boolean isTurnAvailable() {
		for (int cell : board[0]) {
			if (cell == 0) {
				return true;
			}
		}
		return false;

	}

	public void playTurn() {
		int[] choice = getSelection();
		int rowNum = choice[0];
		int colNum = choice[1];
		board[rowNum][colNum] = whoseTurn().getPlayerNum();
		notifySquarePlayedObservers(whoseTurn());
		if (isWinner(whoseTurn())) {
			System.out.println("Yessir!");
			if (isTimer) {
				timer.pauseTimer();

			}
			notifyEndGameObservers(whoseTurn());
		} else if (!isTurnAvailable()) {
			System.out.println("TIE!");
			notifyEndGameObservers(null);
			if (isTimer) {
				timer.pauseTimer();

			}

		} else {
			changeTurns();
			startTurn();
		}

	}

	public boolean isWinner(Player p) {
		int playerNum = p.getPlayerNum();
		if (isDiagonal(playerNum) || isVertical(playerNum) || isHorizontal(playerNum)) {
			p.setWinner();
			System.out.println(p);
			System.out.println("is a winer!");
			return true;
		}

		return false;

	}

	private boolean isDiagonal(int num) {
		int rowNum = board.length;
		int colNum = board[0].length;
		for (int row = 0; row < rowNum - 3; row++) {
			// down and right diagonals
			for (int col = 0; col < (colNum / 2) + 1; col++) {
				if (col + 3 >= colNum) {
					continue;
				}
				if (board[row][col] != num) {
					continue;
				}
				if (checkDiagonal(num, row, col, false)) {
					return true;
				}
			}
			// down and left diagonals
			for (int col = colNum - 1; col > colNum / 2; col--) {
				if (col - 3 <= 0) {
					continue;
				}
				if (board[row][col] != num) {
					continue;
				}
				if (checkDiagonal(num, row, col, true)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkDiagonal(int playerNum, int startRowNum, int startColNum, boolean isBackwards) {
		for (int i = 1; i < 4; i++) {
			int j;
			if (isBackwards) {
				j = -i;
			} else {
				j = i;
			}
			if (board[startRowNum + i][startColNum + j] != playerNum) {
				return false;
			}
		}
		return true;
	}

	private boolean isVertical(int num) {
		int rowNum = board.length;
		int colNum = board[0].length;
		for (int row = 0; row < rowNum - 3; row++) {
			for (int col = 0; col < colNum; col++) {
				if (board[row][col] != num) {
					continue;
				}
				if (checkVertical(num, row, col)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkVertical(int playerNum, int startRowNum, int startColNum) {
		for (int i = 1; i < 4; i++) {
			if (board[startRowNum + i][startColNum] != playerNum) {
				return false;
			}
		}
		return true;
	}

	private boolean isHorizontal(int num) {
		int rowNum = board.length;
		int colNum = board[0].length;
		for (int row = 0; row < rowNum; row++) {
			for (int col = 0; col < colNum - 3; col++) {
				if (board[row][col] != num) {
					continue;
				}
				if (checkHorizontal(num, row, col)) {
					return true;
				}

			}
		}
		return false;
	}

	private boolean checkHorizontal(int playerNum, int startRowNum, int startColNum) {
		for (int i = 1; i < 4; i++) {
			if (board[startRowNum][startColNum + i] != playerNum) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			System.out.println("Times up! Changing Players!");
			changeTurns();
			startTurn();
		}

	}

	public void addObservertoTimer(Observer o) {
		timer.addObserver(o);
	}

	@Override
	public void registerStartedTurnObserver(ObserverStartedTurn o) {
		// TODO Auto-generated method stub
		startedTurnObservers.add(o);
	}

	@Override
	public void notifyStartedTurnObservers(Player p) {
		for (ObserverStartedTurn o : startedTurnObservers) {
			o.updatePlayerTurnText(p);
		}
	}

	@Override
	public void registerSquarePlayedObserver(ObserverSquarePlayed o) {
		squarePlayedObservers.add(o);

	}

	@Override
	public void notifySquarePlayedObservers(Player p) {

		for (ObserverSquarePlayed o : squarePlayedObservers) {
			o.updateBoard(p, selected);
		}

	}

	@Override
	public void registerEndGameObserver(ObserverEndGame o) {
		endGameObservers.add(o);

	}

	@Override
	public void notifyEndGameObservers(Player p) {
		for (ObserverEndGame o : endGameObservers) {
			o.updateText(p);
		}

	}

	@Override
	public void registerInvalidColObserver(ObserverInvalidCols o) {
		invalidColsObservers.add(o);

	}

	@Override
	public void notifyInvalidColObservers(int colNum) {
		for (ObserverInvalidCols o : invalidColsObservers) {
			o.updateButton(colNum);
		}

	}

}
