package mastermindMenu.gameSettingsTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.gameSettings.GameSettings.GameSettingsPrompter;

class MastermindSettingsTest {

	@Test
    public void testGameSettings_Constructor() {
        GameSettings gameSettings = new GameSettings();
        assertNotNull(gameSettings);
    }

    @Test
    public void testGameSettings_GetColors() {
        GameSettings gameSettings = new GameSettings();
        String[] colors = gameSettings.getColors();
        assertEquals(colors, colors);
    }

    @Test
    public void testGameSettings_GetColorMap() {
        GameSettings gameSettings = new GameSettings();
        Color[] colorMap = gameSettings.getColorMap();
        assertEquals(colorMap, colorMap);
    }

    @Test
    public void testGameSettings_GetCodeLength() {
        GameSettings gameSettings = new GameSettings(4, 10);
        assertEquals(4, gameSettings.getCodeLength());
    }


    @Test
    public void testGameSettings_GetMaxTries() {
        GameSettings gameSettings = new GameSettings(4, 10);
        assertEquals(10, gameSettings.getMaxTries());
    }

    @Test
    public void testValidateInput_InputIsZero() {
        int input = 0;
        String errorMessageInvalidInput = "Error: Code length must be greater than 0.";
        String errorMessageOutOfRange = "Error: Number of tries must be greater than 0.";
        int result = GameSettingsPrompter.validateInput(Integer.toString(input), errorMessageInvalidInput, errorMessageOutOfRange);
        assertEquals(0, result);
    }

}
