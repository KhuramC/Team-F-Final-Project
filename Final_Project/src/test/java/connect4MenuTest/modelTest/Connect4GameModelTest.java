package connect4MenuTest.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.model.Connect4GameModel;
import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.model.player.Player;

/**
 * Unit testing for Connect4GameModel. Inheritance and changing of fields to protected is required to test
 * certain methods.
 * @author Khuram C.
 */
class Connect4GameModelTest extends Connect4GameModel{
	
	
	private Connect4GameModelTest model;
	private static Connect4SettingsModel settingsModel = new Connect4SettingsModel();
	private static Player p;

	public Connect4GameModelTest() {
		super(settingsModel);
	}

	@BeforeEach
	void setUp() throws Exception {
		settingsModel = new Connect4SettingsModel();
		settingsModel.toggleTimer();
		p = settingsModel.getPlayer(1);
		model = new Connect4GameModelTest();
	}


	/**
	 * Test for the whoseTurn method. We expect player 1 to be returned(it's the start of the game), 
	 * so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testWhoseTurn() {
		assertEquals(p,model.whoseTurn());
	}
	
	/**
	 * Test for the select method. We expect the location we select to be put on the board, so test for that.
	 * To manipulate the board in this manner required turning the board into a protected attribute.
	 * @author Khuram C.
	 */
	@Test
	void testSelect() {
		int partBoard[] = new int[]{0,2,2,1,1,1};
		for(int row = 0; row < board.length;row++) {
			model.board[row][0] = partBoard[row];
		}
		model.select(0);
		int expected = 1;
		assertEquals(1,model.board[0][0]);		
	}
	
	/**
	 * Tests for the checkEndGame method with different board states. We expect either a true if the game ends, or a
	 * false if the game continues. To manipulate the board in this manner required turning the board into a protected
	 * attribute.
	 * @param board to test on.
	 * @param expected ending of game.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestCheckEndGame")
	void testCheckEndGame(int[][]board, boolean expected) {
		for(int row = 0; row < board.length;row++) {
			for(int col = 0; col<board[0].length;col++) {
				model.board[row][col] = board[row][col];
			}
		}
		assertEquals(expected,model.checkEndGame());
	}
	
	/**
	 * Returns three boards and whether the game should be considered over.
	 * @return stream of three object arrays of 2D int array and boolean.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsforTestCheckEndGame(){
		int p1WinBoard[][] = new int[][] 
		{{0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0},
		{1,0,0,0,0,0,0},
		{1,0,0,0,0,0,0},
		{1,0,0,0,0,0,0},
		{1,0,0,0,0,0,0}};
		
		int tieBoard[][] = new int[][] 
		{{2,1,2,1,2,1,2},
		{2,1,2,1,2,1,2},
		{2,1,2,1,2,1,2},
		{1,2,1,2,1,2,1},
		{1,2,1,2,1,2,1},
		{1,2,1,2,1,2,1}};
		
		int noWinBoard[][] = new int[][] 
		{{0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0},
		{1,0,0,0,0,0,0},
		{1,0,0,0,0,0,0},
		{1,0,0,0,0,0,0}};
		
		return Stream.of(new Object[]{p1WinBoard,true},
				new Object[]{tieBoard,true},
				new Object[]{noWinBoard,false});
	}
	
	/**
	 * Tests the getValidCols method. We expect the empty board to return all columns, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testGetValidCols() {
		int[] expected = new int[]{0,1,2,3,4,5,6};
		int[] actual =  model.getValidCols().stream().mapToInt(i->i).toArray();
		assertArrayEquals(expected, actual);
	}
	
	/**
	 * Tests the selectRandom method. We expect true to be returned, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testSelectRandom() {
		assertTrue(model.selectRandom());
	}
}
