package connect4MenuTest.modelTest.playerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.model.player.Player2Colors;

/**
 * Unit testing for the Player2Colors enum and its methods.
 * @author Khuram C.
 */
class Player2ColorsTest {


	/**
	 * Tests the getAllowedPlayer method with each enum value. Since they are Player2Colors, they should always return 2.
	 * @param color to test.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestGetAllowedPlayer")
	void testGetAllowedPlayer(Player2Colors color) {
		int expectedAllowedPlayer = 2;
		assertEquals(expectedAllowedPlayer,color.getAllowedPlayer());
	}
	
	/**
	 * Returns stream of Player2Color values.
	 * @return stream of Player2Color values.
	 * @author Khuram C.
	 */
	static Stream<Player2Colors> provideArgumentsforTestGetAllowedPlayer(){
		return Stream.of(Player2Colors.values());
	}
	
	
	/**
	 * Tests the getFilePath method with each enum value and their associated expected file path.
	 * @param color to test.
	 * @param expectedFilePath
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestGetFilePath")
	void testGetFilePath(Player2Colors color, String expectedFilePath) {
		assertEquals(expectedFilePath,color.getFilePath());
	}
	
	/**
	 * Returns a stream of arrays with each Player2Colors and its expected filepath.
	 * @return stream of arrays.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsforTestGetFilePath() {
		return Stream.of(new Object[]{Player2Colors.YELLOW,"/connect4/images/connect4p2yellowsquare.drawio.png"},
				new Object[]{Player2Colors.ORANGE,"/connect4/images/connect4p2orangesquare.drawio.png"},
				new Object[]{Player2Colors.PURPLE,"/connect4/images/connect4p2purplesquare.drawio.png"});
	}

}
