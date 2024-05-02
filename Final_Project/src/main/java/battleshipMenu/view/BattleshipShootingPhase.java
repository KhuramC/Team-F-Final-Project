package battleshipMenu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BattleshipShootingPhase extends JFrame {

    private int currentPlayer; // 1 for Player 1, 2 for Player 2
    private int numRows;
    private int numCols;
    private JButton[][] P1Board;
    private JButton[][] P2Board;
    Color lightBlue = new Color(173, 216, 230);
    Color hitColor = Color.RED;
    Color missColor = Color.BLUE;

    private JButton[][] player1BoardCells;
    private JButton[][] player2BoardCells;
    private JTextArea explanationTextArea;
    
    private JPanel startGamePanel;
    private JButton startGameButton;
    private JButton coinFlipButton; // New button for coin flip

    private String[][] player1GameBoard;
    private String[][] player1GameBoardState;
    private String[][] player2GameBoard;
    private String[][] player2GameBoardState;
    private JLabel turnLabel;

    private boolean isPlayer1Turn = true;

    public BattleshipShootingPhase(int numRows, int numCols, String[][] player2GameBoardState, String[][] player1GameBoardState) {
        this.numRows = numRows;
        this.numCols = numCols;
        currentPlayer = 0; // 0 for not determined yet
        setTitle("Battleship - Shooting Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800, 1200);

        this.player2GameBoardState = player2GameBoardState; // Initialize player 2's game board state
        this.player1GameBoardState = player1GameBoardState;

        initializeStartGamePanel(numRows, numCols);

        add(startGamePanel);
        setVisible(true);

    }

    private void initializeStartGamePanel(int numRows, int numCols) {
    	  // Initialize game board panel and cells
        startGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Call DrawGrid to draw the grid lines
                DrawGrid.drawGridLines(g, numRows, numCols, 50, 50, 50);
                DrawGrid.drawGridLines(g, numRows, numCols, 50, 900, 50);
            }
        };
        startGamePanel.setLayout(null); // Use absolute layout

        explanationTextArea = new JTextArea();
        explanationTextArea.setBounds(50, numRows * 50 + 100, 800, 100); // Adjust position and size as needed
        explanationTextArea.setEditable(false); // Make it read-only
        explanationTextArea.setLineWrap(true); // Enable line wrapping
        explanationTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        explanationTextArea.setFont(explanationTextArea.getFont().deriveFont(Font.BOLD, 14));
        explanationTextArea.setText("Welcome to the shooting phase of Battleship. To start off, player 1 must select Coinflip and choose heads or tails. If player 1 guesses correctly, he will shoot first. Incorrect, and he will shoot second. Once that is done, you can select a cell on your board to 'Shoot at'. If it is a hit, it will be marked with an 'H' and red. If it is a miss, it will be marked with an 'M' and blue. This will continue until all pieces of the ships have sunk. Enjoy!"); // Initial text
        startGamePanel.add(explanationTextArea);
        
        
        
        
        
        JLabel WhatPlayer = new JLabel("Player 1's Shooting Board");
        int labelWidth = 300; // Adjust as needed
        int labelHeight = 30; // Adjust as needed
        int labelX = (numCols * 50 ) / 2; // Center horizontally
        int labelY = numRows * 50 + 60; // Position slightly below the P1Board
        WhatPlayer.setBounds(labelX, labelY, labelWidth, labelHeight);
        startGamePanel.add(WhatPlayer);
        
        
        // Create buttons for game board cells and place them manually
        int cellSize = 50; // Adjust size as needed
        int startX = 50;
        int startY = 50;

     // Add column labels (letters)
        for (int col = 0; col < numCols; col++) {
            JLabel columnLabel = new JLabel(String.valueOf((char)('A' + col)));
            columnLabel.setBounds(startX + col * cellSize + cellSize / 2, startY - 30, 20, 20);
            startGamePanel.add(columnLabel);
        }

        // Add row labels (numbers)
        for (int row = 0; row < numRows; row++) {
            JLabel rowLabel = new JLabel(String.valueOf(row + 1));
            rowLabel.setBounds(startX - 30, startY + row * cellSize + cellSize / 2, 20, 20);
            startGamePanel.add(rowLabel);
        }


        P1Board = new JButton[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JButton cellButton = new JButton();
                cellButton.setBounds(startX + col * cellSize, startY + row * cellSize, cellSize, cellSize);
                cellButton.setBackground(lightBlue);
                cellButton.setText("〰");
                final int currentRow = row;
                final int currentCol = col;
                cellButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (currentPlayer == 1) {
                            shootCell(cellButton, currentRow, currentCol, player2GameBoardState);
                        }
                    }
                });
                P1Board[row][col] = cellButton;
                startGamePanel.add(cellButton);
            }
        }
        
        
        // Create buttons for game board cells and place them manually
        int cellSize2 = 50; // Adjust size as needed
        int startX2 = 900;
        int startY2 = 50;

        JLabel P2 = new JLabel("Player 2's Shooting Board");
        int labelWidth2 = 300; // Adjust as needed
        int labelHeight2 = 30; // Adjust as needed
        int labelX2 = ((numCols * 50) / 2) + 850; // Center horizontally
        int labelY2 = numRows * 50 + 60; // Position slightly below the P1Board
        P2.setBounds(labelX2, labelY2, labelWidth2, labelHeight2);
        startGamePanel.add(P2);
        
     // Add column labels (letters)
        for (int col = 0; col < numCols; col++) {
            JLabel columnLabel2 = new JLabel(String.valueOf((char)('A' + col)));
            columnLabel2.setBounds(startX2 + col * cellSize2 + cellSize2 / 2, startY2 - 30, 20, 20);
            startGamePanel.add(columnLabel2);
        }

        // Add row labels (numbers)
        for (int row = 0; row < numRows; row++) {
            JLabel rowLabel2 = new JLabel(String.valueOf(row + 1));
            rowLabel2.setBounds(startX2 - 30, startY2 + row * cellSize2 + cellSize2 / 2, 20, 20);
            startGamePanel.add(rowLabel2);
        }


        P2Board = new JButton[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JButton cellButton = new JButton();
                cellButton.setBounds(startX2 + col * cellSize2, startY2 + row * cellSize2, cellSize2, cellSize2);
                cellButton.setBackground(lightBlue);
                cellButton.setText("〰");
                
                final int currentRow2 = row;
                final int currentCol2 = col;
                cellButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (currentPlayer == 2) {
                            shootCell(cellButton, currentRow2, currentCol2, player1GameBoardState);
                        }
                    }
                });
                
                P2Board[row][col] = cellButton;
                startGamePanel.add(cellButton);
            }
        }
        // Add Coinflip button
        coinFlipButton = new JButton("Coinflip");
        coinFlipButton.setBounds(50, numRows * 50 + 250, 100, 30); // Adjust position and size as needed
        coinFlipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coinFlipButtonClicked();
            }
        });
        startGamePanel.add(coinFlipButton);
    }
    private void coinFlipButtonClicked() {
        Random random = new Random();
        int result = random.nextInt(2); // Generate a random number (0 or 1)

        // Determine currentPlayer based on the result
        if (result == 0) {
            currentPlayer = 1; // Heads
            JOptionPane.showMessageDialog(null, "You got heads. Player 1 goes first.");
        } else {
            currentPlayer = 2; // Tails
            JOptionPane.showMessageDialog(null, "You got tails. Player 2 goes first.");
        }
    }
    private void shootCell(JButton cellButton, int row, int col, String[][] targetGameBoardState) {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to shoot here?", "Confirm Shot", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Update cell color based on hit or miss
            if (targetGameBoardState[row][col].equals("O")) { // Hit
                cellButton.setBackground(Color.RED);
                cellButton.setFont(cellButton.getFont().deriveFont(Font.BOLD, 20));
                cellButton.setText("H");
                targetGameBoardState[row][col] = "H"; // Mark the cell as hit
            } else { // Miss
                cellButton.setBackground(Color.BLUE); // Miss
                cellButton.setFont(cellButton.getFont().deriveFont(Font.BOLD, 18));
                cellButton.setText("M");
                targetGameBoardState[row][col] = "M"; // Mark the cell as missed
            }
            // Check game state
            checkGameState();
            // Switch player turn
            currentPlayer = (currentPlayer == 1) ? 2 : 1;
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "'s turn");
        }
    }
    private void checkGameState() {
        // Check if all ships of any player have been sunk
        boolean allShipsSunkP1 = allShipsSunk(player2GameBoardState);
        boolean allShipsSunkP2 = allShipsSunk(player1GameBoardState);

        if (allShipsSunkP1) {
            JOptionPane.showMessageDialog(null, "Player 1 Has Won!!!");
            disableAllButtons();
        } else if (allShipsSunkP2) {
            JOptionPane.showMessageDialog(null, "Player 2 Has Won!!!");
            disableAllButtons();
        }
    }
    private boolean allShipsSunk(String[][] gameBoardState) {
        // Check if all ships have been sunk
        for (String[] row : gameBoardState) {
            for (String cell : row) {
                if (cell.equals("O")) {
                    return false; // At least one ship is not sunk
                }
            }
        }
        return true; // All ships are sunk
    }
    private void disableAllButtons() {
        // Disable all buttons on the game boards
        for (JButton[] row : P1Board) {
            for (JButton button : row) {
                button.setEnabled(false);
            }
        }
        for (JButton[] row : P2Board) {
            for (JButton button : row) {
                button.setEnabled(false);
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
