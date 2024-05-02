package battleshipMenu.view;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import battleshipMenu.model.MapSize;
import battleshipMenu.model.ShipSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;

public class BattleshipPlacePhase extends JFrame {
    
    // Aesthetics
    Color lightBlue = new Color(173, 216, 230);

    // UI elements for ship placement
    private JPanel gameBoardPanel;
    private JButton[][] boardCells;
    private JButton rotateButton;
    private JButton resetButton;
    private JComboBox<String> shipComboBox;
    private JButton doneButton; // Declare the Done button as a class-level variable
    private int shipsPlacedCount = 0; // Keep track of the number of ships placed
    
 // Declare a set to keep track of placed ships
    private Set<String> placedShipSet = new HashSet<>();
    private String selectedShipSet;

    
    private int numRows; // Number of rows in the game board
    private int numCols; // Number of columns in the game board
    
    private String selectedShip; // Store the selected ship
    private List<Point> placedShips; // Store the cells where ships are placed

    public String[][] player1GameBoardState;
    private boolean player1GameBoardStateSaved = false; // Initialize as false
    
    // Flag to toggle ship orientation
    private boolean isVertical = true; // Default is vertical
    
    public BattleshipPlacePhase(int numRows, int numCols, String shipSet) {
       
    	System.out.println("numRows: " + numRows + ", numCols: " + numCols); // Debugging print statement
    	setTitle("Battleship - Ship Placement Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);

        selectedShipSet = shipSet;
        placedShips = new ArrayList<>(); // Initialize the list of placed ships
        player1GameBoardState = new String[numRows][numCols]; // Initialize player 1's game board state

        // Initialize each cell in player 1's game board state array with the default value representing water
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                player1GameBoardState[row][col] = "~";
            }
        }

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
        doneButton = new JButton("Done");

        JLabel WhatPlayer = new JLabel("Player 1's Game Board");
        WhatPlayer.setBounds(500, 1, 300, 30);
        gameBoardPanel.add(WhatPlayer);
        
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
        rotateButton = new JButton("Rotate Ship: Vertical");
        rotateButton.setBounds(800, 100, 250, 30); // Adjust position and size as needed

        resetButton = new JButton("Reset Grid/Ship Placements");
        resetButton.setBounds(800, 50, 250, 30); // Adjust position and size as needed
        
     // Add the "Done" button to the game board panel
        JButton doneButton = new JButton("Done");
        doneButton.setBounds(800, 400, 150, 30); // Adjust position and size as needed
        gameBoardPanel.add(doneButton);

        // Add buttons to the game board panel
        gameBoardPanel.add(rotateButton);
        gameBoardPanel.add(resetButton);

        
        shipComboBox = new JComboBox<>();
        shipComboBox.setBounds(800, 250, 150, 30);
        gameBoardPanel.add(shipComboBox);
     // Add ship buttons based on selected ship set
        addShipsToComboBox(shipSet);
        
        shipComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected ship from the combo box
                selectedShip = (String) shipComboBox.getSelectedItem();
                
                if (placedShipSet.contains(selectedShip)) {
                    shipComboBox.removeItem(selectedShip);
                }
            }
        });

     // Add mouse listener to the game board cells
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                JButton cellButton = boardCells[row][col];
                int finalRow = row;
                int finalCol = col;
                cellButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Check if a ship is selected
                        if (selectedShip != null) {
                            // Get the selected ship
                            ShipSet.Ship ship = ShipSet.getShip(selectedShip);
                            if (ship != null) {
                                int shipSize = ship.getSize();
                                int direction = isVertical ? 1 : 0; // Determine direction based on orientation
                                
                                // Check if the placement is valid for the entire ship
                                if (isValidPlacement(finalRow, finalCol, shipSize, isVertical, numRows, numCols)) {
                                    // Place the ship
                                    for (int i = 0; i < shipSize; i++) {
                                        int nextRow = finalRow + (isVertical ? i : 0);
                                        int nextCol = finalCol + (isVertical ? 0 : i);
                                        boardCells[nextRow][nextCol].setBackground(Color.GRAY);
                                        placedShips.add(new Point(nextRow, nextCol));
                                    }
                                    
                                    // Update player 1's game board state
                                    updatePlayer1GameBoardState();
                                    printPlayer1GameBoardState();
                                    
                                 // Update combo box options
                                    shipComboBox.removeItem(selectedShip);
                                    
                                 // Update the count of ships placed
                                    updateShipsPlacedCount(); // Call the method here
                                }
                            }
                        }
                    }
                });
            }
        }
     // Add action listener to the rotate button
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle ship orientation
                isVertical = !isVertical;
                // Update button text based on ship orientation
                if (isVertical) {
                    rotateButton.setText("Rotate Ship: Vertical");
                } else {
                    rotateButton.setText("Rotate Ship: Horizontal");
                }
            }
        });
        
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if all 5 ships have been placed
                if (shipsPlacedCount == 5) {
                    // Ask for confirmation
                    int choice = JOptionPane.showConfirmDialog(null, "Are you done placing ships?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        // Implement the action when all ships are placed and the user confirms
                    	savePlayer1GameBoardState();
                        System.out.println("All ships have been placed. Proceed to the next phase.");
                        // Here you can proceed to the next phase or perform any other action
                     // Create BattleshipPlacePhaseP2 for Player 2
                        SwingUtilities.invokeLater(() -> {
                            BattleshipPlacePhaseP2 placePhaseP2 = new BattleshipPlacePhaseP2(numRows, numCols, selectedShipSet, player1GameBoardState);
                            placePhaseP2.setVisible(true);
                        });
                        
                        // Hide BattleshipPlacePhase for Player 1
                        setVisible(false);
                        
                    } else {
                        // If the user selects "No", do nothing
                    }
                } else {
                    // Display a message indicating that all ships need to be placed
                    JOptionPane.showMessageDialog(null, "Please place all 5 ships before proceeding.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
//        resetButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	resetGridState();
//            	System.out.println("Reset button pressed");
//            	// Clear the placed ships list
//                placedShips.clear();
//                // Reset ship selection combo box
//                resetShipComboBox(shipSet);
//                // Reset ship placement to default (vertical)
//                isVertical = true;
//                rotateButton.setText("Rotate Ship: Vertical");
//            }
//        });
//        
        // Add the game board panel to the frame
        add(gameBoardPanel);
  }

 // Method to add ship buttons based on the selected ship set
    private void addShipsToComboBox(String shipSet) {
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
            shipComboBox.addItem(ship);
        }
    }
 // Update player 1's game board state when a ship is placed
    private void updatePlayer1GameBoardState() {
        // Iterate through the list of placed ships
        for (Point shipLocation : placedShips) {
            int row = shipLocation.x;
            int col = shipLocation.y;
            // Update the corresponding cell in player 1's game board state array to indicate the presence of a ship
            player1GameBoardState[row][col] = "O";
        }
    }
 // Method to save "Player 1's Game Board State"
    private void savePlayer1GameBoardState() {
        // Check if the game board state has already been saved
        if (!isPlayer1GameBoardStateSaved()) {
            // Save the game board state
            // You can store it in a variable or write it to a file/database, depending on your requirements
            // For demonstration purposes, let's print the game board state
            System.out.println("Player 1's Game Board State Saved:");
            printPlayer1GameBoardState();
            // Set a flag indicating that the game board state has been saved
            // You might need to modify this flag based on your actual implementation
            // For demonstration purposes, let's assume a boolean flag named "player1GameBoardStateSaved"
            player1GameBoardStateSaved = true;
        } else {
            System.out.println("Player 1's Game Board State has already been saved.");
        }
    }
 // Method to check if "Player 1's Game Board State" has already been saved
    private boolean isPlayer1GameBoardStateSaved() {
        // Implement your logic to check if the game board state has been saved
        // For demonstration purposes, let's assume a boolean flag named "player1GameBoardStateSaved"
        return player1GameBoardStateSaved;
    }
    // Method to print player 1's game board state
    private void printPlayer1GameBoardState() {
        System.out.println("Player 1's Game Board State:");
        for (String[] row : player1GameBoardState) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private void updateShipsPlacedCount() {
        shipsPlacedCount++;
        // Check if all 5 ships have been placed
        if (shipsPlacedCount == 5) {
            doneButton.setEnabled(true); // Enable the "Done" button
        }
    }
    private boolean isValidPlacement(int startRow, int startCol, int shipSize, boolean isVertical, int numRows, int numCols) {
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

        return true; // Placement is valid
    }
    public String[][] getPlayer1GameBoardState() {
        return player1GameBoardState;
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
