package connect4Menu.model;

import java.util.Observable;

import connect4Menu.model.player.IPlayerColors;
import connect4Menu.model.player.Player;
import connect4Menu.model.player.Player1Colors;
import connect4Menu.model.player.Player2Colors;

/**
 * A model from the MVC architecture for the Connect4 menus to play a game of
 * Connect4. The model is Observable and is observed by the Connect4SettingsView.
 * 
 * @author Khuram C.
 */
public class Connect4SettingsModel extends Observable {

	// default_values
	private int colNum = 7; // x
	private int rowNum = 6; // y
	private boolean isTimer = false;
	private int timerTime = 15;
	private Player[] players = {new Player("Player 1", Player1Colors.RED, player1Num),
			new Player("Player 2", Player2Colors.YELLOW, player2Num)};
	public static final int minTimerTime = 15;
	public static final int maxTimerTime = 60;
	public static final int player1Num = 1;
	public static final int player2Num = 2;

	/**
	 * Returns the current amount of columns set for the game.
	 * @return int number of columns.
	 * @author Khuram C.
	 */
	public int getColNum() {
		return colNum;
	}

	/**
	 * Returns the current amount of rows set for the game.
	 * @return int number of rows.
	 * @author Khuram C.
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * Official feature method: Sets the board size based on the input.
	 * @param rowNum number of rows.
	 * @param colNum number of columns.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean setBoardSize(int rowNum,int colNum) {
		this.rowNum = rowNum;
		this.colNum = colNum;
		return true;
	}

	/**
	 * Returns whether the timer is turned on or off before the game starts.
	 * @return boolean for timer's status.
	 * @author Khuram C.
	 */
	public boolean isTimer() {
		return isTimer;
	}

	/**
	 * Official feature method. Turns the timer into the other state, either turning it off or not.
	 * Observers are notified of the change.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean toggleTimer() {
		isTimer = !isTimer;
		setChanged();
		notifyObservers(isTimer);
		clearChanged();
		return true;
	}

	/**
	 * Gives the current amount of time set for the timer.
	 * @return int amount of timer set for the timer currently.
	 * @author Khuram C.
	 */
	public int getTimerTime() {
		return timerTime;
	}

	/**
	 * Sets the time of the timer and notifies Observers of the change.
	 * @param timerTime time of timer.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean setTimerTime(int timerTime) {
		this.timerTime = timerTime;
		setChanged();
		notifyObservers(timerTime);
		clearChanged();
		return true;
	}

	/**
	 * Returns a player based on the number. Returns null if the input is invalid.
	 * @param playerNum number of player.
	 * @return player#playerNum or null.
	 * @author Khuram C.
	 */
	public Player getPlayer(int playerNum) {
		if(0<playerNum && playerNum<3)
			return players[playerNum-1];
		return null;
	}
	
	/**
	 * Changes the player Color based on the player's number. False is returned if unsuccessful due to an invalid input.
	 * @param playerNum player#playerNum.
	 * @param color to change to.
	 * @return boolean detailing success or not.
	 * @author Khuram C.
	 */
	public boolean changePlayerColor(int playerNum,IPlayerColors color) {
		Player player = getPlayer(playerNum);
		if(player!=null) {
			player.setColor(color);
			return true;
		}
		return false;
	}

	

}
