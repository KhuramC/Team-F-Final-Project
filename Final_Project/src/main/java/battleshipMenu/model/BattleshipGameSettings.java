package battleshipMenu.model;

/**
 * Class representing the settings for a Battleship game.
 * @author Roney
 */
public class BattleshipGameSettings {
    private String player1ShipColor;
    private String player2ShipColor;
    private String mapSize;
    private String shipSet;
    private String shootingTimer;

    /**
     * Constructor for BattleshipGameSettings.
     * @param player1ShipColor The color of Player 1's ships.
     * @param player2ShipColor The color of Player 2's ships.
     * @param mapSize The size of the game map.
     * @param shipSet The set of ships to be used in the game.
     * @param shootingTimer The timer setting for shooting phase.
     * 
     * @author Roney
     */
    public BattleshipGameSettings(String player1ShipColor, String player2ShipColor, String mapSize, String shipSet, String shootingTimer) {
        this.player1ShipColor = player1ShipColor;
        this.player2ShipColor = player2ShipColor;
        this.mapSize = mapSize;
        this.shipSet = shipSet;
        this.shootingTimer = shootingTimer;
    }

    /**
     * Gets the color of Player 1's ships.
     * @return The color of Player 1's ships.
     * 
     * @author Roney
     */
    public String getPlayer1ShipColor() {
        return player1ShipColor;
    }

    /**
     * Gets the color of Player 2's ships.
     * @return The color of Player 2's ships.
     * 
     * @author Roney
     */
    public String getPlayer2ShipColor() {
        return player2ShipColor;
    }

    /**
     * Gets the size of the game map.
     * @return The size of the game map.
     * 
     * @author Roney
     */
    public String getMapSize() {
        return mapSize;
    }

    /**
     * Gets the set of ships to be used in the game.
     * @return The set of ships to be used in the game.
     * 
     * @author Roney
     */
    public String getShipSet() {
        return shipSet;
    }

    /**
     * Gets the timer setting for shooting phase.
     * @return The timer setting for shooting phase.
     * 
     * @author Roney
     */
    public String getShootingTimer() {
        return shootingTimer;
    }
}