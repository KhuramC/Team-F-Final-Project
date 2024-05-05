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

public class GameController {
    private MastermindGame game; 
    private GameBoard board;
    private FeedbackPanel feedback;

    public GameController(MastermindGame game) {
        this.game = game;
        this.board = new GameBoard(game.getSettings().getMaxTries(), game.getSettings().getCodeLength(), this::toggleColor, this::submitGuess);
        this.feedback = new FeedbackPanel();
    }
    
    /*
     * @Param 
     */
    private void toggleColor(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Color currentColor = button.getBackground();
        Color[] colorMap = game.getSettings().getColorMap();
        int nextColorIndex = Arrays.asList(colorMap).indexOf(currentColor) + 1;
        button.setBackground(colorMap[nextColorIndex % colorMap.length]);
        button.setOpaque(true);  // Make sure the button is opaque
        button.setBorderPainted(false);  // Optional: disable border painting if it interferes
        button.repaint(); // Make sure the change is visible
    }

    public void startGame() {
        JFrame frame = new JFrame("Mastermind Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.add(board, BorderLayout.CENTER);
        frame.add(feedback, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    private void submitGuess(ActionEvent e) {
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

        boolean isCorrect = guess.equals(String.join("", game.getSecretCode()));
        feedback.appendFeedback(isCorrect ? "Correct! The code was " + guess : provideFeedback(guess));

        if (isCorrect) {
            handleGameWon();
        } else {
            handleNextTurn(row);
        }
    }

    private boolean isGuessComplete(JButton[] guessButtonsRow) {
        return Arrays.stream(guessButtonsRow).noneMatch(button -> button.getBackground() == Color.LIGHT_GRAY);
    }

    private String provideFeedback(String guess) {
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

        return correctPosition + " exact, " + correctColor + " correct color but wrong position.";
    }

    private void handleGameWon() {
        JOptionPane.showMessageDialog(board, "You guessed the code! Starting new game.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        game.resetGame();
    }

    private void handleNextTurn(int currentRow) {
        if (currentRow + 1 < game.getSettings().getMaxTries()) {
            game.incrementCurrentTry();
            board.getGuessButtons()[currentRow + 1][0].setEnabled(true); // Enable the next row
        } else {
            JOptionPane.showMessageDialog(board, "No more tries left! The correct code was " + String.join("", game.getSecretCode()), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            game.resetGame();
        }
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
