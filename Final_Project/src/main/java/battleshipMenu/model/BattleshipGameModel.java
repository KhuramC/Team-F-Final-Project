package battleshipMenu.model;

import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

public class BattleshipGameModel {
	private String[][] player2GameBoardState;
    private String[][] player1GameBoardState;
    private boolean player2GameBoardStateSaved = false;
    private boolean player1GameBoardStateSaved = false;
    private int shipsPlacedCount = 0;
    private int shipsPlacedCountP2 = 0;
    // Constructor
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

    // Update player 1's game board state when a ship is placed
    public void updatePlayer1GameBoardState(Set<Point> placedShips) {
        for (Point shipLocation : placedShips) {
            int row = shipLocation.x;
            int col = shipLocation.y;
            player1GameBoardState[row][col] = "O";
        }
    }
    // Method to save "Player 1's Game Board State"
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

    // Method to check if "Player 1's Game Board State" has already been saved
    public boolean isPlayer1GameBoardStateSaved() {
        return player1GameBoardStateSaved;
    }

    // Method to print player 1's game board state
    public void printPlayer1GameBoardState() {
        for (String[] row : player1GameBoardState) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    // Update player 1's game board state when a ship is placed
    public void updatePlayer2GameBoardState(Set<Point> placedShips) {
        for (Point shipLocation : placedShips) {
            int row = shipLocation.x;
            int col = shipLocation.y;
            player2GameBoardState[row][col] = "O";
        }
    }
    // Method to save "Player 1's Game Board State"
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

    // Method to check if "Player 1's Game Board State" has already been saved
    public boolean isPlayer2GameBoardStateSaved() {
        return player2GameBoardStateSaved;
    }

    // Method to print player 1's game board state
    public void printPlayer2GameBoardState() {
        for (String[] row : player2GameBoardState) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    // Method to update the count of ships placed
    public void updateShipsPlacedCount() {
        shipsPlacedCount++;
    }
    public int getShipsPlacedCount() {
        return shipsPlacedCount;
    }
    // Method to update the count of ships placed
    public void updateShipsPlacedCountP2() {
        shipsPlacedCountP2++;
    }
    public int getShipsPlacedCountP2() {
        return shipsPlacedCountP2;
    }
    // Method to validate ship placement
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
    // Method to validate ship placement
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
    // Method to map color names to Color objects
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

    // Getter method for player 1's game board state
    public String[][] getPlayer1GameBoardState() {
        return player1GameBoardState;
    }
    // Getter method for player 1's game board state
    public String[][] getPlayer2GameBoardState() {
        return player2GameBoardState;
    }
}
