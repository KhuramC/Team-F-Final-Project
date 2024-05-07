package battleshipMenu.model;

import java.util.List;

/**
 * Represents the model for the Battleship game menu.
 * @author Roney
 */
public class BattleshipMenuModel {
    
    private ShipColor player1ShipColor;
    private ShipColor player2ShipColor;
    private MapSize mapSize;
    private List<String> shipSet;
    private String shootingTimer;

    /**
     * Constructor for BattleshipMenuModel.
     * 
     * @author Roney
     */
    public BattleshipMenuModel() {
        // Set default values
        player1ShipColor = ShipColor.GREEN;
        player2ShipColor = ShipColor.GREEN; // Default to the same color for both players
        mapSize = MapSize.SMALL_7x7;
        shipSet = ShipSet.STEALTH;
        shootingTimer = "No Timer";
    }

    /**
     * Gets the color of Player 1's ships.
     * @return The color of Player 1's ships.
     * 
     * @author Roney
     */
    public ShipColor getPlayer1ShipColor() {
        return player1ShipColor;
    }

    /**
     * Sets the color of Player 1's ships.
     * @param player1ShipColor The color of Player 1's ships.
     * 
     * @author Roney
     */
    public void setPlayer1ShipColor(ShipColor player1ShipColor) {
        this.player1ShipColor = player1ShipColor;
        printCurrentState();
    }

    /**
     * Gets the color of Player 2's ships.
     * @return The color of Player 2's ships.
     * 
     * @author Roney
     */
    public ShipColor getPlayer2ShipColor() {
        return player2ShipColor;
    }

    /**
     * Sets the color of Player 2's ships.
     * @param player2ShipColor The color of Player 2's ships.
     * 
     * @author Roney
     */
    public void setPlayer2ShipColor(ShipColor player2ShipColor) {
        this.player2ShipColor = player2ShipColor;
        printCurrentState();
    }

    /**
     * Gets the size of the game map.
     * @return The size of the game map.
     * 
     * @author Roney
     */
    public MapSize getMapSize() {
        return mapSize;
    }

    /**
     * Sets the size of the game map.
     * @param mapSize The size of the game map.
     * 
     * @author Roney
     */
    public void setMapSize(MapSize mapSize) {
        this.mapSize = mapSize;
        printCurrentState();
    }

    /**
     * Gets the ship set for the game.
     * @return The ship set for the game.
     * 
     * @author Roney
     */
    public List<String> getShipSet() {
        return shipSet;
    }

    /**
     * Sets the ship set for the game.
     * @param shipSet The ship set for the game.
     * 
     * @author Roney
     */
    public void setShipSet(List<String> shipSet) {
        this.shipSet = shipSet;
        printCurrentState();
    }

    /**
     * Gets the shooting timer setting.
     * @return The shooting timer setting.
     * 
     * @author Roney
     */
    public String getShootingTimer() {
        return shootingTimer;
    }

    /**
     * Sets the shooting timer setting.
     * @param shootingTimer The shooting timer setting.
     * 
     * @author Roney
     */
    public void setShootingTimer(String shootingTimer) {
        this.shootingTimer = shootingTimer;
        printCurrentState();
    }

    /**
     * Updates the game settings based on the provided BattleshipGameSettings.
     * @param gameSettings The game settings to be updated.
     * 
     * @author Roney
     */
    public void updateGameSettings(BattleshipGameSettings gameSettings) {
        this.player1ShipColor = ShipColor.valueOf(gameSettings.getPlayer1ShipColor().toUpperCase());
        this.player2ShipColor = ShipColor.valueOf(gameSettings.getPlayer2ShipColor().toUpperCase());
        this.mapSize = MapSize.fromString(gameSettings.getMapSize());
        // Assuming shipSet is a List<String> representing the ship configurations
        this.shipSet = ShipSet.getShipList(gameSettings.getShipSet());
        this.shootingTimer = gameSettings.getShootingTimer();
        printCurrentState();
    }

    /**
     * Starts the game.
     * 
     * @author Roney
     */
    public void startGame() {
        // Implement game start logic here
        System.out.println("Starting the game...");
    }

    /**
     * Prints the current state of the model.
     * 
     * @author Roney
     */
    private void printCurrentState() {
        System.out.println("Current state of the model:");
        System.out.println("Player 1 Ship Color: " + player1ShipColor);
        System.out.println("Player 2 Ship Color: " + player2ShipColor);
        System.out.println("Map Size: " + mapSize);
        System.out.println("Ship Set: " + shipSet);
        System.out.println("Shooting Timer: " + shootingTimer);
        System.out.println();
    }
}