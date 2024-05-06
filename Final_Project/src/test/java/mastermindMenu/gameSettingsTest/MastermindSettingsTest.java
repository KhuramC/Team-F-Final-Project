package mastermindMenu.gameSettingsTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.gameSettings.GameSettings.GameSettingsPrompter;

class MastermindSettingsTest {
//	private GameSettings gameSettings;
//	private GameSettingsPrompter gameSettingsPrompter;

//	@Before
//	public void setup() {
//	    gameSettings = new GameSettings();
//	    gameSettingsPrompter = gameSettings.new GameSettingsPrompter();
//	}

//	@Test
//	public void testUserCodeLength() {
//	    // Mock user input for code length
//	    when(JOptionPane.showInputDialog("Enter the length of the code (must be greater than 0):")).thenReturn("4");
//	    gameSettingsPrompter.promptCodeLength();
//	    assertEquals(4, gameSettings.getCodeLength());
//	}
//
//	@Test
//	public void testUserMaxTries() {
//	    // Mock user input for max tries
//	    when(JOptionPane.showInputDialog("Enter the maximum number of tries (must be greater than 0):")).thenReturn("10");
//		gameSettingsPrompter.promptMaxTries();
//	    assertEquals(10, gameSettings.getMaxTries());
//	} 

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

    @Test
    public void testGetColors() {
        String[] expectedColors = {"R", "G", "B", "Y", "O", "P"};
        assertArrayEquals(expectedColors, new GameSettings().getColors());
    }

    @Test
    public void testGetColorMap() {
        Color[] expectedColorMap = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
        assertArrayEquals(expectedColorMap, new GameSettings().getColorMap());
    }

    // You cannot test getCodeLength() and getMaxTries() without initializing through JOptionPane,
    // so we skip these tests.
}





