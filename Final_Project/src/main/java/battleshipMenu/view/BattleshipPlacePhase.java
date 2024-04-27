package battleshipMenu.view;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
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
    
    private int numRows; // Number of rows in the game board
    private int numCols; // Number of columns in the game board
    
    private String selectedShip; // Store the selected ship
    private List<Point> placedShips; // Store the cells where ships are placed

    private String[][] player1GameBoardState;
    
    // Flag to toggle ship orientation
    private boolean isVertical = true; // Default is vertical
    
    public BattleshipPlacePhase(int numRows, int numCols, String shipSet) {
       
    	System.out.println("numRows: " + numRows + ", numCols: " + numCols); // Debugging print statement
    	setTitle("Battleship - Ship Placement Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);

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

        resetButton = new JButton("Reset Grid");
        resetButton.setBounds(800, 50, 150, 30); // Adjust position and size as needed
        

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

