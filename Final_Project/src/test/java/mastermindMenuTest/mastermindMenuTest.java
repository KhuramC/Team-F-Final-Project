package mastermindMenuTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import mastermindMenu.MastermindMenu;
import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;

/** 
 * @author Alon B.
 */
class mastermindMenuTest {

	/** 
	 * Made to test the one method in menu that is testable.
	 */
//	@Test
//	void testStartMastermindRuns() {
//		assertDoesNotThrow(() -> MastermindMenu.startMastermind());
//	}
	 private MastermindMenu menu;
	    private GameSettings settings;
	    private MastermindGame game;
	    private GameController controller;

	    @BeforeEach
	    public void setUp() {
	        // Initialize real objects
	        settings = new GameSettings(); // Assume default settings are sufficient for testing
	        game = new MastermindGame(settings); // Assume this can be initialized with settings
	        controller = new GameController(game); // Assume the controller can be initialized with game

	        // Initialize the MastermindMenu with real dependencies
	        menu = new MastermindMenu(settings, game, controller);
	    }

	    @Test
	    public void testStartGame() {
	        assertFalse(game.isStarted(), "Game should not be started initially.");

	        // Start the game using MastermindMenu
	        menu.startGame();

	        // Verify the game is started
	        assertTrue(game.isStarted(), "Game should be marked as started after calling startGame.");
	    }
	}