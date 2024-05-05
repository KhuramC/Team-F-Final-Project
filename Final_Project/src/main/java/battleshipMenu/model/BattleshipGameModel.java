package battleshipMenu.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * The BattleshipGameModel class represents the model component of the Battleship game.
 * It manages the game board states, ship placement, and validation.
 * 
 * @author Roney
 */
public class BattleshipGameModel {
    private String[][] player2GameBoardState;
    private String[][] player1GameBoardState;
    private boolean player2GameBoardStateSaved = false;
    private boolean player1GameBoardStateSaved = false;
    private int shipsPlacedCount = 0;
    private int shipsPlacedCountP2 = 0;
    private int remainingTimeInSeconds;
    private String shootingTimer; // Declare shootingTimer as an instance variable
    private int currentPlayer; // Declare currentPlayer as an instance variable
    private int initialTurnTimeInSeconds;
    /**
     * Constructor for the BattleshipGameModel class.
     *
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     * 
     * @author Roney
     */
    public BattleshipGameModel(int numRows, int numCols) {
        player1GameBoardState = new String[numRows][numCols];
        // Initialize player 1's game board state
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                player1GameBoardState[row][col] = "~";
            }
        }
        player2GameBoardState = new String[numRows][numCols];
        // Initialize player 2's game board state
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                player2GameBoardState[row][col] = "~";
            }
        }
    }

    /**
     * Update player 1's game board state when a ship is placed.
     *
     * @param placedShips The set of points representing the locations of the placed ships.
     * 
     * @author Roney
     */
    public void updatePlayer1GameBoardState(Set<Point> placedShips) {
        for (Point shipLocation : placedShips) {
            int row = shipLocation.x;
            int col = shipLocation.y;
            player1GameBoardState[row][col] = "O";
        }
    }

    /**
     * Save "Player 1's Game Board State" if it has not been saved already.
     * 
     * @author Roney
     */
    public void savePlayer1GameBoardState() {
        if (!isPlayer1GameBoardStateSaved()) {
            // Save the game board state
            System.out.println("Player 1's Game Board State Saved:");
            printPlayer1GameBoardState();
            player1GameBoardStateSaved = true;
        } else {
            System.out.println("Player 1's Game Board State has already been saved.");
        }
    }

    /**
     * Check if "Player 1's Game Board State" has already been saved.
     *
     * @return true if "Player 1's Game Board State" has been saved, false otherwise.
     * 
     * @author Roney
     */
    public boolean isPlayer1GameBoardStateSaved() {
        return player1GameBoardStateSaved;
    }

    /**
     * Print player 1's game board state to the console.
     * 
     * @author Roney
     */
    public void printPlayer1GameBoardState() {
        for (String[] row : player1GameBoardState) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Update player 2's game board state when a ship is placed.
     *
     * @param placedShips The set of points representing the locations of the placed ships.
     * 
     * @author Roney
     */
    public void updatePlayer2GameBoardState(Set<Point> placedShips) {
        for (Point shipLocation : placedShips) {
            int row = shipLocation.x;
            int col = shipLocation.y;
            player2GameBoardState[row][col] = "O";
        }
    }

    /**
     * Save "Player 2's Game Board State" if it has not been saved already.
     * 
     * @author Roney
     */
    public void savePlayer2GameBoardState() {
        if (!isPlayer2GameBoardStateSaved()) {
            // Save the game board state
            System.out.println("Player 2's Game Board State Saved:");
            printPlayer2GameBoardState();
            player2GameBoardStateSaved = true;
        } else {
            System.out.println("Player 2's Game Board State has already been saved.");
        }
    }

    /**
     * Check if "Player 2's Game Board State" has already been saved.
     *
     * @return true if "Player 2's Game Board State" has been saved, false otherwise.
     * 
     * @author Roney
     */
    public boolean isPlayer2GameBoardStateSaved() {
        return player2GameBoardStateSaved;
    }

    /**
     * Print player 2's game board state to the console.
     * 
     * @author Roney
     */
    public void printPlayer2GameBoardState() {
        for (String[] row : player2GameBoardState) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Update the count of ships placed for player 1.
     * 
     * @author Roney
     */
    public void updateShipsPlacedCount() {
        shipsPlacedCount++;
    }

    /**
     * Get the count of ships placed for player 1.
     *
     * @return The count of ships placed for player 1.
     * 
     * @author Roney
     */
    public int getShipsPlacedCount() {
        return shipsPlacedCount;
    }

    /**
     * Update the count of ships placed for player 2.
     * 
     * @author Roney
     */
    public void updateShipsPlacedCountP2() {
        shipsPlacedCountP2++;
    }

    /**
     * Get the count of ships placed for player 2.
     *
     * @return The count of ships placed for player 2.
     * 
     * @author Roney
     */
    public int getShipsPlacedCountP2() {
        return shipsPlacedCountP2;
    }

    /**
     * Validate ship placement on player 1's game board.
     *
     * @param startRow   The starting row index of the placement.
     * @param startCol   The starting column index of the placement.
     * @param shipSize   The size of the ship being placed.
     * @param isVertical Flag indicating whether the ship is being placed vertically or horizontally.
     * @param numRows    The number of rows on the game board.
     * @param numCols    The number of columns on the game board.
     * @return true if the placement is valid, false otherwise.
     * 
     * @author Roney
     */
    public boolean isValidPlacement(int startRow, int startCol, int shipSize, boolean isVertical, int numRows, int numCols) {
        // Debugging print statements
        System.out.println("startRow: " + startRow + ", startCol: " + startCol + ", shipSize: " + shipSize + ", isVertical: " + isVertical);
        System.out.println("numRows: " + numRows + ", numCols: " + numCols);

        // Check if the placement is within the bounds of the game board
        if (startRow < 0 || startCol < 0 || startRow >= numRows || startCol >= numCols) {
            JOptionPane.showMessageDialog(null, "Invalid Placement", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Placement is off the board");
            return false; // Placement is off the board
        }

        // Check if the placement overlaps with any existing ships or is out of bounds
        for (int i = 0; i < shipSize; i++) {
            int row = startRow + (isVertical ? i : 0);
            int col = startCol + (isVertical ? 0 : i);
            if (row >= numRows || col >= numCols || !player1GameBoardState[row][col].equals("~")) {
                JOptionPane.showMessageDialog(null, "Invalid Placement", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Invalid Placement!");
                return false; // Placement overlaps with an existing ship or is off the board
            }
        }
        return true;
    }

    /**
     * Validate ship placement on player 2's game board.
     *
     * @param startRow   The starting row index of the placement.
     * @param startCol   The starting column index of the placement.
     * @param shipSize   The size of the ship being placed.
     * @param isVertical Flag indicating whether the ship is being placed vertically or horizontally.
     * @param numRows    The number of rows on the game board.
     * @param numCols    The number of columns on the game board.
     * @return true if the placement is valid, false otherwise.
     * 
     * @author Roney
     */
    public boolean isValidPlacementP2(int startRow, int startCol, int shipSize, boolean isVertical, int numRows, int numCols) {
        // Debugging print statements
        System.out.println("startRow: " + startRow + ", startCol: " + startCol + ", shipSize: " + shipSize + ", isVertical: " + isVertical);
        System.out.println("numRows: " + numRows + ", numCols: " + numCols);

        // Check if the placement is within the bounds of the game board
        if (startRow < 0 || startCol < 0 || startRow >= numRows || startCol >= numCols) {
            JOptionPane.showMessageDialog(null, "Invalid Placement", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Placement is off the board");
            return false; // Placement is off the board
        }

        // Check if the placement overlaps with any existing ships or is out of bounds
        for (int i = 0; i < shipSize; i++) {
            int row = startRow + (isVertical ? i : 0);
            int col = startCol + (isVertical ? 0 : i);
            if (row >= numRows || col >= numCols || !player2GameBoardState[row][col].equals("~")) {
                JOptionPane.showMessageDialog(null, "Invalid Placement", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Invalid Placement!");
                return false; // Placement overlaps with an existing ship or is off the board
            }
        }
        return true;
    }

    /**
     * Map color names to Color objects.
     *
     * @param colorName The name of the color.
     * @return The corresponding Color object.
     * 
     * @author Roney
     */
    public Color mapColor(String colorName) {
        switch (colorName) {
            case "Green":
                return Color.GREEN;
            case "Yellow":
                return Color.YELLOW;
            case "Purple":
                return new Color(128, 0, 128);
            case "Orange":
                return Color.ORANGE;
            case "Gray":
                return Color.GRAY;
            default:
                return Color.WHITE;
        }
    }

    /**
     * Get player 1's game board state.
     *
     * @return The 2D array representing player 1's game board state.
     * 
     * @author Roney
     */
    public String[][] getPlayer1GameBoardState() {
        return player1GameBoardState;
    }

    /**
     * Get player 2's game board state.
     *
     * @return The 2D array representing player 2's game board state.
     * 
     * @author Roney
     */
    public String[][] getPlayer2GameBoardState() {
        return player2GameBoardState;
    }

}