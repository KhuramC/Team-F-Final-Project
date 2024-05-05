package battleshipMenu.view;

import javax.swing.*;

import battleshipMenu.model.BattleshipGameModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
/**
 * Represents the GUI for the shooting phase of the Battleship game.
 * Extends JFrame to create the game window.
 */
public class BattleshipShootingPhase extends JFrame {

	private BattleshipGameModel battleshipGameModel;
	
    private int currentPlayer; // 1 for Player 1, 2 for Player 2
    private int numRows;
    private int numCols;
    private JButton[][] P1Board;
    private JButton[][] P2Board;
    Color lightBlue = new Color(173, 216, 230);
    Color hitColor = Color.RED;
    Color missColor = Color.BLUE;
   
    private int initialTurnTimeInSeconds;
    private Timer turnTimer;
    private int remainingTimeInSeconds;
    
    private JLabel timerLabel;
    private JButton startGameButton;
    private JButton coinFlipButton;
    private JTextArea explanationTextArea;
    
    private JPanel startGamePanel;

    private Color Player1ShipColor;
    private Color Player2ShipColor;
   
    private String shootingTimer;
    private JLabel turnLabel;

    private boolean isPlayer1Turn = true;

    /**
     * Constructor for the BattleshipShootingPhase class.
     * Initializes the game window and components.
     * 
     * @param numRows              Number of rows in the game board.
     * @param numCols              Number of columns in the game board.
     * @param player2GameBoardState Initial state of player 2's game board.
     * @param player1GameBoardState Initial state of player 1's game board.
     * @param P1ShipColor          Color of Player 1's ships.
     * @param P2ShipColor          Color of Player 2's ships.
     * @param shootingTimer        Timer setting for shooting phase.
     * @param battleshipGameModel  Model containing game data and logic.
     */
    public BattleshipShootingPhase(int numRows, int numCols, String[][] player2GameBoardState, String[][] player1GameBoardState, Color P1ShipColor, Color P2ShipColor, String shootingTimer, BattleshipGameModel battleshipGameModel) {
        this.numRows = numRows;
        this.numCols = numCols;
        currentPlayer = 0; // 0 for not determined yet
        setTitle("Battleship - Shooting Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1800, 1200);

        this.battleshipGameModel = battleshipGameModel;
        this.shootingTimer = shootingTimer;
        this.Player1ShipColor = P1ShipColor;
        this.Player2ShipColor = P2ShipColor;
        
        initializeStartGamePanel(numRows, numCols);

        add(startGamePanel);
        setVisible(true);

    }
    /**
     * Initializes the start game panel by calling various helper methods to set up
     * the layout, explanation text area, timer label, player labels, player boards,
     * and the coinflip button.
     * 
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     */
    private void initializeStartGamePanel(int numRows, int numCols) {
        initializePanelLayout(numRows, numCols);
        initializeExplanationTextArea(numRows);
        initializeTimerLabel(numRows);
        initializePlayerLabels(numRows, numCols);
        initializePlayerBoards(numRows, numCols);
        initializeCoinFlipButton();
    }
    /**
     * Initializes the layout of the start game panel with a custom paint component
     * that draws grid lines.
     * 
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     */
    private void initializePanelLayout(int numRows, int numCols) {
        startGamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                DrawGrid.drawGridLines(g, numRows, numCols, 50, 50, 50);
                DrawGrid.drawGridLines(g, numRows, numCols, 50, 900, 50);
            }
        };
        startGamePanel.setLayout(null);
    }
    /**
     * Initializes the explanation text area with the provided text and adds it to the start game panel.
     * 
     * @param numRows The number of rows in the game board.
     */
    private void initializeExplanationTextArea(int numRows) {
    	P1Board = new JButton[numRows][numCols];
    	P2Board = new JButton[numRows][numCols];
    	
    	explanationTextArea = new JTextArea();
        explanationTextArea.setBounds(50, numRows * 50 + 100, 800, 100);
        explanationTextArea.setEditable(false);
        explanationTextArea.setLineWrap(true);
        explanationTextArea.setWrapStyleWord(true);
        explanationTextArea.setFont(explanationTextArea.getFont().deriveFont(Font.BOLD, 14));
        explanationTextArea.setText("Welcome to the shooting phase of Battleship. To start off, player 1 must select Coinflip and choose heads or tails. If player 1 guesses correctly, he will shoot first. Incorrect, and he will shoot second. Once that is done, you can select a cell on your board to 'Shoot at'. If it is a hit, it will be marked with an 'H' and red. If it is a miss, it will be marked with an 'M' and blue. This will continue until all pieces of the ships have sunk. Enjoy!");
        startGamePanel.add(explanationTextArea);
    }
    /**
     * Initializes the timer label and adds it to the start game panel.
     * 
     * @param numRows The number of rows in the game board.
     */
    private void initializeTimerLabel(int numRows) {
        timerLabel = new JLabel("Remaining Time: ");
        timerLabel.setBounds(150, numRows * 50 + 200, 200, 30);
        startGamePanel.add(timerLabel);
    }
    /**
     * Initializes the labels for player 1 and player 2 and adds them to the start game panel.
     * 
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     */
    private void initializePlayerLabels(int numRows, int numCols) {
        // Player 1 label
        JLabel player1Label = new JLabel("Player 1's Shooting Board");
        int labelWidth = 300;
        int labelHeight = 30;
        int labelX = (numCols * 50 ) / 2;
        int labelY = numRows * 50 + 60;
        player1Label.setBounds(labelX, labelY, labelWidth, labelHeight);
        startGamePanel.add(player1Label);

        // Player 2 label
        JLabel player2Label = new JLabel("Player 2's Shooting Board");
        int labelWidth2 = 300;
        int labelHeight2 = 30;
        int labelX2 = ((numCols * 50) / 2) + 850;
        int labelY2 = numRows * 50 + 60;
        player2Label.setBounds(labelX2, labelY2, labelWidth2, labelHeight2);
        startGamePanel.add(player2Label);
    }
    /**
     * Initializes the player boards for player 1 and player 2 and adds them to the start game panel.
     * 
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     */
    private void initializePlayerBoards(int numRows, int numCols) {
        initializePlayerBoard(P1Board, 50, 50, numRows, numCols, battleshipGameModel.getPlayer2GameBoardState(), 1);
        initializePlayerBoard(P2Board, 900, 50, numRows, numCols, battleshipGameModel.getPlayer1GameBoardState(), 2);
    }
    /**
     * Initializes the player board for a specific player and adds it to the start game panel.
     * 
     * @param board The player board to initialize.
     * @param startX The x-coordinate of the starting position of the board.
     * @param startY The y-coordinate of the starting position of the board.
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     * @param targetGameBoardState The game board state to associate with the player board.
     * @param player The player number associated with the board.
     */
    private void initializePlayerBoard(JButton[][] board, int startX, int startY, int numRows, int numCols, String[][] targetGameBoardState, int player) {
        int cellSize = 50;
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
                        if (currentPlayer == player) {
                            shootCell(cellButton, currentRow, currentCol, targetGameBoardState);
                        }
                    }
                });
                board[row][col] = cellButton;
                startGamePanel.add(cellButton);
            }
        }
    }
    /**
     * Initializes the coinflip button and adds it to the start game panel.
     */
    private void initializeCoinFlipButton() {
        coinFlipButton = new JButton("Coinflip");
        coinFlipButton.setBounds(50, numRows * 50 + 250, 100, 30);
        coinFlipButton.addActionListener(e -> coinFlipButtonClicked());
        startGamePanel.add(coinFlipButton);
    }
    /**
     * Handles the event when the coin flip button is clicked.
     * Flips a coin to determine the starting player, displays the result, sets the initial turn time based on the shooting timer,
     * and starts the turn timer.
     */
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
     // Set initial turn time based on shootingTimer
        if (shootingTimer.equals("No Timer")) {
            initialTurnTimeInSeconds = 0; // No timer
        } else if (shootingTimer.equals("30 sec")) {
            initialTurnTimeInSeconds = 30; // 30 seconds
        } else if (shootingTimer.equals("1 min")) {
            initialTurnTimeInSeconds = 60; // 1 minute
        }

        // Start the turn timer
        startTurnTimer(initialTurnTimeInSeconds);
    }
    /**
     * Handles shooting a cell on the game board and updates the game state accordingly.
     * @param cellButton The button representing the cell to be shot.
     * @param row The row index of the cell to be shot.
     * @param col The column index of the cell to be shot.
     * @param targetGameBoardState The 2D array representing the target player's game board state.
     */
    private void shootCell(JButton cellButton, int row, int col, String[][] targetGameBoardState) {
        if (!targetGameBoardState[row][col].equals("H") && !targetGameBoardState[row][col].equals("M")) {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to shoot here?", "Confirm Shot", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                // Update cell color based on hit or miss
                if (targetGameBoardState[row][col].equals("O")) { // Hit
                    cellButton.setBackground(Color.RED);
                    cellButton.setFont(cellButton.getFont().deriveFont(Font.BOLD, 18));
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
                restartTurnTimer();
            }
        } else {
            JOptionPane.showMessageDialog(null, "You can't shoot at this cell. Please select another one.");
        }
    }
    /**
     * Checks the game state to determine if any player has won.
     */
    private void checkGameState() {
        // Check if all ships of any player have been sunk
        boolean allShipsSunkP1 = allShipsSunk(battleshipGameModel.getPlayer2GameBoardState());
        boolean allShipsSunkP2 = allShipsSunk(battleshipGameModel.getPlayer1GameBoardState());

        if (allShipsSunkP1) {
            JOptionPane.showMessageDialog(null, "Player 1 Has Won!!!");
            disableAllButtons();
        } else if (allShipsSunkP2) {
            JOptionPane.showMessageDialog(null, "Player 2 Has Won!!!");
            disableAllButtons();
        }
    }
    /**
     * Checks if all ships on the game board have been sunk.
     * @param gameBoardState The 2D array representing the game board state.
     * @return true if all ships are sunk, false otherwise.
     */
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
    /**
     * Disables all buttons on the game boards after end game and reveals non-hit ship locations for the loser.
     */
    private void disableAllButtons() {
    	
    	// Determine which player has lost
        boolean player1Lost = allShipsSunk(battleshipGameModel.getPlayer2GameBoardState());
        boolean player2Lost = allShipsSunk(battleshipGameModel.getPlayer1GameBoardState());

        // Disable all buttons on the game boards and reveal non-hit ship locations
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                // Disable buttons for player 1's board
                JButton buttonP1 = P2Board[row][col];
                buttonP1.setEnabled(false);
                if (player1Lost && battleshipGameModel.getPlayer1GameBoardState()[row][col].equals("O")) {
                    buttonP1.setBackground(Player1ShipColor); // Change background color to show ship position
                }

                // Disable buttons for player 2's board
                JButton buttonP2 = P1Board[row][col];
                buttonP2.setEnabled(false);
                if (player2Lost && battleshipGameModel.getPlayer2GameBoardState()[row][col].equals("O")) {
                    buttonP2.setBackground(Player2ShipColor); // Change background color to show ship position
                }
            }
        }
    }
    /**
     * Starts the turn timer with the specified duration in seconds.
     * @param seconds The duration of the turn timer in seconds.
     */
    private void startTurnTimer(int seconds) {
        remainingTimeInSeconds = seconds;
        if (remainingTimeInSeconds > 0) {
            turnTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remainingTimeInSeconds--;
                    timerLabel.setText("Remaining Time: " + remainingTimeInSeconds + " sec");
                    if (remainingTimeInSeconds <= 0) {
                        endTurn();
                    }
                }
            });
            turnTimer.start();
        } else {
        }
    }
    /**
     * Restarts the turn timer with the initial turn time.
     */
    private void restartTurnTimer() {
        stopTurnTimer();
        startTurnTimer(initialTurnTimeInSeconds);
    }
    /**
     * Stops the turn timer.
     */
    private void stopTurnTimer() {
        if (turnTimer != null && turnTimer.isRunning()) {
            turnTimer.stop();
        }
    }
    /**
     * Ends the current player's turn and switches to the next player.
     * Restarts the timer for the next player if applicable.
     */
    private void endTurn() {

        // Switch player turn
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "'s turn");

        // Restart timer for the next player
        if (shootingTimer.equals("30 sec")) {
            startTurnTimer(30);
        } else if (shootingTimer.equals("1 min")) {
            startTurnTimer(60);
        } else {
            // No timer option, do nothing
        }
    }
}
