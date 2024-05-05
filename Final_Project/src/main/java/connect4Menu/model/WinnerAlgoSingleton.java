package connect4Menu.model;

import connect4Menu.model.player.Player;

public class WinnerAlgoSingleton {

	private int[][] board;
	private static WinnerAlgoSingleton instance = null;
	private WinnerAlgoSingleton() {
	}
	
	public static WinnerAlgoSingleton getInstance() {
		if(instance == null) {
			instance = new WinnerAlgoSingleton();
		}
		return instance;
	}
	
	
	
	public boolean isWinner(Player p,int[][] board) {
		this.board = board;
		int playerNum = p.getPlayerNum();
		if (isDiagonal(playerNum) || isVertical(playerNum) || isHorizontal(playerNum)) {
			//p.setWinner();
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
		
		//SEE WHY THIS IS WRONG
		for (int i = 1; i < 4; i++) {
			int j;
			if (isBackwards) {
				j = -i;
			} else {
				j = i;
			}
			if (board[startRowNum +i][startColNum + j] != playerNum) {
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

}
