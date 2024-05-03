package battleshipMenu.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShipSet {
    public static class Ship {
        private String name;
        private int size;

        public Ship(String name, int size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

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
