package mastermindMenu.gameSettingsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.apache.maven.settings.Settings;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.gameSettings.GameSettings.GameSettingsPrompter;

/**
 * Tests the functionality of the GameSettings class used in the Mastermind game.
 * This class tests input validation, color settings, and initialization behavior of game settings.
 * @author Alon B.
 */
class MastermindSettingsTest {
	
	/**
     * Tests the validateInput method of GameSettingsPrompter for various input scenarios.
     * Validates that the method handles valid inputs, zero, negative, and non-numeric inputs correctly.
     */
	@Test
    public void testValidateInput() {
        // Testing valid input
        assertEquals(5, GameSettings.GameSettingsPrompter.validateInput("5", GameSettings.ERROR_INVALID_INPUT, GameSettings.ERROR_LENGTH_MUST_BE_GREATER_THAN_ZERO));
        
        // Testing input as zero
        assertEquals(0, GameSettings.GameSettingsPrompter.validateInput("0", GameSettings.ERROR_INVALID_INPUT, GameSettings.ERROR_LENGTH_MUST_BE_GREATER_THAN_ZERO));
        
        // Testing negative input
        assertEquals(0, GameSettings.GameSettingsPrompter.validateInput("-1", GameSettings.ERROR_INVALID_INPUT, GameSettings.ERROR_LENGTH_MUST_BE_GREATER_THAN_ZERO));
        
        // Testing non-integer input
        assertEquals(0, GameSettings.GameSettingsPrompter.validateInput("abc", GameSettings.ERROR_INVALID_INPUT, GameSettings.ERROR_LENGTH_MUST_BE_GREATER_THAN_ZERO));
    }
	
	/**
     * Tests the getColors method to ensure it returns the expected array of color codes.
     */
    @Test
    public void testGetColors() {
        String[] expectedColors = {"R", "G", "B", "Y", "O", "P"};
        assertArrayEquals(expectedColors, new GameSettings().getColors());
    }
    
    /**
     * Tests the getColorMap method to ensure it returns the expected array of Color objects.
     */
    @Test
    public void testGetColorMap() {
        Color[] expectedColorMap = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
        assertArrayEquals(expectedColorMap, new GameSettings().getColorMap());
    }

}






