package connect4MenuTest.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connect4Menu.model.WinnerAlgoSingleton;

/**
 * Unit testing for WinnerAlgoSingleton and its methods.
 * @author Khuram C.
 */
class WinnerAlgoSingletonTest {
	
	
	WinnerAlgoSingleton instance = null;
	private int[][] board = null;
	private int playerNum;

	@BeforeEach
	void setUp() throws Exception {
		board = new int[6][7]; //default Connect4 size
		playerNum = 1;
		instance = WinnerAlgoSingleton.getInstance();
	}

	/**
	 * Tests the getInstance method. We expect and instance of the Singleton, so we check for that.
	 * @author Khuram C.
	 */
	@Test
	void testGetInstance() {
		if(WinnerAlgoSingleton.getInstance() instanceof WinnerAlgoSingleton) {
			assertTrue(true);
		}
	}
	
	/**
	 * Tests the isWinner method whenever there is no current winner. False is expected, and that is tested.
	 * @author Khuram C.
	 */
	@Test 
	void testIsWinnerFalse() {
		boolean expected = false;
		assertEquals(expected, instance.isWinner(playerNum, board));
	}
	
	/**
	 * Tests the isWinner method whenever there is a winner on a diagonal. True is expected, and that is tested.
	 * @author Khuram C.
	 */
	@Test 
	void testIsWinnerDiagonal() {
		int finalRowNum = board.length-1;
		int finalColNum = board[0].length-1;
		for(int i = 0;i < 4; i++) {
			board[finalRowNum-i][finalColNum-i] = playerNum;
		}
		boolean expected = true;
		assertEquals(expected,instance.isWinner(playerNum, board));
		
	}
	
	/**
	 * Tests the isWinner method whenver there is a winner on a backwards diagonal. True is expected, and that is tested.
	 * @author Khuram C.
	 */
	@Test
	void testIsWinnerBackwardsDiagonal() {
		int finalRowNum = board.length-1;
		int colNum = 0;
		for(int i = 0;i < 4; i++) {
			board[finalRowNum-i][colNum+i] = playerNum;
		}
		boolean expected = true;
		assertEquals(expected,instance.isWinner(playerNum, board));
		
	}
	
	/**
	 * Tests the isWinner method whenver there is a winner on a vertical. True is expected, and that is tested.
	 * @author Khuram C.
	 */
	@Test
	void testIsWinnerVertical() {
		int finalRowNum = board.length-1;
		int finalColNum = board[0].length-1;
		for(int row = 0;row < 4; row++) {
			board[finalRowNum-row][finalColNum] = playerNum;
		}
		boolean expected = true;
		assertEquals(expected,instance.isWinner(playerNum, board));
		
	}
	
	/**
	 * Tests the isWinner method whenever there is a winner on a horizontal. True is expected, and that is tested.
	 * @author Khuram C.
	 */
	@Test
	void testIsWinnerHorizontal() {
		int finalRowNum = board.length-1;
		for(int col = 0; col < 4; col++) {
			board[finalRowNum][col] = playerNum;
		}
		boolean expected = true;
		assertEquals(expected,instance.isWinner(playerNum, board));	
	}
	
	

}
