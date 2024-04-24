package mancala;

import javax.swing.*;
import java.awt.*;

public class Mancala {
    private int[] board;
    private JFrame frame;
    private JTextArea textArea;

    public Mancala() {
        // There are 14 pits in Mancala: 6 for player A, 6 for player B, and 1 for each player's Mancala
        this.board = new int[14];

        // Create the window
        frame = new JFrame("Mancala Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a text area to display the board
        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(textArea);
    }

    public void startGame() {
        // Initialize the board. Each player's pits get 4 stones. The Mancalas start with 0.
        for (int i = 0; i < 14; i++) {
            if (i == 6 || i == 13) {
                board[i] = 0;
            } else {
                board[i] = 4;
            }
        }

        // Update the text area
        printBoard();

        // Show the window
        frame.setVisible(true);
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder("Current board:\n");
        sb.append("Player A: ");
        for (int i = 0; i < 6; i++) {
            sb.append(board[i]).append(" ");
        }
        sb.append("\nPlayer A Mancala: ").append(board[6]);

        sb.append("\nPlayer B: ");
        for (int i = 7; i < 13; i++) {
            sb.append(board[i]).append(" ");
        }
        sb.append("\nPlayer B Mancala: ").append(board[13]);

        textArea.setText(sb.toString());
    }
}