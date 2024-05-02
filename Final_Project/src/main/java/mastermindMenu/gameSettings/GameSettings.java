package mastermindMenu.gameSettings;

import java.awt.Color;

import javax.swing.JOptionPane;

public class GameSettings {
	private static final String[] COLORS = {"R", "G", "B", "Y", "O", "P"};
    private static final Color[] COLOR_MAP = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
    private int codeLength;
    private int maxTries;

    public GameSettings() {
        // Initialize code length and max tries by prompting the user
        setCodeLength();
        setMaxTries();
    }

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
