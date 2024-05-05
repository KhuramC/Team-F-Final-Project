package connect4Menu.model;

/**
 * A class for determining the winner of a Connect4 game. Because I didn't want everything to be static, I made it a Singleton class instead.
 * There is never a need for more than one instance of this class.
 * @author Khuram C.
 */
public class WinnerAlgoSingleton {

	private int[][] board;
	private int playerNum;
	private static WinnerAlgoSingleton instance = null;
	private WinnerAlgoSingleton() {
	}
	
	/**
	 * Gives an instance of this class to the user. If necessary, it will create a new instance to do so.
	 * @return instance of WinnerAlgoSingleton.
	 * @author Khuram C.
	 */
	public static WinnerAlgoSingleton getInstance() {
		if(instance == null) {
			instance = new WinnerAlgoSingleton();
		}
		return instance;
	}
	
	
	/**
	 * Main algorithm. Checks if there is a winner with the given player and playerNum due to a diagonal, vertical, or horizontal
	 * connect 4.
	 * @param playerNum number of player to check for.
	 * @param board to check winner from.
	 * @return boolean determining winner.
	 * @author Khuram C.
	 */
	public boolean isWinner(int playerNum,int[][] board) {
		this.board = board;
		this.playerNum = playerNum;
		return(isDiagonal() || isVertical() || isHorizontal());
	}

	/**
	 * Checks if there is a win diagonally. There are two directions a diagonal can go. Checking is only done looking down,
	 * and since 0,0 corresponds to the upper left, the diagonal that goes down and right is considered the regular diagonal. Going
	 * down and left is considered the backwards diagonal.
	 * @return boolean determining winner.
	 * @author Khuram C.
	 */
	private boolean isDiagonal() {
		int rowNum = board.length;
		int colNum = board[0].length;
		//Because we are only checking down, it's impossible to have a connect4 in the last 3 rows.
		for (int row = 0; row < rowNum - 3; row++) { 
			for(int col = 0; col < colNum; col++) {
				//checks if it's valid to have a diagonal and then if there actually is one. Due to short-circuiting in Java,
				//this should never error out.
				if(col+3 <= colNum - 1 && checkDiagonal(row,col,false)) {
					//down and right diagonals
					return true;
				}
				if(col - 3 >= 0 && checkDiagonal(row,col,true) ) {
					//down and left diagonals
					return true;
				}	
			}
		}
		return false;
	}

	/**
	 * Helper method for isDiagonal. Checks if the there is a diagonal 4 in a row (going down) starting at the given cell location. 
	 * @param startRowNum rowNum of cell.
	 * @param startColNum colNum of cell.
	 * @param isBackwards whether it goes down and left(true) or it goes down and right(false).
	 * @return boolean detailing if there is a a diagonal 4 in a row.
	 * @author Khuram C.
	 */
	private boolean checkDiagonal(int startRowNum, int startColNum, boolean isBackwards) {
		for (int i = 0; i < 4; i++) {
			int j = i;
			if (isBackwards) {
				j = -i;
			}
			if (board[startRowNum +i][startColNum + j] != playerNum) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if there is a win vertically. Checking is only done going down.
	 * @return boolean determining if a winner has been found.
	 * @author Khuram C.
	 */
	private boolean isVertical() {
		int rowNum = board.length;
		int colNum = board[0].length;
		//there can't be a vertical win going down in the last 3 rows; there's not enough space.
		for (int row = 0; row < rowNum - 3; row++) {
			for (int col = 0; col < colNum; col++) {
				if (checkVertical(row, col)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Helper method for isVertical. Checks if there is a vertical 4 in a row (going down) starting at the given cell location.
	 * @param startRowNum rowNum of cell.
	 * @param startColNum colNum of cell.
	 * @return boolean detailing if there is a vertical 4 in a row.
	 * @author Khuram C.
	 */
	private boolean checkVertical(int startRowNum, int startColNum) {
		for (int i = 0; i < 4; i++) {
			if (board[startRowNum + i][startColNum] != playerNum) {
				return false;
			}
		}
		return true;
	}

	
	/**
	 * Checks if there is a win horizontally. Checking is only done going right.
	 * @return boolean determining if a winner has been found.
	 * @author Khuram C.
	 */
	private boolean isHorizontal() {
		int rowNum = board.length;
		int colNum = board[0].length;
		for (int row = 0; row < rowNum; row++) {
			//there can't be a horizontal win going right in the last 3 cols; there's not enough space.
			for (int col = 0; col < colNum - 3; col++) {
				if (checkHorizontal(row, col)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Helper method for isHorizontal. Checks if there is a horizontal 4 in a row (going right) starting at the given cell location.
	 * @param startRowNum rowNum of cell.
	 * @param startColNum colNum of cell.
	 * @return boolean detailing if there is a horizontal 4 in a row.
	 * @author Khuram C.
	 */
	private boolean checkHorizontal(int startRowNum, int startColNum) {
		for (int i = 0; i < 4; i++) {
			if (board[startRowNum][startColNum + i] != playerNum) {
				return false;
			}
		}
		return true;
	}
}
