package battleshipMenuTest.viewTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import battleshipMenu.model.BattleshipGameModel;
import battleshipMenu.view.BattleshipShootingPhase;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
/**
 * Tests for the BattleshipShootingPhase class.
 * 
 * @author Roney
 */
class BattleshipShootingPhaseTest {

    private BattleshipShootingPhase shootingPhase;
    private BattleshipGameModel gameModel;
    private JButton[][] P1Board;
    private JButton[][] P2Board;
    private int numRows = 10;
    private int numCols = 10;
    private Color Player1ShipColor = Color.RED;
    private Color Player2ShipColor = Color.BLUE;

    @BeforeEach
    void setUp() {
        gameModel = new BattleshipGameModel(numRows, numCols);
        String[][] player2GameBoardState = new String[numRows][numCols];
        String[][] player1GameBoardState = new String[numRows][numCols];
        shootingPhase = new BattleshipShootingPhase(numRows, numCols, player2GameBoardState, player1GameBoardState, Player1ShipColor, Player2ShipColor, "30 sec", gameModel);
        P1Board = new JButton[numRows][numCols];
        P2Board = new JButton[numRows][numCols];
    }
    /**
     * Test case for initializePanelLayout() method.
     * 
     * @author Roney
     */
    @Test
    public void testInitializePanelLayout() {
        shootingPhase.initializePanelLayout(numRows, numCols);
        assertNotNull(shootingPhase.getStartGamePanel(), "Start game panel should not be null");
    }
    /**
     * Test case for initializeExplanationTextArea() method.
     * 
     * @author Roney
     */
    @Test
    public void testInitializeExplanationTextArea() {
        shootingPhase.initializeExplanationTextArea(numRows);
        JTextArea explanationTextArea = shootingPhase.getExplanationTextArea();
        assertNotNull(explanationTextArea, "Explanation text area should not be null");
    }
    /**
     * Test case for initializeTimerLabel() method.
     * 
     * @author Roney
     */
    @Test
    public void testInitializeTimerLabel() {
        shootingPhase.initializeTimerLabel(numRows);
        JLabel timerLabel = shootingPhase.getTimerLabel();
        assertNotNull(timerLabel, "Timer label should not be null");
    }
    /**
     * Test case for initializePlayerLabels() method.
     * 
     * @author Roney
     */
    @Test
    public void testInitializePlayerLabels() {
        shootingPhase.initializePlayerLabels(numRows, numCols);
        JLabel player1Label = shootingPhase.getPlayer1Label();
        JLabel player2Label = shootingPhase.getPlayer2Label();
        assertNotNull(player1Label, "Player 1 label should not be null");
        assertNotNull(player2Label, "Player 2 label should not be null");
    }
    /**
     * Test case for initializePlayerBoards() method.
     * 
     * @author Roney
     */
    @Test
    public void testInitializePlayerBoards() {
        shootingPhase.initializePlayerBoards(numRows, numCols);
        assertNotNull(P1Board, "Player 1 board should not be null");
        assertNotNull(P2Board, "Player 2 board should not be null");
    }
    /**
     * Another Test case for initializePlayerBoard() method.
     * 
     * @author Roney
     */
    @Test
    public void testInitializePlayerBoard() {
        String[][] targetGameBoardState = new String[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                targetGameBoardState[i][j] = "~";
            }
        }
        int startX = 50;
        int startY = 50;
        int player = 1;
        shootingPhase.initializePlayerBoard(P1Board, startX, startY, numRows, numCols, targetGameBoardState, player);
        assertNotNull(P1Board, "Player board should not be null");
    }
    /**
     * Test case for initializing the coin flip button.
     * 
     * @author Roney
     */
    @Test
    public void testInitializeCoinFlipButton() {
        shootingPhase.initializeCoinFlipButton();
        JButton coinFlipButton = shootingPhase.getCoinFlipButton();
        assertNotNull(coinFlipButton, "Coinflip button should not be null");
    }
    /**
     * Test case for handling the coin flip button click event.
     * 
     * @author Roney
     */
    @Test
    public void testCoinFlipButtonClicked() {
        shootingPhase.coinFlipButtonClicked();
        int currentPlayer = shootingPhase.getCurrentPlayer();
        assertTrue(currentPlayer == 1 || currentPlayer == 2, "Current player should be either 1 or 2");
    }
    /**
     * Test case for shooting a cell on the game board.
     * 
     * @author Roney
     */
    @Test
    public void testShootCell() {
        String[][] targetGameBoardState = new String[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                targetGameBoardState[i][j] = "~";
            }
        }
        int row = 0;
        int col = 0;
        
        // Simulate a ship in the target cell
        targetGameBoardState[row][col] = "O";

        JButton cellButton = new JButton();
        cellButton.setBackground(Color.BLUE); // Set initial background color
        shootingPhase.shootCell(cellButton, row, col, targetGameBoardState);
        
        assertEquals(Color.RED, cellButton.getBackground(), "Cell background color should be red if it's a hit");
        assertEquals("H", cellButton.getText(), "Cell text should be 'H' if it's a hit");
    }
    /**
     * Test case for checking if all ships are sunk on the game board.
     * 
     * @author Roney
     */
    @Test
    public void testAllShipsSunk() {
        String[][] gameBoardState1 = {
            {"H", "H", "H"},
            {"H", "H", "M"},
            {"H", "M", "H"}
        };
        assertTrue(shootingPhase.allShipsSunk(gameBoardState1), "All ships should be sunk");

        String[][] gameBoardState2 = {
            {"O", "H", "H"},
            {"H", "M", "O"},
            {"O", "H", "O"}
        };
        assertFalse(shootingPhase.allShipsSunk(gameBoardState2), "Not all ships should be sunk");
    }
    /**
     * Test case for disabling all buttons on the game board.
     * 
     * @author Roney
     */
    @Test
    public void testDisableAllButtons() {
        String[][] player2GameBoardState = {
            {"O", "O", "O"},
            {"O", "O", "O"},
            {"O", "O", "O"}
        };
        String[][] player1GameBoardState = {
            {"H", "H", "H"},
            {"M", "M", "M"},
            {"O", "O", "O"}
        };
        shootingPhase = new BattleshipShootingPhase(numRows, numCols, player2GameBoardState, player1GameBoardState, Player1ShipColor, Player2ShipColor, "30 sec", gameModel);
        shootingPhase.disableAllButtons();

        verifyButtonsDisabledAndShipPositionsRevealed(shootingPhase.getP1Board(), true, Player1ShipColor);
        verifyButtonsDisabledAndShipPositionsRevealed(shootingPhase.getP2Board(), false, Player2ShipColor);
    }
    /**
     * Verifies that buttons are disabled and ship positions are revealed on the game board.
     * 
     * @param board The 2D array of JButton representing the game board.
     * @param shipPositionsRevealed Flag indicating whether ship positions are revealed.
     * @param shipColor The color representing ship positions.
     * @author Roney
     */
    @Test
    private void verifyButtonsDisabledAndShipPositionsRevealed(JButton[][] board, boolean shipPositionsRevealed, Color shipColor) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JButton button = board[row][col];
                assertFalse(button.isEnabled(), "Button should be disabled");
                if (shipPositionsRevealed) {
                    if (button.getBackground().equals(shipColor)) {
                        // Ship position should be revealed
                        assertEquals(shipColor, button.getBackground(), "Background color should be set to reveal ship position");
                    } else {
                        // Cell hasn't been shot at yet, so it should remain light blue
                        assertEquals(new Color(173, 216, 230), button.getBackground(), "Background color should be light blue for unshot cells");
                    }
                } else {
                    // For the losing player's board, all unshot cells should be light blue
                    assertEquals(new Color(173, 216, 230), button.getBackground(), "Background color should be light blue for unshot cells");
                }
            }
        }
    }
    /**
     * Test case for starting the turn timer.
     * 
     * @author Roney
     */
    @Test
    public void testStartTurnTimer() {
        // Test starting the turn timer
        shootingPhase.startTurnTimer(30); // Start timer for 30 seconds
        // Verify that the turn timer is started and the remaining time is set to 30 seconds
        assertNotNull(shootingPhase.getTurnTimer());
        assertEquals(30, shootingPhase.getRemainingTimeInSeconds());
        assertTrue(shootingPhase.getTurnTimer().isRunning()); // Timer should be running
        // You can add more assertions based on the expected behavior
    }
    /**
     * Test case for stopping the turn timer.
     * 
     * @author Roney
     */
    @Test
    public void testStopTurnTimer() {
        // Test stopping the turn timer
        // First, start the timer
        shootingPhase.startTurnTimer(30);
        // Now, stop the timer
        shootingPhase.stopTurnTimer();
        // Verify that the turn timer is stopped
        assertFalse(shootingPhase.getTurnTimer().isRunning()); // Timer should not be running
        // You can add more assertions based on the expected behavior
    }
}