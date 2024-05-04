package battleshipMenu.view;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import battleshipMenu.model.MapSize;
import battleshipMenu.model.ShipSet;
import battleshipMenu.model.BattleshipGameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;

public class BattleshipPlacePhase extends JFrame {
    
    // Aesthetics
    Color lightBlue = new Color(173, 216, 230);
   
    private BattleshipGameModel battleshipGameModel;
    // UI elements for ship placement
    private JPanel gameBoardPanel;
    private JButton[][] boardCells;
    private JButton rotateButton;
    private JButton resetButton;
    private JComboBox<String> shipComboBox;
    private JButton doneButton; // Declare the Done button as a class-level variable
    
    private JTextArea explanationTextArea;
    
    private Color player1ShipColor;
    private Color player2ShipColor;
    
 // Declare a set to keep track of placed ships
    private Set<String> placedShipSet = new HashSet<>();
    private String selectedShipSet;

    private String shootingTimer;
    private int numRows; // Number of rows in the game board
    private int numCols; // Number of columns in the game board
    
    private String selectedShip; // Store the selected ship
    private List<Point> placedShips; // Store the cells where ships are placed

    public String[][] player1GameBoardState;
    private boolean player1GameBoardStateSaved = false; // Initialize as false
    
    // Flag to toggle ship orientation
    private boolean isVertical = true; // Default is vertical
    
    public BattleshipPlacePhase(int numRows, int numCols, String shipSet, String P1shipColor, String P2shipColor, String shootingTimer, BattleshipGameModel battleshipGameModel) {
       
    	System.out.println("numRows: " + numRows + ", numCols: " + numCols); // Debugging print statement
    	setTitle("Battleship - Ship Placement Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);

        selectedShipSet = shipSet;
        placedShips = new ArrayList<>(); // Initialize the list of placed ships
        player1GameBoardState = new String[numRows][numCols]; // Initialize player 1's game board state

        this.battleshipGameModel = battleshipGameModel;
        this.shootingTimer = shootingTimer;
        
        player1ShipColor = mapColor(P1shipColor);
        player2ShipColor = mapColor(P2shipColor);
        
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

        explanationTextArea = new JTextArea();
        explanationTextArea.setBounds(50, numRows * 50 + 100, numRows * 50, 100); // Adjust position and size as needed
        explanationTextArea.setEditable(false); // Make it read-only
        explanationTextArea.setLineWrap(true); // Enable line wrapping
        explanationTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        explanationTextArea.setFont(explanationTextArea.getFont().deriveFont(Font.BOLD, 14));
        explanationTextArea.setText("Welcome to the 'Ship Placement' phase. This is where player 1 will select and place their ships. Use the 'rotate' button to rotate the placement of your ships. Select 'Done' once done placing all 5 ships to continue to the next phase."); // Initial text
        gameBoardPanel.add(explanationTextArea);
        
        JLabel WhatPlayer = new JLabel("Player 1's Game Board");
        int labelWidth = 300; // Adjust as needed
        int labelHeight = 30; // Adjust as needed
        int labelX = (numCols * 50 ) / 2; // Center horizontally
        int labelY = numRows * 50 + 60; // Position slightly below the P1Board
        WhatPlayer.setBounds(labelX, labelY, labelWidth, labelHeight);
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
        
     // Add the "Done" button to the game board panel
        JButton doneButton = new JButton("Done");
        doneButton.setBounds(800, 400, 150, 30); // Adjust position and size as needed
        gameBoardPanel.add(doneButton);

        // Add buttons to the game board panel
        gameBoardPanel.add(rotateButton);

        
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
										boardCells[nextRow][nextCol].setBackground(player1ShipColor);
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
            	int shipsPlacedCount = battleshipGameModel.getShipsPlacedCount(); // Get the ship count from the model
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
                            BattleshipPlacePhaseP2 placePhaseP2 = new BattleshipPlacePhaseP2(numRows, numCols, selectedShipSet, player1GameBoardState, player1ShipColor, player2ShipColor, shootingTimer, battleshipGameModel );
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
    	// Call the corresponding method in BattleshipGameModel to update the game board state
    	battleshipGameModel.updatePlayer1GameBoardState(new HashSet<>(placedShips));
    }
 // Method to save "Player 1's Game Board State"
    private void savePlayer1GameBoardState() {
    	if (!battleshipGameModel.isPlayer1GameBoardStateSaved()) {
            battleshipGameModel.savePlayer1GameBoardState();
        } else {
            System.out.println("Player 1's Game Board State has already been saved.");
        }
    }
 // Method to check if "Player 1's Game Board State" has already been saved
    private boolean isPlayer1GameBoardStateSaved() {
    	return battleshipGameModel.isPlayer1GameBoardStateSaved();
    }
    // Method to print player 1's game board state
    private void printPlayer1GameBoardState() {
    	battleshipGameModel.printPlayer1GameBoardState();
    }
    private void updateShipsPlacedCount() {
    	battleshipGameModel.updateShipsPlacedCount();
    }
    private boolean isValidPlacement(int startRow, int startCol, int shipSize, boolean isVertical, int numRows, int numCols) {
    	return battleshipGameModel.isValidPlacement(startRow, startCol, shipSize, isVertical, numRows, numCols);
    }
    private Color mapColor(String colorName) {
    	return battleshipGameModel.mapColor(colorName);
    }
    public String[][] getPlayer1GameBoardState() {
    	return battleshipGameModel.getPlayer1GameBoardState();
    }
}