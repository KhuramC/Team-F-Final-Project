import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;

public class GameControllerTest {
    private GameController controller;
    private MastermindGame game;
    private GameSettings settings;

    @Before
    public void setUp() {
        settings = new GameSettings();
        settings.setMaxTries(4);
        settings.setCodeLength(4);
        game = new MastermindGame(settings);
        controller = new GameController(game);
    }

    @Test
    public void testConstructor() {
        assertNotNull(controller);
        assertEquals(game, controller.game);
    }

    @Test
    public void testToggleColor() {
        JButton button = new JButton();
        button.setBackground(Color.RED);
        controller.toggleColor(new ActionEvent(button, 0, ""));
        assertEquals(Color.BLUE, button.getBackground());
    }

    @Test
    public void testStartGame() {
        controller.startGame();
        assertNotNull(controller.board);
        assertNotNull(controller.feedback);
    }

    @Test
    public void testSubmitGuess() {
        JButton[] guessButtonsRow = new JButton[4];
        for (int i = 0; i < 4; i++) {
            guessButtonsRow[i] = new JButton();
            guessButtonsRow[i].setBackground(Color.RED);
        }
        controller.submitGuess(new ActionEvent(guessButtonsRow[0], 0, "0"));
        assertEquals("RRRR", controller.feedback.getFeedbackArea());
    }

    @Test
    public void testIsGuessComplete() {
        JButton[] guessButtonsRow = new JButton[4];
        for (int i = 0; i < 4; i++) {
            guessButtonsRow[i] = new JButton();
            guessButtonsRow[i].setBackground(Color.RED);
        }
        assertTrue(controller.isGuessComplete(guessButtonsRow));
    }

    @Test
    public void testProvideFeedback() {
        String guess = "RRRG";
        String secretCode = "RRRY";
        assertEquals("Row 1 has 3 in the correct position and 1 correct color(s) but wrong in the wrong position.", controller.provideFeedback(guess));
    }

    @Test
    public void testHandleGameWon() {
        controller.handleGameWon();
        assertEquals("You guessed the code!", controller.feedback.getFeedbackArea());
    }

    @Test
    public void testHandleNextTurn() {
        controller.handleNextTurn(0);
        assertEquals(1, game.getCurrentTry());
    }

    @Test
    public void testResetGame() {
        controller.resetGame();
        assertEquals(0, game.getCurrentTry());
        assertNotNull(controller.board);
        assertNotNull(controller.feedback);
    }
}