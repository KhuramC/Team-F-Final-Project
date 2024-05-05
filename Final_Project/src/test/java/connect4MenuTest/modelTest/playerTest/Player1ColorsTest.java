package connect4MenuTest.modelTest.playerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.model.player.Player1Colors;

/**
 * Unit testing for the Player1Colors enum and its methods.
 * @author Khuram C.
 */
class Player1ColorsTest {


	/**
	 * Tests the getAllowedPlayer method with each enum value. Since they are Player1Colors, they should always return 1.
	 * @param color to test.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestGetAllowedPlayer")
	void testGetAllowedPlayer(Player1Colors color) {
		int expectedAllowedPlayer = 1;
		assertEquals(expectedAllowedPlayer,color.getAllowedPlayer());
	}
	
	/**
	 * Returns stream of Player1Color values.
	 * @return stream of Player1Color values.
	 * @author Khuram C.
	 */
	static Stream<Player1Colors> provideArgumentsforTestGetAllowedPlayer(){
		return Stream.of(Player1Colors.values());
	}
	
	
	/**
	 * Tests the getFilePath method with each enum value and their associated expected file path.
	 * @param color to test.
	 * @param expectedFilePath
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestGetFilePath")
	void testGetFilePath(Player1Colors color, String expectedFilePath) {
		assertEquals(expectedFilePath,color.getFilePath());
	}
	
	/**
	 * Returns a stream of arrays with each Player1Colors and its expected filepath.
	 * @return stream of arrays.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsforTestGetFilePath() {
		return Stream.of(new Object[]{Player1Colors.RED,"/connect4/images/connect4p1redsquare.drawio.png"},
				new Object[]{Player1Colors.BLACK,"/connect4/images/connect4p1blacksquare.drawio.png"},
				new Object[]{Player1Colors.GREEN,"/connect4/images/connect4p1greensquare.drawio.png"});
	}

}
