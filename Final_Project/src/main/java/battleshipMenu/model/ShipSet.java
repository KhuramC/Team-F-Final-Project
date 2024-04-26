package battleshipMenu.model;

import java.util.Arrays;
import java.util.List;

public class ShipSet {
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
}                        