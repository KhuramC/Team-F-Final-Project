package mainMenuTest.controlTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import mainMenu.control.MainMenuController;
import mainMenu.model.GameType;

/**
 * Unit tests for MainMenuController. Due to restrictions placed on the class, the controller needs to be extended
 * test certain methods. 
 * @author Khuram C.
 */
class MainMenuControllerTest extends MainMenuController {
	
	private MainMenuControllerTest controller = null;

	@BeforeEach
	void setUp() throws Exception {
		controller = new MainMenuControllerTest();
	}

	/**
	 * Tests the startGame method in the StartGameButtonListener subclass with the different GameTypes. True is expected
	 * for all GameTypes
	 * @param GameType
	 * @param expected
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideGameTypesforTestStartGameButtonListenerStartGame")
	void testStartGameButtonListenerStartGame(GameType GameType, boolean expected) {
		StartGameButtonListener listener = new StartGameButtonListener();
		boolean actual = listener.startGame(GameType);
		assertEquals(expected,actual);
	}
	
	/**
	 * Returns a stream of arrays consisting of each successive GameType with a true boolean variable.
	 * @return stream of arrays with each GameType and true.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideGameTypesforTestStartGameButtonListenerStartGame(){
		return Stream.of(new Object[]{GameType.BATTLESHIP,true},
				new Object[]{GameType.MASTERMIND,true},
				new Object[]{GameType.MANCALA,true}, 
				new Object[]{GameType.CONNECT4,true});
	}
	
	/**
	 * Tests the initiate method. Because the method uses the invokeLater for the thread, we need to wait for it to go
	 * first by making a new thread that waits to check if initiate has worked. Otherwise, the test occurs before the method
	 * actually does anything, and would never be consistent. This also requires inheriting from the controller to check the 
	 * view's visibility.
	 * 
	 * @author Khuram C.
	 */
	@Test
	void testInitiate() {
		controller.initiate();
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				boolean expectedBool = true;
				assertEquals(expectedBool,controller.startView.isVisible());
			}
		});
	}

}
