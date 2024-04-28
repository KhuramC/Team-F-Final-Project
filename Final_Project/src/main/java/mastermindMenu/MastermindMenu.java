package mastermindMenu;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MastermindMenu extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String[] COLORS = {"R", "G", "B", "Y", "O", "P"};
    private static final Color[] COLOR_MAP = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA};
    private int codeLength;
    private int maxTries;
    private JButton[][] guessButtons;
    private JTextArea feedbackArea;
    private String[] secretCode;
    private int currentTry;

    public MastermindMenu() {
        codeLength = Integer.parseInt(JOptionPane.showInputDialog("Enter the length of the code:"));
        maxTries = Integer.parseInt(JOptionPane.showInputDialog("Enter the maximum number of tries:"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setTitle("Mastermind Game");
        setLayout(new BorderLayout());
        initializeGameComponents();
        resetGame();
    }

    private void initializeGameComponents() {

    }

    private void toggleColor(JButton button, int row, int col) {

    }

    private void submitGuess(int row) {

    }


    private String provideFeedback(String guess) {
 
    }

    private void resetGame() {

    }

    private void setupFeedbackPanel() {

    }

    private void enableRow(int row, boolean enable) {

    }

    private String colorToChar(Color color) {
        if (Color.RED.equals(color)) return "R";
        if (Color.GREEN.equals(color)) return "G";
        if (Color.BLUE.equals(color)) return "B";
        if (Color.YELLOW.equals(color)) return "Y";
        if (Color.ORANGE.equals(color)) return "O";
        if (Color.MAGENTA.equals(color)) return "P";
        return "";  // Return an empty string for unmatched colors
    }

}
