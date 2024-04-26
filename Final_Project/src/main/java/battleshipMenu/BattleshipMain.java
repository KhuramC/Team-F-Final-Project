package battleshipMenu;

import java.util.List;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import battleshipMenu.control.*;
import battleshipMenu.model.*;
import battleshipMenu.view.*;

public class BattleshipMain {
    private BattleshipMain() {
    }

    public static void startBattleship() {
        // Print ship sets
        printShipSets();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BattleshipMenuModel model = new BattleshipMenuModel();
                BattleshipMenuView startView = new BattleshipMenuView();
                BattleshipMenuController controller = new BattleshipMenuController(model, startView);
                controller.initiate();
            }
        });

        // Play Battleship game
        playBattleship();
    }

    // Method to print ship sets
    private static void printShipSets() {
        System.out.println("Ship Sets:");
        System.out.println("Stealth Set:");
        printShipSet(ShipSet.STEALTH);
        System.out.println("\nNormal Set:");
        printShipSet(ShipSet.NORMAL);
        System.out.println("\nMassive Set:");
        printShipSet(ShipSet.MASSIVE);
        System.out.println();
    }

    // Method to print a ship set
    private static void printShipSet(List<String> shipSet) {
        for (String ship : shipSet) {
            System.out.println(ship);
        }
    }

    private static final int GRID_SIZE = 9;
    private static final char WATER = '~';
    private static final char SHIP = 'O';
    private static final char HIT = 'X';
    private static final char MISS = '-';

    private static char[][] player1Grid;
    private static char[][] player2Grid;

    public static void playBattleship() {
        // Initialize grids
        player1Grid = new char[GRID_SIZE][GRID_SIZE];
        player2Grid = new char[GRID_SIZE][GRID_SIZE];
        initializeGrids(player1Grid);
        initializeGrids(player2Grid);

        // Player 1 places ships
        System.out.println("Player 1, place your ships:");
        placeShips(player1Grid);

        // Player 2 places ships
        System.out.println("Player 2, place your ships:");
        placeShips(player2Grid);

        // Display initial grids
        displayGrids();

        // Start shooting phase
        startShootingPhase();
    }

    private static void initializeGrids(char[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = WATER;
            }
        }
    }

    private static void placeShips(char[][] grid) {
        Scanner scanner = new Scanner(System.in);
        for (int ship = 1; ship <= 3; ship++) { // Place 3 ships
            System.out.println("Place Ship " + ship + " (size 3):");
            System.out.print("Enter row (1-9): ");
            int row = scanner.nextInt() - 1;
            System.out.print("Enter column (A-I): ");
            int col = columnToIndex(scanner.next().toUpperCase().charAt(0));

            // Check if the selected position is valid
            if (row < 0 || row >= GRID_SIZE || col < 0 || col >= GRID_SIZE || grid[row][col] == SHIP) {
                System.out.println("Invalid position. Try again.");
                ship--; // Repeat the current ship placement
                continue;
            }

            // Place ship
            grid[row][col] = SHIP;
            if (col + 2 < GRID_SIZE) {
                grid[row][col + 1] = SHIP;
                grid[row][col + 2] = SHIP;
            } else {
                System.out.println("Cannot place ship here. Try again.");
                ship--; // Repeat the current ship placement
            }
        }
    }

    private static void displayGrids() {
        System.out.println("Player 1's Grid:");
        displayGrid(player1Grid);
        System.out.println("\nPlayer 2's Grid:");
        displayGrid(player2Grid);
    }

    private static void displayGrid(char[][] grid) {
        System.out.print("  ");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();

        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int columnToIndex(char col) {
        return col - 'A';
    }

    private static void startShootingPhase() {
        boolean player1Turn = true;
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            if (player1Turn) {
                System.out.println("Player 1's turn:");
                playerTurn(player2Grid);
            } else {
                System.out.println("Player 2's turn:");
                playerTurn(player1Grid);
            }
            displayGrids(); // Display updated grids after each turn
            player1Turn = !player1Turn;
        }

        if (player1Turn) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("Player 1 wins!");
        }
    }

    private static void playerTurn(char[][] opponentGrid) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean validShot = false;

        while (!validShot) {
            System.out.print("Enter row to attack (1-9): ");
            row = scanner.nextInt() - 1;
            System.out.print("Enter column to attack (A-I): ");
            col = columnToIndex(scanner.next().toUpperCase().charAt(0));

            if (row < 0 || row >= GRID_SIZE || col < 0 || col >= GRID_SIZE) {
                System.out.println("Invalid position. Try again.");
                continue;
            }

            if (opponentGrid[row][col] == WATER) {
                System.out.println("Miss!");
                opponentGrid[row][col] = MISS;
            } else if (opponentGrid[row][col] == SHIP) {
                System.out.println("Hit!");
                opponentGrid[row][col] = HIT;
            } else {
                System.out.println("Already shot there. Try again.");
                continue;
            }

            validShot = true;
        }
    }

    private static boolean isGameOver() {
        return !hasShipsRemaining(player1Grid) || !hasShipsRemaining(player2Grid);
    }

    private static boolean hasShipsRemaining(char[][] grid) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == SHIP) {
                    return true;
                }
            }
        }
        return false;
    }
}

