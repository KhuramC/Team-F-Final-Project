package mastermindMenu.gameBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


/* 
 * This class makes the board of buttons and instantiates the listeners as well as sets up the submit buton.
 * @author Alon B.
 */
public class GameBoard extends JPanel {
	 private static final long serialVersionUID = 1L;
	    private JButton[][] guessButtons;

	    /**
	     * Creates a game board with a specified number of attempts and code length.
	     * Each row includes several buttons for setting the code colors and a submit button
	     * to finalize the guess.
	     * @param maxTries        The maximum number of guess attempts allowed.
	     * @param codeLength      The length of the secret code, determining the number of buttons per row.
	     * @param colorListener   The action listener for color change events on each guess button.
	     * @param submitListener  The action listener for the submit button in each row.
	     */
	    public GameBoard(int maxTries, int codeLength, ActionListener colorListener, ActionListener submitListener) {
	        setLayout(new GridLayout(maxTries, codeLength + 1)); // Make grid with maxTries by codeLength and add one for submit button.
	        guessButtons = new JButton[maxTries][codeLength];

	        for (int i = 0; i < maxTries; i++) { // Set up all buttons
	            for (int j = 0; j < codeLength; j++) {
	                JButton button = new JButton();
	                button.setBackground(Color.LIGHT_GRAY);
	                button.setPreferredSize(new Dimension(50, 50));
	                button.addActionListener(colorListener); 
	                guessButtons[i][j] = button;
	                add(button);
	            }
	            JButton submitButton = new JButton("Submit");// Set up submit buttons
	            submitButton.setActionCommand(String.valueOf(i));
	            submitButton.addActionListener(submitListener);
	            add(submitButton);
	        }
	    }

	    public JButton[][] getGuessButtons() {
	        return guessButtons;
	    }
}