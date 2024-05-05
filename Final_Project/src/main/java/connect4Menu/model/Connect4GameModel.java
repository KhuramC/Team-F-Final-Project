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

public class Connect4GameModel
		implements Observer, IStartedTurnObservable, ISquarePlayedObservable, IEndGameObservable, IInvalidColsObservable {

	private Player p1;
	private Player p2;
	private final int[][] board;
	private boolean isTimer;
	private CountDownTimer timer;
	private int[] selected = new int[2];
	private boolean hasSelected = false;
	private ArrayList<IStartedTurnObserver> startedTurnObservers = new ArrayList<>();
	private ArrayList<ISquarePlayedObserver> squarePlayedObservers = new ArrayList<>();
	private ArrayList<IEndGameObserver> endGameObservers = new ArrayList<>();
	private ArrayList<IInvalidColsObserver> invalidColsObservers = new ArrayList<>();


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

	public Player getP2() {
		return p2;
	}

	public int[][] getBoard() {
		return board;
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
			System.out.println(p.getName());
			timer.startTimer();
		}

	}

	public boolean isTurnAvailable() {
		for (int cell : board[0]) {
			if(isValidSelection(cell)) {
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
		checkEndGame();
		

	}
	
	private boolean checkEndGame() {
		if (WinnerAlgoSingleton.getInstance().isWinner(whoseTurn(),board)) {
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
		
		
		return true;
	}

	private ArrayList<Integer> getValidCols() {
		ArrayList<Integer> validColsList = new ArrayList<>();
		for(int col = 0; col < board[0].length;col++) {
			if(isValidSelection(col)) {
				validColsList.add(col);
			}
		}
		
		return validColsList;
		
	}
	private boolean selectRandom(Player p) {
		Random rand = new Random();
		int randomColChoice = -1;
		int colNum = board[0].length;
		ArrayList<Integer> validColNums = getValidCols();
		int randomValidColChoice = validColNums.get(rand.nextInt(validColNums.size()));
		
//		boolean[] validCols = new boolean[colNum];
//		int validColAmount = 0;
//		for(int col = 0; col < colNum;col++) {
//			if(isValidSelection(col)) {
//				validCols[col] = true;
//				validColAmount++;
//			}else {
//				validCols[col] = false;
//			}
//		}
//		int randomValidColNum = rand.nextInt(validColAmount);
//		for(int col = 0; col < colNum;col++) {
//			if(validCols[col]) {
//				randomValidColNum--;
//			}
//			if(randomValidColNum==0) {
//				randomColChoice = col;
//			}
//		}
		select(randomValidColChoice);
		
		
		
		checkEndGame();
		return true;
		
	}
	

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			System.out.println("Times up! Changing Players!");
			selectRandom(whoseTurn());
			changeTurns();
			startTurn();
		}

	}

	public void addObservertoTimer(Observer o) {
		timer.addObserver(o);
	}

	@Override
	public void registerStartedTurnObserver(IStartedTurnObserver o) {
		startedTurnObservers.add(o);
	}

	@Override
	public void notifyStartedTurnObservers(Player p) {
		for (IStartedTurnObserver o : startedTurnObservers) {
			o.updatePlayerTurnText(p);
		}
	}

	@Override
	public void registerSquarePlayedObserver(ISquarePlayedObserver o) {
		squarePlayedObservers.add(o);
	}

	@Override
	public void notifySquarePlayedObservers(Player p) {
		for (ISquarePlayedObserver o : squarePlayedObservers) {
			o.updateBoard(p, selected);
		}
	}

	@Override
	public void registerEndGameObserver(IEndGameObserver o) {
		endGameObservers.add(o);
	}

	@Override
	public void notifyEndGameObservers(Player p) {
		for (IEndGameObserver o : endGameObservers) {
			o.updateText(p);
		}
	}

	@Override
	public void registerInvalidColObserver(IInvalidColsObserver o) {
		invalidColsObservers.add(o);
	}

	@Override
	public void notifyInvalidColObservers(int colNum) {
		for (IInvalidColsObserver o : invalidColsObservers) {
			o.updateButton(colNum);
		}
	}
}
