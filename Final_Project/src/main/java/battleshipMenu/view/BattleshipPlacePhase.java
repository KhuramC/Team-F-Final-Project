package battleshipMenu.view;

import javax.swing.*;
import battleshipMenu.model.MapSize;
import java.awt.*;

public class BattleshipPlacePhase extends JFrame {
    
	//Aesthetics
	Color lightBlue = new Color(173,216,230);

	// UI elements for ship placement
    private JPanel gameBoardPanel;
    private JButton[][] boardCells;
    private JButton rotateButton;
    private JButton resetButton;

    
    public BattleshipPlacePhase(int numRows, int numCols) {
        setTitle("Battleship - Ship Placement Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);

        // Initialize UI elements and layout
        initializeUI(numRows, numCols);
    }

    private void initializeUI(int numRows, int numCols) {
        // Initialize game board panel and cells
        gameBoardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Call DrawGrid to draw the grid lines
                DrawGrid.drawGridLines(g, numRows, numCols, 50, 50, 50);
            }
        };
        gameBoardPanel.setLayout(null); // Use absolute layout

        
        // Create buttons for game board cells and place them manually
        int cellSize = 50; // Adjust size as needed
        int startX = 50;
        int startY = 50;



        boardCells = new JButton[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JButton cellButton = new JButton();
                cellButton.setBounds(startX + col * cellSize, startY + row * cellSize, cellSize, cellSize);
                cellButton.setBackground(lightBlue);
                cellButton.setText("〰");
                boardCells[row][col] = cellButton;
                gameBoardPanel.add(cellButton);
            }
        }
        

        // Initialize buttons for ship placement options
        rotateButton = new JButton("Rotate Ship");
        rotateButton.setBounds(600, 50, 150, 30); // Adjust position and size as needed

        resetButton = new JButton("Reset Grid");
        resetButton.setBounds(800, 50, 150, 30); // Adjust position and size as needed

        // Add buttons to the game board panel
        gameBoardPanel.add(rotateButton);
        gameBoardPanel.add(resetButton);

        // Add the game board panel to the frame
        add(gameBoardPanel);
    }

    // Methods for handling user interaction (e.g., placing ships, rotating ships)

    // Method to update UI based on game state (e.g., highlighting selected cells)

    // Other necessary methods...

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	// Get the selected board size from BattleshipMenuView
            String selectedBoardSize = "Small (7x7)"; // Replace with actual selected size
            MapSize boardSize = MapSize.fromString(selectedBoardSize);

            // Extract rows and columns from the selected board size
            int numRows = boardSize.getRows();
            int numCols = boardSize.getCols();
        	
            BattleshipPlacePhase placePhase = new BattleshipPlacePhase(numRows, numCols);
            placePhase.setVisible(true);
        });
    }
}

