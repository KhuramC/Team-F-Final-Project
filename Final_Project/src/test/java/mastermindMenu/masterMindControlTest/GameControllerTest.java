import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;

public class GameControllerTest {
    private GameSettings settings;

    @Mock
    private MastermindGame game;

    private GameController gameController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        GameSettings settings = new GameSettings("RGBY", 4);
        when(game.getSettings()).thenReturn(settings);
        gameController = new GameController(game);
    }


    @Test
    public void testToggleColor() {
        // Assuming the initial color is LIGHT_GRAY
        Color[] expectedColors = new Color[] { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
        JButton button = new JButton();

        for (Color expectedColor : expectedColors) {
            gameController.toggleColor(new ActionEvent(button, 0, null));
            assertEquals(expectedColor, button.getBackground());
        }

        // After cycling through all colors, it should go back to the first color
        gameController.toggleColor(new ActionEvent(button, 0, null));
        assertEquals(expectedColors[0], button.getBackground());
    }

    @Test
    public void testIsGuessComplete() {
        JButton[] guessButtonsRow = new JButton[4];
        Arrays.fill(guessButtonsRow, new JButton());

        // All buttons are in default color (LIGHT_GRAY), so the guess is incomplete
        assertFalse(gameController.isGuessComplete(guessButtonsRow));

        // Set all buttons to a valid color
        for (JButton button : guessButtonsRow) {
            button.setBackground(Color.RED);
        }

        // Now the guess is complete
        assertTrue(gameController.isGuessComplete(guessButtonsRow));
    }

    @Test
    public void testProvideFeedback() {
        game.setSecretCode(new String[] { "R", "B", "G", "Y" });

        String guess = "RBYG";
        String feedback = gameController.provideFeedback(guess);
        assertTrue(feedback.contains("2 in the correct position and 2 correct color(s) but wrong in the wrong position."));

        guess = "RRGG";
        feedback = gameController.provideFeedback(guess);
        assertTrue(feedback.contains("0 in the correct position and 2 correct color(s) but wrong in the wrong position."));

        guess = "RBYY";
        feedback = gameController.provideFeedback(guess);
        assertTrue(feedback.contains("2 in the correct position and 1 correct color(s) but wrong in the wrong position."));
    }

    // Add more tests as needed
}