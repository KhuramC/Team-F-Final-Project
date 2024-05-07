package connect4MenuTest.viewTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.model.player.Player;
import connect4Menu.model.player.Player1Colors;
import connect4Menu.view.Connect4GameView;

/**
 * Unit testing for Connect4GameView and its methods. Due to restrictions placed on the class, the view needs to be extended
 * and certain fields need to be considered protected to test certain methods. The addListener method is tested (indirectly)
 * in the Connect4GameControllerTest.
 * @author Khuram C.
 */
class Connect4GameViewTest extends Connect4GameView {
	
	
	private Connect4GameViewTest view = null;
	private static int rowNum = 6;
	private static int colNum = 7;
	private static int timerTime = 15;
	private static Player p;
	
	public Connect4GameViewTest() {
		super(rowNum, colNum, timerTime);
	}

	@BeforeEach
	void setUp() throws Exception {
		view = new Connect4GameViewTest();
		p = new Player("Player 1", Player1Colors.GREEN,1);
	}

	/**
	 * Tests the setTimerVisibility method. We expect the timerLabel to be visible after passing true in,
	 * so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testSetTimerVisibility() {
		boolean expected = true;
		view.setTimerLabelVisibility(expected);
		assertEquals(expected,view.timerLabel.isVisible());
	}
	
	/**
	 * Tests the update method. We expect the timerLabel text to get updated with the timerTime after passing it in,
	 * so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testUpdate() {
		long timerTime = 12;
		String expected = "Time left: " + Long.toString(timerTime);
		view.update(null, timerTime);
		assertEquals(expected,view.timerLabel.getText());
	}
	
	/**
	 * Tests the updatePlayerTurnText method. We expect true to be returned, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testUpdatePlayerTurnText() {
		assertTrue(view.updatePlayerTurnText(p));
	}
	
	/**
	 * Tests the updateBoard method. We expect true to be returned, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testUpdateBoard() {
		assertTrue(view.updateBoard(p, new int[]{1,2}));
	}
	
	/**
	 * Tests the updateTextWithWinner method with different parameters. The endLabel should say it's a tie if null is passed, 
	 * or that a player won if it they were passed.
	 * @param p player that won(if not null.
	 * @param expected String in endLabel.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsForTestUpdateTextWithWinner")
	void testUpdateTextWithWinner(Player p, String expected) {
		view.updateTextWithWinner(p);
		assertEquals(expected,view.endLabel.getText());	
	}
	
	/** 
	 * Returns a stream of arrays with Players and Strings for their associated 'win' message.
	 * @return a stream of arrays with Players and Strings.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsForTestUpdateTextWithWinner(){
		return Stream.of(new Object[]{null,"It was a tie!"},
				new Object[]{p,p.getName() + " won!"});
	}
	
	/**
	 * Tests the updateButton method. We expect true to be returned, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testUpdateButton() {
		assertTrue(view.updateButton(2));
	}

}
