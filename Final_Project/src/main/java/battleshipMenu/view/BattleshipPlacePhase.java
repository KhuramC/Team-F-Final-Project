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

/**
 * The view class responsible for the ship placement phase of the Battleship game.
 * This class extends JFrame to create a graphical user interface.
 * 
 * @author Roney
 */
public class BattleshipPlacePhase extends JFrame {
    
    // Aesthetics
    Color lightBlue = new Color(173, 216, 230);
    private Color player1ShipColor;
    private Color player2ShipColor;
    
    private BattleshipGameModel battleshipGameModel;
    // UI elements for ship placement
    private JPanel gameBoardPanel;
    private JButton[][] boardCells;
    private JButton rotateButton;
    private JButton doneButton;
    private JLabel placeLabel;
    private JLabel[] columnLabels;
    private JLabel[] rowLabels;
    private JLabel playerLabel;
    
    private JComboBox<String> shipComboBox; // Combobox with ship set
    private JTextArea explanationTextArea; // Text area

    private int numRows; // Number of rows in the game board
    private int numCols; // Number of columns in the game board

    public String[][] player1GameBoardState;
    private String shootingTimer;
    private String selectedShip; // Store the selected ship
    private String selectedShipSet;
    
    private List<Point> placedShips; // Store the cells where ships are placed
    private Set<String> placedShipSet = new HashSet<>();
    
    private boolean isVertical = true; // Default is vertical 
    /**
     * Constructor for the BattleshipPlacePhase class.
     * @param numRows Number of rows in the game board.
     * @param numCols Number of columns in the game board.
     * @param shipSet Selected ship set.
     * @param P1shipColor Color for Player 1's ships.
     * @param P2shipColor Color for Player 2's ships.
     * @param shootingTimer Selected shooting timer option.
     * @param battleshipGameModel Reference to the BattleshipGameModel.
     * 
     * @author Roney
     */
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

        // Initialize UI elements and layout
        initializeUI(numRows, numCols, shipSet);
    }
    /**
     * Initializes the user interface for the ship placement phase.
     *
     * @param numRows  The number of rows in the game board.
     * @param numCols  The number of columns in the game board.
     * @param shipSet  The selected ship set. Valid values are "Stealth", "Normal", and "Massive".
     *                 Determines the set of ships available for placement.
     *                 
     *                 @author Roney
     */
    private void initializeUI(int numRows, int numCols, String shipSet) {
    	initializeGameBoardPanel(numRows, numCols);
    	initializeDoneButton();
    	initializeExplanationTextArea(numRows);
    	initializePlayerLabel(numRows, numCols);
        initializePlaceLabel();
        initializeColumnLabels(numCols);
        initializeRowLabels(numRows);
        initializeCellButtons(numRows, numCols);
        initializeRotateButton();
        initializeShipComboBox(shipSet);
        addShipsToComboBox(shipSet);
        addMouseListenerToCellButtons(numRows,numCols);
        addRotateButtonListener();
        addShipComboBoxListener();        
       
        /**
         * ActionListener for the "Done" button. Performs actions when the button is clicked,
         * such as checking if all ships have been placed, prompting the user for confirmation,
         * and proceeding to the next phase of the game if conditions are met.
         * 
         * @author Roney
         */
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
    /**
     * Adds an ActionListener to the shipComboBox. When an action (selection) is performed
     * on the shipComboBox, this listener gets the selected ship from the combo box.
     * If the selected ship is already placed, it removes it from the combo box options.
     * 
     * @author Roney
     */
    private void addShipComboBoxListener() {
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
    }
    /**
     * ActionListener for the "Rotate" button. Toggles the orientation of the ship between vertical and horizontal
     * when the button is clicked. Updates the button text to reflect the current ship orientation.
     * 
     * @author Roney
     */
    private void addRotateButtonListener() {
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
    }
    /**
     * Adds a mouse listener to a cell button on the game board. When the cell button is clicked,
     * checks if a ship is selected. If a ship is selected, verifies if the placement is valid for the
     * entire ship. If the placement is valid, places the ship on the game board, updates player 1's
     * game board state, updates the combo box options, and updates the count of ships placed.
     * 
     * @param finalRow      The final row index of the cell button.
     * @param finalCol      The final column index of the cell button.
     * @param selectedShip  The currently selected ship to be placed.
     * @param isVertical    Flag indicating whether the ship orientation is vertical.
     * @param numRows       The number of rows in the game board.
     * @param numCols       The number of columns in the game board.
     * @param player1ShipColor  The color to indicate player 1's ship on the game board.
     * @param placedShips   Set containing the placed ships' locations.
     * 
     * @author Roney
     */
    private void addMouseListenerToCellButtons(int numRows, int numCols) {
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
//    	                            printPlayer1GameBoardState();

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
    }
    /**
     * Initializes the game board panel with a custom paint component for drawing grid lines.
     * 
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     * 
     * @author Roney
     */
    private void initializeGameBoardPanel(int numRows, int numCols) {
        gameBoardPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                DrawGrid.drawGridLines(g, numRows, numCols, 50, 50, 50);
            }
        };
        gameBoardPanel.setLayout(null);
    }
    /**
     * Initializes the "Done" button and adds it to the game board panel.
     * 
     * @author Roney
     */
    private void initializeDoneButton() {
        doneButton = new JButton("Done");
        doneButton.setBounds(800, 400, 150, 30);
        gameBoardPanel.add(doneButton);
    }
    /**
     * Initializes the explanation text area and adds it to the game board panel.
     * 
     * @param numRows The number of rows in the game board.
     * 
     * @author Roney
     */
    private void initializeExplanationTextArea(int numRows) {
        explanationTextArea = new JTextArea();
        explanationTextArea.setBounds(50, numRows * 50 + 100, numRows * 50, 100);
        explanationTextArea.setEditable(false);
        explanationTextArea.setLineWrap(true);
        explanationTextArea.setWrapStyleWord(true);
        explanationTextArea.setFont(explanationTextArea.getFont().deriveFont(Font.BOLD, 14));
        explanationTextArea.setText("Welcome to the 'Ship Placement' phase. This is where player 1 will select and place their ships. Use the 'rotate' button to rotate the placement of your ships. Select 'Done' once all 5 ships have been placed to continue to the shooting phase.");
        gameBoardPanel.add(explanationTextArea);
    }
    /**
     * Initializes the label indicating the player's game board and adds it to the game board panel.
     * 
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     * 
     * @author Roney
     */
    private void initializePlayerLabel(int numRows, int numCols) {
    	// Initialize playerLabel within the method
        playerLabel = new JLabel("Player 1's Game Board");
        int labelWidth = 300; // Adjust as needed
        int labelHeight = 30; // Adjust as needed
        int labelX = (numCols * 50) / 2; // Center horizontally
        int labelY = numRows * 50 + 60; // Position slightly below the P1Board
        playerLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        gameBoardPanel.add(playerLabel);
    }
    /**
     * Initializes the label indicating the ship selection and placement area.
     * 
     * @author Roney
     */
    private void initializePlaceLabel() {
        placeLabel = new JLabel("↓↓ Select & Place Ships ↓↓");
        placeLabel.setBounds(800, 215, 150, 30);
        gameBoardPanel.add(placeLabel);
    }
    /**
     * Initializes the column labels (letters) for the game board.
     * 
     * @param numCols The number of columns in the game board.
     * 
     * @author Roney
     */
    private void initializeColumnLabels(int numCols) {
    	int cellSize = 50; // Adjust size as needed
        int startX = 50;
        int startY = 50;
        columnLabels = new JLabel[numCols]; // Initialize the array
        // Add column labels (letters)
        for (int col = 0; col < numCols; col++) {
            JLabel columnLabel = new JLabel(String.valueOf((char) ('A' + col)));
            columnLabel.setBounds(startX + col * cellSize + cellSize / 2, startY - 30, 20, 20);
            gameBoardPanel.add(columnLabel);
            columnLabels[col] = columnLabel; // Add label to the array
        }
    }
    /**
     * Initializes the row labels (numbers) for the game board.
     * 
     * @param numRows The number of rows in the game board.
     * 
     * @author Roney
     */
    private void initializeRowLabels(int numRows) {
    	int cellSize = 50; // Adjust size as needed
        int startX = 50;
        int startY = 50;
        rowLabels = new JLabel[numRows]; // Initialize the array
        // Add row labels (numbers)
        for (int row = 0; row < numRows; row++) {
            JLabel rowLabel = new JLabel(String.valueOf(row + 1));
            rowLabel.setBounds(startX - 30, startY + row * cellSize + cellSize / 2, 20, 20);
            gameBoardPanel.add(rowLabel);
            rowLabels[row] = rowLabel; // Add label to the array
        }
    }
    /**
     * Initializes the cell buttons for the game board and adds them to the game board panel.
     * 
     * @param numRows The number of rows in the game board.
     * @param numCols The number of columns in the game board.
     * 
     * @author Roney
     */
    private void initializeCellButtons(int numRows, int numCols) {
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
    }
    /**
     * Initializes the button for rotating the ship orientation and adds it to the game board panel.
     * 
     * @author Roney
     */
    private void initializeRotateButton() {
        rotateButton = new JButton("Rotate Ship: Vertical");
        rotateButton.setBounds(800, 100, 250, 30); // Adjust position and size as needed
        gameBoardPanel.add(rotateButton);
    }
    /**
     * Initializes the ship combo box and adds it to the game board panel.
     * 
     * @param shipSet The set of available ship types.
     * 
     * @author Roney
     */
    private void initializeShipComboBox(String shipSet) {
        shipComboBox = new JComboBox<>();
        shipComboBox.setBounds(800, 250, 150, 30);
        gameBoardPanel.add(shipComboBox);
    }
    /**
     * Adds ship buttons to the combo box based on the selected ship set.
     *
     * @param shipSet The selected ship set. Valid values are "Stealth", "Normal", and "Massive".
     * If an invalid ship set is provided, no ship buttons are added.
     *                
     * @author Roney
     */
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
                break;
        }
    }
    /**
     * Method to place ship buttons based on the ship set.
     *
     * @param shipSet The list of ship names to be displayed as options for placement.
     * 
     * @author Roney
     */
    private void placeShipButtons(List<String> shipSet) {
        int x = 800; // Initial x position for ship buttons
        int y = 250; // Initial y position for ship buttons
        int buttonWidth = 150; // Width of ship buttons
        int buttonHeight = 30; // Height of ship buttons

        for (String ship : shipSet) {
            shipComboBox.addItem(ship);
        }
    }
    /**
     * Update player 1's game board state when a ship is placed.
     * 
     * @author Roney
     */
    private void updatePlayer1GameBoardState() {
    	battleshipGameModel.updatePlayer1GameBoardState(new HashSet<>(placedShips));
    }
    /**
     * Save player 1's game board state if it has not been saved already.
     * 
     * @author Roney
     */
    private void savePlayer1GameBoardState() {
    	if (!battleshipGameModel.isPlayer1GameBoardStateSaved()) {
            battleshipGameModel.savePlayer1GameBoardState();
        } else {
            System.out.println("Player 1's Game Board State has already been saved.");
        }
    }
    /**
     * Check if player 1's game board state has already been saved.
     *
     * @return true if player 1's game board state has been saved, false otherwise.
     * 
     * @author Roney
     */
    private boolean isPlayer1GameBoardStateSaved() {
    	return battleshipGameModel.isPlayer1GameBoardStateSaved();
    }
    /**
     * Print player 1's game board state to the console.
     * 
     * @author Roney
     */
    private void printPlayer1GameBoardState() {
    	battleshipGameModel.printPlayer1GameBoardState();
    }
    /**
     * Update the count of ships placed for player 1.
     * 
     * @author Roney
     */
    private void updateShipsPlacedCount() {
    	battleshipGameModel.updateShipsPlacedCount();
    }
    /**
     * Check if a ship placement is valid on player 1's game board.
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
    private boolean isValidPlacement(int startRow, int startCol, int shipSize, boolean isVertical, int numRows, int numCols) {
    	return battleshipGameModel.isValidPlacement(startRow, startCol, shipSize, isVertical, numRows, numCols);
    }
    /**
     * Map a color name to a Color object.
     *
     * @param colorName The name of the color.
     * @return The corresponding Color object.
     * 
     * @author Roney
     */
    public Color mapColor(String colorName) {
    	return battleshipGameModel.mapColor(colorName);
    }
    /**
     * Get player 1's game board state.
     *
     * @return The 2D array representing player 1's game board state.
     * 
     * @author Roney
     */
    public String[][] getPlayer1GameBoardState() {
    	return battleshipGameModel.getPlayer1GameBoardState();
    }
    /**
     * Getter method for retrieving the game board panel.
     *
     * @return The JPanel representing the game board panel.
     * 
     * @author Roney
     */
    public JPanel getGameBoardPanel() {
        return gameBoardPanel;
    }

    /**
     * Getter method for retrieving the "Done" button.
     *
     * @return The JButton representing the "Done" button.
     * 
     * @author Roney
     */
    public JButton getDoneButton() {
        return doneButton;
    }

    /**
     * Getter method for retrieving the explanation text area.
     *
     * @return The JTextArea representing the explanation text area.
     * 
     * @author Roney
     */
    public JTextArea getExplanationTextArea() {
        return explanationTextArea;
    }

    /**
     * Getter method for retrieving the player label.
     *
     * @return The JLabel representing the player label.
     * 
     * @author Roney
     */
    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    /**
     * Getter method for retrieving the place label.
     *
     * @return The JLabel representing the place label.
     * 
     * @author Roney
     */
    public JLabel getPlaceLabel() {
        return placeLabel;
    }

    /**
     * Getter method for retrieving the column labels.
     *
     * @return An array of JLabel objects representing the column labels.
     * 
     * @author Roney
     */
    public JLabel[] getColumnLabels() {
        return columnLabels;
    }

    /**
     * Getter method for retrieving the row labels.
     *
     * @return An array of JLabel objects representing the row labels.
     * 
     * @author Roney
     */
    public JLabel[] getRowLabels() {
        return rowLabels;
    }

    /**
     * Getter method for retrieving the cell buttons.
     *
     * @return A 2D array of JButton objects representing the cell buttons.
     * 
     * @author Roney
     */
    public JButton[][] getBoardCells() {
        return boardCells;
    }

    /**
     * Getter method for retrieving the rotate button.
     *
     * @return The JButton representing the rotate button.
     * 
     * @author Roney
     */
    public JButton getRotateButton() {
        return rotateButton;
    }

    /**
     * Getter method for retrieving the ship combo box.
     *
     * @return The JComboBox representing the ship combo box.
     * 
     * @author Roney
     */
    public JComboBox<String> getShipComboBox() {
        return shipComboBox;
    }

}