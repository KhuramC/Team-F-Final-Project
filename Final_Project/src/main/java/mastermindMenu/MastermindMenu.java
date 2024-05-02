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
        JPanel gamePanel = new JPanel(new GridLayout(maxTries, codeLength + 1));
        guessButtons = new JButton[maxTries][codeLength];

        for (int i = 0; i < maxTries; i++) {
        	int finalI = i;
            for (int j = 0; j < codeLength; j++) {
                JButton button = new JButton();
                button.setBackground(Color.LIGHT_GRAY);
                button.setPreferredSize(new Dimension(50, 50));
                int finalJ = j;
                button.addActionListener(e -> toggleColor(button, finalI, finalJ));
                guessButtons[i][j] = button;
                gamePanel.add(button);
            }
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> submitGuess(finalI));
            gamePanel.add(submitButton);
        }

        add(gamePanel, BorderLayout.CENTER);
        setupFeedbackPanel();
    }

    private void toggleColor(JButton button, int row, int col) {
        if (row != currentTry) return;  // Only allow changes to the current row
        Color currentColor = button.getBackground();
        int nextColorIndex = (Arrays.asList(COLOR_MAP).indexOf(currentColor) + 1) % COLOR_MAP.length;
        button.setBackground(COLOR_MAP[nextColorIndex]);
    }

    private void submitGuess(int row) {
        if (row != currentTry) return; // Ensure only current row can submit
        
        String guess = Arrays.stream(guessButtons[row])
                             .map(button -> colorToChar(button.getBackground()))
                             .collect(Collectors.joining());

        if (guess.equals(String.join("", secretCode))) {
            feedbackArea.append("Correct! The code was " + guess + "\n");
            int playAgain = JOptionPane.showConfirmDialog(this, "You guessed correctly! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (playAgain == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        } else {
            String feedback = provideFeedback(guess);
            feedbackArea.append(feedback + "\n");
            currentTry++;
            if (currentTry < maxTries) {
                enableRow(currentTry, true); // Enable next row
                enableRow(currentTry - 1, false); // Disable the previous row
            } else {
                feedbackArea.append("You've run out of tries! The correct code was " + String.join("", secretCode) + "\n");
                int playAgain = JOptionPane.showConfirmDialog(this, "You're out of tries! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
        }
    }


    private String provideFeedback(String guess) {
        int correctPosition = 0;
        int correctColor = 0;
        boolean[] secretChecked = new boolean[codeLength];
        boolean[] guessChecked = new boolean[codeLength];

        for (int i = 0; i < codeLength; i++) {
            if (guess.charAt(i) == secretCode[i].charAt(0)) {
                correctPosition++;
                secretChecked[i] = true;
                guessChecked[i] = true;
            }
        }

        for (int i = 0; i < codeLength; i++) {
            if (!guessChecked[i]) {
                for (int j = 0; j < codeLength; j++) {
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

    private void resetGame() {
        Random random = new Random();
        secretCode = new String[codeLength];
        for (int i = 0; i < codeLength; i++) {
            secretCode[i] = COLORS[random.nextInt(COLORS.length)];
        }
        currentTry = 0;
        feedbackArea.setText("");
        IntStream.range(0, maxTries).forEach(row -> enableRow(row, row == 0));
    }

    private void setupFeedbackPanel() {
        feedbackArea = new JTextArea(5, 30);
        feedbackArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void enableRow(int row, boolean enable) {
        for (JButton button : guessButtons[row]) {
            button.setEnabled(enable);
        }
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
