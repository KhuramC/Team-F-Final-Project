package mastermind.MastermindGameTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;

class mastermindGameTest {
    private MastermindGame game;
    private GameSettings settings;

    @BeforeEach
    void setUp() {
        // Create GameSettings with known values
        settings = new GameSettings(new String[] {"R", "G", "B", "Y"}, 4);
        game = new MastermindGame(settings);
    }
    
    void testResetGamePopulatesSecretCodeCorrectly() {
        game.resetGame();
        String[] secretCode = game.getSecretCode();
        assertNotNull(secretCode, "Secret code array should not be null");
        assertEquals(4, secretCode.length, "Secret code length should match settings");

    }
    
    @Test
    void testGameSettingsProvidesCorrectColors() {
        GameSettings settings = new GameSettings(new String[]{"R", "G", "B", "Y"}, 4);
        String[] colors = settings.getColors();
        assertNotNull(colors);
        assertTrue(Arrays.asList("R", "G", "B", "Y").containsAll(Arrays.asList(colors)));
    }

    @Test
    void testCurrentTryIsReset() {
        game.incrementCurrentTry();
        game.resetGame();
        assertEquals(0, game.getCurrentTry(), "Current try should be reset to 0 after resetGame");
    }

    @Test
    void testIncrementCurrentTry() {
        int initialTry = game.getCurrentTry();
        game.incrementCurrentTry();
        assertEquals(initialTry + 1, game.getCurrentTry(), "Current try should increment by 1");
    }

    @Test
    void testGetSettings() {
        assertSame(settings, game.getSettings(), "getSettings should return the original settings object used in construction");
    }
}
