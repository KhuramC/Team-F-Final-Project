package mainMenuTest.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainMenu.model.GameType;
import mainMenu.model.MainMenuModel;

/**
 * Unit tests for the MainMenuModel and its methods.
 * @author Khuram C.
 */
class MainMenuModelTest {

	private MainMenuModel model = null;
	
	@BeforeEach
	void setUp() throws Exception {
		model = new MainMenuModel();
	}

	/**
	 * Tests the getGameChosen method. The default value in a MainMenuModel should be BATTLESHIP, so this tests if that is
	 * correct.
	 * @author Khuram C.
	 */
	@Test
	void testGetGameChosen() {
		GameType expectedGameType = GameType.BATTLESHIP; //this is due to it being the default value in a MainMenuModel.
		assertEquals(expectedGameType,model.getGameChosen());
	}
	
	/**
	 * Tests the chooseGame method. It uses the method to set the gameType in the model, and should be returned
	 * whenever getGameChosen is called.
	 * @author Khuram C.
	 */
	@Test
	void testchooseGame() {
		GameType expectedGameType = GameType.CONNECT4;
		model.chooseGame(expectedGameType);
		assertEquals(expectedGameType,model.getGameChosen());
	}
}
