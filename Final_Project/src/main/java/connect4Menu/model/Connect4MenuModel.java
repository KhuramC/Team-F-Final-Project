package connect4Menu.model;

/**
 * A model from the MVC architecture for the Connect4 menus to play a game of Connect4.
 * @author Khuram C.
 */
public class Connect4MenuModel {

	//default_values
	private int colNum = 6; // x
	private int rowNum = 7; // y
	private boolean isTimer = false;
	private int timerTime = 15;
	private Player p1 = new Player("Player 1", Player1Colors.RED);
	private Player p2 = new Player("Player 2", Player2Colors.YELLOW);
	public static final int minTimerTime = 15;
	public static final int maxTimerTime = 60;
	public static final int player1Num = 1;
	public static final int player2Num = 2;
	
	/**
	 * A default constructor for the Connect4MenuModel. Because all the elements already have a default value, no parameters are
	 * needed.
	 * @author Khuram C.
	 */
	public Connect4MenuModel() {
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int col_amount) {
		this.colNum = col_amount;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int row_amount) {
		this.rowNum = row_amount;
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
	
}
