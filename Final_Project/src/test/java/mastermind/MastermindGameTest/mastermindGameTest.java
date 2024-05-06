package mastermind.MastermindGameTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;

public class mastermindGameTest{
	

	private MastermindGame game = null;
    private GameSettings settings;

    @BeforeEach
    public void setUp() {
        settings = new GameSettings();
        settings.setCodeLength(4);
        settings.setMaxTries(10);
        game = new MastermindGame(settings);
    }

    @Test
    public void testConstructor() {
        assertNotNull(game);
        assertEquals(settings, game.getSettings());
        assertNotNull(game.secretCode);
        assertEquals(settings.getCodeLength(), game.secretCode.length);
        assertEquals(0, game.getCurrentTry());
    }

    @Test
    public void testResetGame() {
        game.resetGame();
        assertNotNull(game.secretCode);
        assertEquals(settings.getCodeLength(), game.secretCode.length);
        assertEquals(0, game.getCurrentTry());
        for (String color : game.secretCode) {
            assertTrue(settings.getColors()[0].equals(color) || settings.getColors()[1].equals(color) || settings.getColors()[2].equals(color) || settings.getColors()[3].equals(color));
        }
    }

    @Test
    public void testGetSecretCode() {
        String[] secretCode = game.getSecretCode();
        assertNotNull(secretCode);
        assertEquals(settings.getCodeLength(), secretCode.length);
    }

    @Test
    public void testGetCurrentTry() {
        assertEquals(0, game.getCurrentTry());
        game.incrementCurrentTry();
        assertEquals(1, game.getCurrentTry());
    }

    @Test
    public void testGetSettings() {
        assertEquals(settings, game.getSettings());
    }

    @Test
    public void testSetSecretCode() {
        String[] newSecretCode = new String[] {"Red", "Blue", "Green", "Yellow"};
        game.setSecretCode(newSecretCode);
        assertArrayEquals(newSecretCode, game.getSecretCode());
    }
}