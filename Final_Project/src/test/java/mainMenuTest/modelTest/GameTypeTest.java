package mainMenuTest.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import mainMenu.model.GameType;

/**
 * Unit testing for the GameType enum.
 * @author Khuram C.
 */
class GameTypeTest {


	/**
	 * Tests the getGameDescription method with the different gameTypes.
	 * @param gameType to be tested
	 * @param expectedGameDescription expected from method.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestGetGameDescription")
	void testGetGameDescription(GameType gameType, String expectedGameDescription) {
		assertEquals(expectedGameDescription,gameType.getGameDescription());
	}
	
	/**
	 * Returns a stream of arrays with each GameType and their expected game descriptions.
	 * @return stream of GameTypes with associated description.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsforTestGetGameDescription(){
		return Stream.of( new Object[]{GameType.BATTLESHIP, "A strategic guessing game where players try to "
				+ "sink each other's hidden ships on a grid-based board."},
		new Object[]{GameType.MASTERMIND,"A code-breaking game where one player creates a sequence"
				+ " of colors, and the other attempts to guess it using logic and deduction."},
		new Object[]{GameType.MANCALA,"A turn-based, ancient board game focused on collecting"
				+ " and sowing seeds across a board to capture as many as possible."},
		new Object[]{GameType.CONNECT4,"A two-player strategy game where players aim to connect"
				+ " four of their colored discs in a row vertically, horizontally, or diagonally."});
	}

}
