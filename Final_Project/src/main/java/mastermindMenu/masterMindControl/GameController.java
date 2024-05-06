package mastermindMenu.masterMindControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.feedback.FeedbackPanel;
import mastermindMenu.gameBoard.GameBoard;

/**
 * /**
 * Controls the logic and interactions within the Mastermind game,
 * linking together the game's UI and the game state.
 * @author Alon B.
 * 
 */
public class GameController {
    public MastermindGame game; 
    public GameBoard board;
    public FeedbackPanel feedback;
    
    /**
     * Constructs a GameController for managing a Mastermind game.
     * @param game The Mastermind game logic handler.
     */
    public GameController(MastermindGame game) {
        this.game = game;
        this.board = new GameBoard(game.getSettings().getMaxTries(), game.getSettings().getCodeLength(), this::toggleColor, this::submitGuess);
        this.feedback = new FeedbackPanel();
    }
    
    /**
     * Toggles the color of a button based on a sequential click, cycling through predefined colors.
     * @param e The action event triggered by clicking a guess button.
     */
    public void toggleColor(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Color currentColor = button.getBackground();
        Color[] colorMap = game.getSettings().getColorMap();
        int nextColorIndex = Arrays.asList(colorMap).indexOf(currentColor) + 1;
        button.setBackground(colorMap[nextColorIndex % colorMap.length]);
        button.setOpaque(true);
        button.repaint(); // Make sure the change is visible
    }
    
    /**
     * Initiates and displays the game frame, including all components.
     */
    public void startGame() {
        JFrame frame = new JFrame("Mastermind Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.add(board, BorderLayout.CENTER);
        frame.add(feedback, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    /**
     * Handles the guess submission logic, determining if the guess is correct and managing game progression.
     * @param e The action event triggered by the submit button.
     */
    public void submitGuess(ActionEvent e) {
        int row = Integer.parseInt(e.getActionCommand());
        if (row != game.getCurrentTry()) return; // Ensure only the current row can submit

        JButton[] guessButtonsRow = board.getGuessButtons()[row];
        String guess = Arrays.stream(guessButtonsRow)
        	    .map(button -> colorToChar(button.getBackground()))
        	    .filter(Objects::nonNull)  // Ensure no null values are processed
        	    .map(result -> (String) result[1])  // Extract the String representation from the array
        	    .collect(Collectors.joining());  // Collect the strings into a single string
        
        
        
        if (!isGuessComplete(guessButtonsRow)) { // Basic Check
            JOptionPane.showMessageDialog(board, "Please select a color for each position in the row before submitting.", "Incomplete Guess", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        board.setRowEnabled(row, false);

        boolean isCorrect = guess.equals(String.join("", game.getSecretCode()));
        feedback.appendFeedback(isCorrect ? "Correct! The code was " + guess : provideFeedback(guess));

        if (isCorrect) {
            handleGameWon();
        } else {
            handleNextTurn(row);
        }
    }

    /**
     * Checks if all buttons in a row have been properly set to a color different from the default.
     * @param guessButtonsRow The row of buttons to check.
     * @return true if all buttons are set; otherwise, false.
     */
    public boolean isGuessComplete(JButton[] guessButtonsRow) {
        return Arrays.stream(guessButtonsRow).noneMatch(button -> button.getBackground() == Color.LIGHT_GRAY);
    }
    
    /**
     * Provides feedback for the current guess compared to the secret code.
     * @param guess The current guess.
     * @return A string detailing the results of the guess.
     */
    public String provideFeedback(String guess) {
        int correctPosition = 0;
        int correctColor = 0;
        String[] secretCode = game.getSecretCode();
        boolean[] secretChecked = new boolean[secretCode.length];
        boolean[] guessChecked = new boolean[guess.length()];

        for (int i = 0; i < secretCode.length; i++) {
            if (guess.charAt(i) == secretCode[i].charAt(0)) {
                correctPosition++;
                secretChecked[i] = true;
                guessChecked[i] = true;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (!guessChecked[i]) {
                for (int j = 0; j < secretCode.length; j++) {
                    if (!secretChecked[j] && guess.charAt(i) == secretCode[j].charAt(0)) {
                        correctColor++;
                        secretChecked[j] = true;
                        break;
                    }
                }
            }
        }

        return "Row " + (game.getCurrentTry() + 1) + " has " + correctPosition + " in the correct position and " + correctColor + " correct color(s) but wrong in the wrong position.";
    }
    
    
    public void handleGameWon() {
        JOptionPane.showMessageDialog(board, "You guessed the code!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        showPlayAgainOption();
    }

    /**
     * If the next turn is not the given maximum number of tries then the player has lost
     * @param currentRow
     */
    public void handleNextTurn(int currentRow) {
        if (currentRow + 1 < game.getSettings().getMaxTries()) {
            game.incrementCurrentTry();
           // board.getGuessButtons()[currentRow + 1][0].setEnabled(true); // Enable the next row
            board.setRowEnabled(currentRow + 1, true); // Enable next row
        } else {
            JOptionPane.showMessageDialog(board, "No more tries left! The correct code was " + String.join("", game.getSecretCode()), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            showPlayAgainOption();
        }
    }
    
    /** 
     * Helper method to handle if user would like to play again
     * If the user clicks no they will be redirected back to the main menu
     */
    private void showPlayAgainOption() {
        int response = JOptionPane.showConfirmDialog(board,
            "Would you like to play again?", "Game Over",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);  //Exit game if needed
        }
    }
   
    /**
     * Resets both the game logic and UI components.
     */
    public void resetGame() {
        game.resetGame();  // Reset the game logic
        board.resetBoard();  // Reset the game board UI
        feedback.clearFeedback();  // Clear the feedback panel
    }

    private Object[] colorToChar(Color color) {
        Color[] colorMap = game.getSettings().getColorMap();
        String[] colors = game.getSettings().getColors();  // Assuming getColors() returns the names corresponding to the colors

        for (int i = 0; i < colorMap.length; i++) {
            if (colorMap[i].equals(color)) {
                return new Object[] {colorMap[i], colors[i]};  // Return an array containing both Color and String
            }
        }
        return null; // Return null if no matching color found
    }
}
