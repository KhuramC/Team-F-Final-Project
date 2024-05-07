package connect4MenuTest.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.model.player.IPlayerColors;
import connect4Menu.model.player.Player;
import connect4Menu.model.player.Player1Colors;
import connect4Menu.model.player.Player2Colors;


/**
 * Unit testing for the Connect4SettingsModel and its methods.
 * @author Khuram C.
 */
class Connect4SettingsModelTest {

	private Connect4SettingsModel model = null;

	@BeforeEach
	void setUp() throws Exception {
		model = new Connect4SettingsModel();
	}

	/**
	 * Tests the getColNum method. The default value should be 7, so this tests if that is correct.
	 * @author Khuram C.
	 */
	@Test
	void testGetColNum() {
		int expectedColNum = 7; //default value.
		assertEquals(expectedColNum,model.getColNum());
	}
	
	/**
	 * Tests the getRowNum method. The default value should be 6, so this tests if that is correct.
	 * @author Khuram C.
	 */
	@Test
	void testGetRowNum() {
		int expectedRowNum = 6; //default value.
		assertEquals(expectedRowNum,model.getRowNum());
	}
	
	/**
	 * Tests the setBoardSize method. Since only one thing can be asserted at a time, this one checks
	 * if the row number is correct.
	 * @author Khuram C.
	 */
	@Test
	void testSetBoardSizeRows() {
		int expectedRowNum = 9;
		int expectedColNum = 10;
		model.setBoardSize(expectedRowNum, expectedColNum);
		assertEquals(expectedRowNum,model.getRowNum());
	}
	
	/**
	 * Tests the setBoardSize method. Since only one thing can be asserted at a time, this one checks
	 * if the column number is correct.
	 * @author Khuram C.
	 */
	@Test
	void testSetBoardSizeCols() {
		int expectedRowNum = 9;
		int expectedColNum = 10;
		model.setBoardSize(expectedRowNum, expectedColNum);
		assertEquals(expectedColNum,model.getColNum());
		
	}
	
	/**
	 * Tests the isTimer method. The default value is false, so this tests if that is correct.
	 * @author Khuram C.
	 */
	@Test
	void testIsTimer() {
		boolean expected = false; //default value
		assertEquals(expected,model.isTimer());
	}
	
	/**
	 * Tests the toggleTimer method. After toggling, the value should be true, so this tests if that is correct.
	 * @author Khuram C.
	 */
	@Test
	void testToggleTimer() {
		model.toggleTimer();
		boolean expected = true;
		assertEquals(expected,model.isTimer());
	}
	
	/**
	 * Tests the getTimerTime method. The default value is 15, so this tests if that is correct.
	 * @author Khuram C.
	 */
	@Test
	void testGetTimerTime() {
		int expected = 15; //default value
		assertEquals(expected,model.getTimerTime());
	}
	
	/**
	 * Tests the setTimerTime method. This checks if the expected value is what is returned by getTimerTime after using 
	 * the setTimerTime method.
	 * @author Khuram C.
	 */
	@Test
	void testSetTimerTime() {
		int expected = 52;
		model.setTimerTime(expected);
		assertEquals(expected,model.getTimerTime());
	}
	
	/**
	 * Tests the getPlayer method with different parameters. There are specific values for if 1 or 2 is passed, and the edge
	 * cases of 0 and 3 are also tested.
	 * @param playerNum player's number.
	 * @param expected Player to be returned.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsForTestGetPlayer")
	void testGetPlayer(int playerNum,Player expected) {
		Player actual = model.getPlayer(playerNum);
		assertEquals(expected,actual);
	}
	
	/**
	 * Returns a stream consisting of the playerNums 0 to 3 with the associated Player expected.
	 * @return stream of object arrays with 2 elements.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsForTestGetPlayer(){
		return Stream.of( new Object[]{0,null},
				new Object[]{1,new Player("Player 1", Player1Colors.RED, Connect4SettingsModel.player1Num)},
				new Object[]{2,new Player("Player 2", Player2Colors.YELLOW, Connect4SettingsModel.player2Num)},
				new Object[]{3,null});
	}
	
	/**
	 * Tests the changePlayerColors method with different parameters. Sometimes the method returns false if it is unable to
	 * change the color. If it claims to be successful, it checks if it did. Otherwise, it is checked whether it is expected to fail.
	 * @param playerNum player's number.
	 * @param expectedColor expectedColor to get back after setting.
	 * @param expectedSuccess expected return value of changePlayerColor method.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestChangePlayerColor")
	void testChangePlayerColor(int playerNum, IPlayerColors expectedColor,boolean expectedSuccess){
		boolean actualSuccess = model.changePlayerColor(playerNum, expectedColor);
		if(actualSuccess) {
			assertEquals(expectedColor,model.getPlayer(playerNum).getColor());
		}else {
			assertEquals(expectedSuccess,actualSuccess);	
		}
	}
	/**
	 * Returns a stream consisting the playerNums 0 to 3, the associated playerColor, and whether the method should return true or not.
	 * @return stream of object arrays with 3 elements.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsforTestChangePlayerColor(){
		return Stream.of(new Object[]{0,null,false},
				new Object[]{1,Player1Colors.BLACK,true},
				new Object[]{2,Player2Colors.ORANGE,true},
				new Object[]{3,null,false});
	
	}
}
