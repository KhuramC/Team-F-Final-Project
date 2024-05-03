package connect4Menu.model;

/**
 * A model from the MVC architecture for the Connect4 menus to play a game of
 * Connect4.
 * 
 * @author Khuram C.
 */
public class Connect4SettingsModel {

	// default_values
	private int colNum = 6; // x
	private int rowNum = 7; // y
	private boolean isTimer = false;
	private int timerTime = 15;
	private Player[] players = {new Player("Player 1", Player1Colors.RED, player1Num),
			new Player("Player 2", Player2Colors.YELLOW, player2Num)};
	public static final int minTimerTime = 15;
	public static final int maxTimerTime = 60;
	public static final int player1Num = 1;
	public static final int player2Num = 2;

	/**
	 * A default constructor for the Connect4MenuModel. Because all the elements
	 * already have a default value, no parameters are needed.
	 * 
	 * @author Khuram C.
	 */
	public Connect4SettingsModel() {
	}

	// getters and setters
	public int getColNum() {
		return colNum;
	}

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

	public boolean isTimer() {
		return isTimer;
	}

	public void setTimer(boolean isTimer) {
		this.isTimer = isTimer;
	}

	public int getTimerTime() {
		return timerTime;
	}

	public void setTimerTime(int timerTime) {
		this.timerTime = timerTime;
	}

	public Player getPlayer(int playerNum) {
		if(0<playerNum && playerNum<3)
			return players[playerNum-1];
		return null;
	}
	
	public boolean changePlayerColor(int playerNum,PlayerColors color) {
		players[playerNum-1].setColor(color);
		return true;
	}

	

}
