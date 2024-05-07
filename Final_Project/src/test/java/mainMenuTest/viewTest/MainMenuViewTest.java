package mainMenuTest.viewTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mainMenu.model.GameType;
import mainMenu.view.MainMenuView;

/**
 * Unit tests for the MainMenuView. Due to the restrictions placed on the class, the view needs to be extended to 
 * test certain methods. The addListener methods are tested (indirectly) within the MainMenuControllerTest class.
 * @author Khuram C.
 */
class MainMenuViewTest extends MainMenuView {

	private MainMenuViewTest view = null;

	@BeforeEach
	void setUp() throws Exception {
		view = new MainMenuViewTest();
	}
	
	/**
	 * Tests the update and its helper method changeSummaryTextPaneText. Updates with Mancala as the gameChosen, 
	 * so the description for Mancala is what is expected. This class requires inheriting from the MainMenuView to get
	 * access to the text for testing.
	 * 
	 * @author Khuram C.
	 */
	@Test
	void testUpdate() {
		String expectedText = GameType.MANCALA.getGameDescription();
		view.update(null, GameType.MANCALA);
		assertEquals(expectedText,view.gameSummaryTextPane.getText());
	}
	
	/**
	 * Tests the getGameChoicesBoxChoice method. Once again, if nothing has been changed, BATTLESHIP is expected because as the
	 * first value in the enum, it is the 'default' and therefore expected GameType.
	 * 
	 * @author Khuram C.
	 */
	@Test
	void testGetGameChoicesBoxChoice() {
		GameType expectedGame = GameType.BATTLESHIP;
		GameType actualGame = view.getGameChoicesBoxChoice();
		assertEquals(expectedGame,actualGame);
		
	}

}
