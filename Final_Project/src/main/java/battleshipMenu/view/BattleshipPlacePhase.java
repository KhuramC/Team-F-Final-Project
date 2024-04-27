package battleshipMenu.view;

import javax.swing.*;
import java.util.List;
import battleshipMenu.model.MapSize;
import battleshipMenu.model.ShipSet;

import java.awt.*;

public class BattleshipPlacePhase extends JFrame {
    
	//Aesthetics
	Color lightBlue = new Color(173,216,230);

	// UI elements for ship placement
    private JPanel gameBoardPanel;
    private JButton[][] boardCells;
    private JButton rotateButton;
    private JButton resetButton;

    
    public BattleshipPlacePhase(int numRows, int numCols, String shipSet) {
        setTitle("Battleship - Ship Placement Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);

        // Initialize UI elements and layout
        initializeUI(numRows, numCols, shipSet);
    }

    private void initializeUI(int numRows, int numCols, String shipSet) {
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

        JLabel placeLabel = new JLabel("↓↓ Select & Place Ships ↓↓");
        placeLabel.setBounds(800, 215, 150, 30);
        gameBoardPanel.add(placeLabel);
        
        // Create buttons for game board cells and place them manually
        int cellSize = 50; // Adjust size as needed
        int startX = 50;
        int startY = 50;

     // Add column labels (letters)
        for (int col = 0; col < numCols; col++) {
            JLabel columnLabel = new JLabel(String.valueOf((char)('A' + col)));
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
                boardCells[row][col] = cellButton;
                gameBoardPanel.add(cellButton);
            }
        }
        

        // Initialize buttons for ship placement options
        rotateButton = new JButton("Rotate Ship");
        rotateButton.setBounds(800, 100, 150, 30); // Adjust position and size as needed

        resetButton = new JButton("Reset Grid");
        resetButton.setBounds(800, 50, 150, 30); // Adjust position and size as needed
        

        // Add buttons to the game board panel
        gameBoardPanel.add(rotateButton);
        gameBoardPanel.add(resetButton);

     // Add ship buttons based on selected ship set
        addShipButtons(shipSet);
        
        // Add the game board panel to the frame
        add(gameBoardPanel);
    }

 // Method to add ship buttons based on the selected ship set
    private void addShipButtons(String shipSet) {
        switch (shipSet) {
            case "Stealth":
                placeShipButtons(ShipSet.STEALTH);
                break;
            case "Normal":
                placeShipButtons(ShipSet.NORMAL);
                break;
            case "Massive":
                placeShipButtons(ShipSet.MASSIVE);
                break;
            default:
                // Handle other ship sets or provide a default behavior
                break;
        }
    }
    // Method to place ship buttons based on the ship set
    private void placeShipButtons(List<String> shipSet) {
        int x = 800; // Initial x position for ship buttons
        int y = 250; // Initial y position for ship buttons
        int buttonWidth = 150; // Width of ship buttons
        int buttonHeight = 30; // Height of ship buttons

        for (String ship : shipSet) {
            JButton shipButton = new JButton(ship);
            shipButton.setBounds(x, y, buttonWidth, buttonHeight);
            gameBoardPanel.add(shipButton);
            y += buttonHeight + 10; // Increase y position for next ship button
        }
    }

    // Methods for handling user interaction (e.g., placing ships, rotating ships)

    // Method to update UI based on game state (e.g., highlighting selected cells)

    // Other necessary methods...

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	// Get the selected board size from BattleshipMenuView
            String selectedBoardSize = "Small (7x7)";
            String selectedShipSet = "Stealth"; // Replace with actual selected ship set
            // Replace with actual selected size
            MapSize boardSize = MapSize.fromString(selectedBoardSize);

            // Extract rows and columns from the selected board size
            int numRows = boardSize.getRows();
            int numCols = boardSize.getCols();
        	
            BattleshipPlacePhase placePhase = new BattleshipPlacePhase(numRows, numCols, selectedShipSet);
            placePhase.setVisible(true);
        });
    }
}

