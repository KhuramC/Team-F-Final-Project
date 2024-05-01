package battleshipMenu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipShootingPhase extends JFrame {

    private JPanel gameBoardPanel;
    private int currentPlayer; // 1 for Player 1, 2 for Player 2
    private int numRows;
    private int numCols;
    private JButton[][] boardCells;
    Color lightBlue = new Color(173, 216, 230);
    Color hitColor = Color.RED;
    Color missColor = Color.BLUE;

    private JButton[][] player1BoardCells;
    private JButton[][] player2BoardCells;
    
    private String[][] player1GameBoard;
    private String[][] player2GameBoard;
	private String[][] player2GameBoardState;
    
	private boolean isPlayer1Turn = true;
	
    public BattleshipShootingPhase(int numRows, int numCols, String[][] player2GameBoardState) {
        this.numRows = numRows;
        this.numCols = numCols;
        currentPlayer = 1;
        setTitle("Battleship - Shooting Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);

        this.player2GameBoardState = player2GameBoardState; // Initialize player 2's game board state
        
        initializeSPhase(numRows, numCols);
        
        initializeGameBoards(numRows, numCols);
    }

    private void initializeSPhase(int numRows, int numCols) {

        gameBoardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Call DrawGrid to draw the grid lines
                DrawGrid.drawGridLines(g, numRows, numCols, 50, 50, 50);
            }
        };
        gameBoardPanel.setLayout(null); // Use absolute layout
        int cellSize = 50; // Adjust size as needed
        int startX = 50;
        int startY = 50;

        // Add column labels (letters)
        for (int col = 0; col < numCols; col++) {
            JLabel columnLabel = new JLabel(String.valueOf((char) ('A' + col)));
            columnLabel.setBounds(startX + col * cellSize + cellSize / 2, startY - 30, 20, 20);
            gameBoardPanel.add(columnLabel);
        }

        // Add row labels (numbers)
        for (int row = 0; row < numRows; row++) {
            JLabel rowLabel = new JLabel(String.valueOf(row + 1));
            rowLabel.setBounds(startX - 30, startY + row * cellSize + cellSize / 2, 20, 20);
            gameBoardPanel.add(rowLabel);
        }
        boardCells = new JButton[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JButton cellButton = new JButton();
                cellButton.setBounds(startX + col * cellSize, startY + row * cellSize, cellSize, cellSize);
                cellButton.setBackground(lightBlue);
                cellButton.setText("〰");
                cellButton.addActionListener(new ShootListener(row, col));
                boardCells[row][col] = cellButton;
                gameBoardPanel.add(cellButton);
            }
        }
        add(gameBoardPanel);
    }

    private class ShootListener implements ActionListener {
        private int row;
        private int col;

        public ShootListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to shoot here?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Check the cell to be a hit or miss
                String targetCell = player2GameBoardState[row][col];
                if (targetCell.equals("O")) {
                    boardCells[row][col].setBackground(hitColor);
                    JOptionPane.showMessageDialog(null, "It's a Hit!", "Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    boardCells[row][col].setBackground(missColor);
                    JOptionPane.showMessageDialog(null, "It's a Miss!", "Result", JOptionPane.INFORMATION_MESSAGE);
                }
                // Switch player turn
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
    }
    
 // Method to initialize game boards for both players
    private void initializeGameBoards(int numRows, int numCols) {
        player1GameBoard = new String[numRows][numCols];
        player2GameBoard = new String[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                player1GameBoard[row][col] = "〰"; // Initialize player 1's game board with empty cells
                player2GameBoard[row][col] = "〰"; // Initialize player 2's game board with empty cells
            }
        }
    }
    
    public void setPlayer1GameBoard(String[][] gameBoard) {
        this.player1GameBoard = gameBoard;
    }

    public void setPlayer2GameBoard(String[][] gameBoard) {
        this.player2GameBoard = gameBoard;
    }
}



