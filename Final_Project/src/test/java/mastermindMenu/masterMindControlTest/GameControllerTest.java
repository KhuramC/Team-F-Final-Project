package mastermindMenu.masterMindControlTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;

	/**
     * Test class for GameController.
     * This class contains various tests to ensure that GameController
     * behaves correctly under different scenarios.
     * @author Alon B.
     */
public class GameControllerTest {
    private GameController controller;
    private MastermindGame game;
    private GameSettings settings;
    

    /**
     * Sets up the environment for testing GameController.
     * Initializes GameSettings, MastermindGame, and GameController with some predefined settings.
     */
    @BeforeEach
    public void setUp() {
        settings = new GameSettings(); // Because of BeforeEach the user input will pop up every time a test needs to be ran but will not actually take the values
        settings.setMaxTries(4); // Set values instead of user input
        settings.setCodeLength(4);
        game = new MastermindGame(settings);
        game.setSecretCode(new String[] {"R", "G", "B", "Y"});
        controller = new GameController(game);
    }
    /**
     * Tests the constructor of GameController to ensure it properly initializes.
     */
    @Test
    public void testConstructor() {
        assertNotNull(controller);
        assertEquals(game, controller.game);
    }
    /**
     * Tests the toggleColor method to ensure it changes the button's color as expected.
     */
    @Test
    public void testToggleColor() {
        JButton button = new JButton();
        button.setBackground(Color.RED);
        controller.toggleColor(new ActionEvent(button, 0, ""));
        assertEquals(Color.GREEN, button.getBackground());
    }

    /**
     * Tests the startGame method to ensure all components are correctly initialized for starting the game.
     */
    @Test
    public void testStartGame() {
        controller.startGame();
        assertNotNull(controller.board);
        assertNotNull(controller.feedback);
    }
    
    /**
     * Tests the submitGuess method to ensure that the guess is correctly submitted and feedback is updated.
     */
    @Test
    public void testCorrectGuess() {
        // Simulate setting the correct colors
        JButton[] guessButtonsRow = controller.board.getGuessButtons()[0];
        guessButtonsRow[0].setBackground(Color.RED);  
        guessButtonsRow[1].setBackground(Color.GREEN); 
        guessButtonsRow[2].setBackground(Color.BLUE);  
        guessButtonsRow[3].setBackground(Color.YELLOW); 

        // Simulate pressing the submit button
        controller.submitGuess(new ActionEvent(guessButtonsRow[0], ActionEvent.ACTION_PERFORMED, "0"));

        String expectedFeedback = "Correct! The code was RGYB";
        assertEquals(expectedFeedback, controller.feedback.getFeedbackArea(),
                     "Feedback should exactly match the correct guess message.");
    }
    
    @Test
    public void testIncorrectGuess() {
        // Simulate setting incorrect colors
        JButton[] guessButtonsRow = controller.board.getGuessButtons()[0];
        guessButtonsRow[0].setBackground(Color.YELLOW); // Incorrect code
        guessButtonsRow[1].setBackground(Color.BLUE);   
        guessButtonsRow[2].setBackground(Color.GREEN);  
        guessButtonsRow[3].setBackground(Color.RED);    

        // Simulate pressing the submit button
        controller.submitGuess(new ActionEvent(guessButtonsRow[0], ActionEvent.ACTION_PERFORMED, "0"));
        
        // Incorrect feedback should not be "Correct! The code was YBGR"
        String incorrectFeedback = "Correct! The code was YBGR";
        assertNotEquals(incorrectFeedback, controller.feedback.getFeedbackArea(),
                        "Feedback should not incorrectly validate the wrong guess.");
    }


    /**
     * Tests the isGuessComplete method to check if a guess row is fully populated.
     */
    @Test
    public void testIsGuessComplete() {
        JButton[] guessButtonsRow = new JButton[4];
        for (int i = 0; i < 4; i++) {
            guessButtonsRow[i] = new JButton();
            guessButtonsRow[i].setBackground(Color.RED);
        }
        assertTrue(controller.isGuessComplete(guessButtonsRow));
    }
    
    /**
     * Tests the provideFeedback method to ensure it returns the correct feedback for a guess.
     */
    @Test
    public void testProvideFeedback() {
    	game.setSecretCode(new String[]{"R", "G", "R", "R"});
        String guess = "RRRG";
        assertEquals("Row 1 has 2 in the correct position and 2 correct color(s) but wrong in the wrong position.", controller.provideFeedback(guess));
    }
    
    /**
     * Tests the handleGameWon method to ensure it properly handles a game win scenario.
     */
    @Test
    public void testHandleGameWon() {
        controller.handleGameWon();
        assertEquals("You guessed the code!", controller.feedback.getFeedbackArea(),
                     "Feedback area should display the game won message.");
    }

    /**
     * Tests the handleNextTurn method to ensure it properly sets up for the next turn.
     */
    @Test
    public void testHandleNextTurn() {
        controller.handleNextTurn(0);
        assertEquals(1, game.getCurrentTry());
    }
    
    /**
     * Tests the resetGame method to ensure it resets the game state appropriately.
     */
    @Test
    public void testResetGame() {
        controller.resetGame();
        assertEquals(0, game.getCurrentTry());
        assertNotNull(controller.board);
        assertNotNull(controller.feedback);
    }
}