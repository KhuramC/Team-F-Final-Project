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
	public static final String ERROR_INVALID_INPUT = "Please enter a valid number.";
    public static final String ERROR_LENGTH_MUST_BE_GREATER_THAN_ZERO = "Error: Code length must be greater than 0.";
    private static final String ERROR_MAX_TRIES_MUST_BE_GREATER_THAN_ZERO = "Error: Number of tries must be greater than 0.";

    /**
     * Constructs a new GameSettings object by initializing code length and max tries
     * through user input dialogs.
     */
    public GameSettings() {
    	getUserInput();
    }
    
    
    
    
    public class GameSettingsPrompter {
    	
        /**
         * Prompts the user to set the code length for the Mastermind game.
         * Ensures that the input is a positive integer.
         */
        public int promptCodeLength() {
            int codeLength;
            do {
                String input = JOptionPane.showInputDialog(null, "Enter the length of the code (must be greater than 0):", "Game Settings", JOptionPane.QUESTION_MESSAGE);
                codeLength = validateInput(input, ERROR_INVALID_INPUT, ERROR_LENGTH_MUST_BE_GREATER_THAN_ZERO);
            } while (codeLength <= 0);
            return codeLength;
        }

        /**
         * Prompts the user to set the maximum number of tries allowed in the game.
         * Ensures that the input is a positive integer.
         */
        public int promptMaxTries() {
            int maxTries;
            do {
                String input = JOptionPane.showInputDialog(null, "Enter the maximum number of tries (must be greater than 0):", "Game Settings", JOptionPane.QUESTION_MESSAGE);
                maxTries = validateInput(input, ERROR_INVALID_INPUT, ERROR_MAX_TRIES_MUST_BE_GREATER_THAN_ZERO);
            } while (maxTries <= 0);
            return maxTries;
        }
        /**
         * Does the basic error check to see if input number is greater than 0
         * @param input
         * @param errorMessageInvalidInput
         * @param errorMessageOutOfRange
         * @return value if it is a valid argument 
         */
        public static int validateInput(String input, String errorInvalidInput, String errorOutOfRange) {
            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    JOptionPane.showMessageDialog(null, errorOutOfRange, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
                return value;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, errorInvalidInput, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
    }
    

	/**
     * Prompts the user to input the game settings for code length and maximum number of tries.
     */
    private void getUserInput() {
        GameSettingsPrompter prompter = new GameSettingsPrompter();
        codeLength = prompter.promptCodeLength();
        maxTries = prompter.promptMaxTries();
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


	public void setMaxTries(int i) {
		maxTries = i;
	}


	public void setCodeLength(int i) {
		codeLength = i;
	}
    
}
