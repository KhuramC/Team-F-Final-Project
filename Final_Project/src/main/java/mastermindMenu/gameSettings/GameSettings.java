package mastermindMenu.gameSettings;

import java.awt.Color;

import javax.swing.JOptionPane;


/**
 * Configures the game settings for Mastermind, including the allowable colors,
 * code length, and maximum number of tries.
 * This class prompts the user for input to set these parameters upon instantiation.
 * @author Alon B.
 */
public class GameSettings {
	private static final String[] COLORS = {"R", "G", "B", "Y", "O", "P"};
    private static final Color[] COLOR_MAP = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
    private int codeLength;
    private int maxTries;
	private String[] testColors;
	private int testCodeLength;

    /**
     * Constructs a new GameSettings object by initializing code length and max tries
     * through user input dialogs.
     */
    public GameSettings() {
    	getUserInput();
    }
    
    /**
     * This is a test-friendly constuctor used to help 
     * @param colors		testing colors
     * @param codeLength	Testing code length
     */
    public GameSettings(String[] colors, int codeLength) {
        this.testColors = colors;
        this.testCodeLength = codeLength;
    }

    public String[] gettestColors() {
        return testColors;
    }

    public int gettestCodeLength() {
        return testCodeLength;
    }
    
    /**
     * Prompts the user to input the game settings for code length and maximum number of tries.
     */
    private void getUserInput() {
    	setCodeLength();
    	setMaxTries();
    }
    
    /**
     * Prompts the user to set the code length for the Mastermind game.
     * Ensures that the input is a positive integer.
     */
    private void setCodeLength() {
        String input;
        do {
            input = JOptionPane.showInputDialog(null, "Enter the length of the code (must be greater than 0):", "Game Settings", JOptionPane.QUESTION_MESSAGE);
            try {
                codeLength = Integer.parseInt(input);
                if (codeLength <= 0) { 
                    JOptionPane.showMessageDialog(null, "Error: Code length must be greater than 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                codeLength = 0;
            }
        } while (codeLength <= 0);
    }
    
    /**
     * Prompts the user to set the maximum number of tries allowed in the game.
     * Ensures that the input is a positive integer.
     */
    private void setMaxTries() {
        String input;
        do {
            input = JOptionPane.showInputDialog(null, "Enter the maximum number of tries (must be greater than 0):", "Game Settings", JOptionPane.QUESTION_MESSAGE);
            try {
                maxTries = Integer.parseInt(input);
                if (maxTries <= 0) {
                    JOptionPane.showMessageDialog(null, "Error: Number of tries must be greater than 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                maxTries = 0;
            }
        } while (maxTries <= 0);
    }

    public String[] getColors() {
        return COLORS;
    }

    public Color[] getColorMap() {
        return COLOR_MAP;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public int getMaxTries() {
        return maxTries;
    }  
}
