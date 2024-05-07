package battleshipMenu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing different sets of ships available in the game.
 * 
 * @author Roney
 */
public class ShipSet {
    /**
     * Inner class representing a ship in the game.
     */
    public static class Ship {
        private String name;
        private int size;

        /**
         * Constructor for Ship class.
         * @param name The name of the ship.
         * @param size The size of the ship.
         * 
         * @author Roney
         */
        public Ship(String name, int size) {
            this.name = name;
            this.size = size;
        }

        /**
         * Gets the name of the ship.
         * @return The name of the ship.
         * 
         * @author Roney
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the size of the ship.
         * @return The size of the ship.
         * 
         * @author Roney
         */
        public int getSize() {
            return size;
        }
    }

    public static final List<String> STEALTH = Arrays.asList(
        "Cruiser (2x1)",
        "Cruiser (2x1)",
        "Cruiser (2x1)",
        "Submarine (3x1)",
        "Destroyer (3x1)"
    );

    public static final List<String> NORMAL = Arrays.asList(
        "Cruiser (2x1)",
        "Submarine (3x1)",
        "Destroyer (3x1)",
        "Battleship (4x1)",
        "AircraftCarrier (5x1)"
    );

    public static final List<String> MASSIVE = Arrays.asList(
        "AircraftCarrier (5x1)",
        "AircraftCarrier (5x1)",
        "Battleship (4x1)",
        "Battleship (4x1)",
        "Submarine (3x1)"
    );

    /**
     * Gets the list of ships for the specified ship set.
     * @param shipSetName The name of the ship set.
     * @return The list of ships for the specified ship set.
     * 
     * @author Roney
     */
    public static List<String> getShipList(String shipSetName) {
        switch (shipSetName.toUpperCase()) {
            case "STEALTH":
                return new ArrayList<>(STEALTH);
            case "NORMAL":
                return new ArrayList<>(NORMAL);
            case "MASSIVE":
                return new ArrayList<>(MASSIVE);
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Gets a Ship object for the specified ship name.
     * @param shipName The name of the ship.
     * @return The Ship object corresponding to the specified ship name.
     * 
     * @author Roney
     */
    public static Ship getShip(String shipName) {
        switch (shipName) {
            case "Cruiser (2x1)":
                return new Ship("Cruiser", 2);
            case "Submarine (3x1)":
                return new Ship("Submarine", 3);
            case "Destroyer (3x1)":
                return new Ship("Destroyer", 3);
            case "Battleship (4x1)":
                return new Ship("Battleship", 4);
            case "AircraftCarrier (5x1)":
                return new Ship("Aircraft Carrier", 5);
            default:
                return null;
        }
    }
}