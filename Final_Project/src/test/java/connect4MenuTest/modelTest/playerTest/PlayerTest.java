package connect4MenuTest.modelTest.playerTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.model.player.IPlayerColors;
import connect4Menu.model.player.Player;
import connect4Menu.model.player.Player1Colors;

/**
 * Unit testing for the Player class and its methods.
 */
class PlayerTest {
	static private Player p;

	@BeforeEach
	void setUp() throws Exception {
		p = new Player("Player 1", Player1Colors.BLACK,1);
	}


	/**
	 * Tests the getName method. The default value is "Player 1", so we expect it back.
	 */
	@Test
	void testGetName() {
		String expectedName = "Player 1";
		assertEquals(expectedName, p.getName());
	}
	
	/**
	 * Tests the getColor method. The default value is black, so we expect it back.
	 */
	@Test
	void testGetColor() {
		IPlayerColors expectedColor = Player1Colors.BLACK;
		assertEquals(expectedColor,p.getColor());
	}
	
	/**
	 * Tests the setColor method. We set it to green, so we expect it back as well.
	 * @author Khuram C.
	 */
	@Test
	void testSetColor() {
		IPlayerColors expectedColor = Player1Colors.GREEN;
		p.setColor(expectedColor);
		IPlayerColors actualColor = p.getColor();
		assertEquals(expectedColor,actualColor);
	}
	
	/**
	 * Tests the isTurn method. The default value is false, so we expect it back.
	 * @author Khuram C.
	 */
	@Test
	void testIsTurn() {
		boolean expectedIsTurn = false;
		assertEquals(expectedIsTurn,p.isTurn());
	}
	
	/**
	 * Tests the setTurn method. Because we set it to true, we expect it back as well.
	 * @author Khuram C.
	 */
	@Test
	void testSetTurn() {
		boolean expectedIsTurn = true;
		p.setTurn(expectedIsTurn);
		boolean actualIsTurn = p.isTurn();
		assertEquals(expectedIsTurn,actualIsTurn);
	}
	
	/**
	 * Tests the getPlayerNum method. The one created has a playerNum of 1 and is what is expected.
	 * @author Khuram C.
	 */
	@Test
	void testGetPlayerNum() {
		int expectedPlayerNum = 1;
		assertEquals(expectedPlayerNum,p.getPlayerNum());
	}
	
	
	/**
	 * Tests the equals method in Player with different parameters. 
	 * @param p1 player to compare.
	 * @param expected value from equals method.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestEquals")
	void testEquals(Player p1, boolean expected) {
		boolean actual = p.equals(p1);
		assertEquals(expected,actual);
		
	}
	
	
	/**
	 * Returns a stream of arrays of different types of Players and their expected values for the equals method.
	 * @return a stream of {Player,boolean} arrays.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsforTestEquals(){
		return Stream.of(new Object[]{p,true},
				new Object[]{null,false},
				new Object[]{new Player("Player 1", Player1Colors.BLACK,1),true},
				new Object[]{new Player("Player 1", Player1Colors.BLACK,2),false},
				new Object[]{new Player("Player 1", Player1Colors.GREEN,1),false},
				new Object[]{new Player("Player 2", Player1Colors.BLACK,1),false},
				new Object[]{new Player("Player 1", Player1Colors.GREEN,2),false},
				new Object[]{new Player("Player 2", Player1Colors.GREEN,1),false},
				new Object[]{new Player("Player 2", Player1Colors.BLACK,2),false},
				new Object[]{new Player("Player 2", Player1Colors.GREEN,2),false});
	}
	

}
