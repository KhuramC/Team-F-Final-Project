package mastermindMenu.gameBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
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
	     * @param maxTries The maximum number of guess attempts allowed.
	     * @param codeLength The length of the secret code, determining the number of buttons per row.
	     * @param colorListener The action listener for color change events on each guess button.
	     * @param submitListener The action listener for the submit button in each row.
	     */
	    public GameBoard(int maxTries, int codeLength, ActionListener colorListener, ActionListener submitListener) {
	        setLayout(new GridLayout(maxTries, codeLength + 1)); // Make grid with maxTries by codeLength and add one for submit button.
	        guessButtons = new JButton[maxTries][codeLength];
	        initializeButtons(maxTries, codeLength, colorListener, submitListener);
	    }
	    
	    
	    /**
	     * Initializes the buttons on the game board.
	     * @param maxTries The maximum number of attempts.
	     * @param codeLength The length of the code in the game.
	     * @param colorListener The action listener for color change actions.
	     * @param submitListener The action listener for submit actions.
	     */
	    private void initializeButtons(int maxTries, int codeLength, ActionListener colorListener, ActionListener submitListener) {
	    for (int i = 0; i < maxTries; i++) { // Set up all buttons
            for (int j = 0; j < codeLength; j++) {
                JButton button = new JButton();
                button.setEnabled(i == 0); // Initialize, only enable the first row
                button.setBackground(Color.WHITE);
                button.setPreferredSize(new Dimension(50, 50));
                button.addActionListener(colorListener); 
                button.setBorderPainted(true);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // Add a black line border with a thickness of 1 (has to be int)
                guessButtons[i][j] = button;
                add(button);
            }
            JButton submitButton = new JButton("Submit");// Set up submit buttons
            submitButton.setActionCommand(String.valueOf(i));
            submitButton.addActionListener(submitListener);
            add(submitButton);
        }
	   }
	   
	    
	    /**
	     * This is the class specific part that needs to reset when the game is reset.
	     */
	    public void resetBoard() {
	        IntStream.range(0, guessButtons.length).forEach(row -> {
	            for (JButton button : guessButtons[row]) {
	            	button.setBackground(Color.WHITE);  // Set default color
	                button.setEnabled(row == 0);  // Enable only the first row
	                button.repaint();
	            }
	        });
	    }
	    
	    /**
	     * Enables or disables a row of buttons.
	     * @param row The row index to enable or disable.
	     * @param enable True to enable the row, false to disable it.
	     */
	    public void setRowEnabled(int row, boolean enable) {
	        for (JButton button : guessButtons[row]) {
	            button.setEnabled(enable);
	        }
	    }
//	    public void enableRow(int row) {
//	        for (JButton button : guessButtons[row]) {
//	            button.setEnabled(true);
//	            button.setBackground(Color.white);
//	            button.repaint();
//	            }
//	    }

	    public JButton[][] getGuessButtons() {
	        return guessButtons;
	    }
}