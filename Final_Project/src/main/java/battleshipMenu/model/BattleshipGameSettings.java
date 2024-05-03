package battleshipMenu.model;

public class BattleshipGameSettings {
    private String player1ShipColor;
    private String player2ShipColor;
    private String mapSize;
    private String shipSet;
    private String shootingTimer;

    public BattleshipGameSettings(String player1ShipColor, String player2ShipColor, String mapSize, String shipSet, String shootingTimer) {
        this.player1ShipColor = player1ShipColor;
        this.player2ShipColor = player2ShipColor;
        this.mapSize = mapSize;
        this.shipSet = shipSet;
        this.shootingTimer = shootingTimer;
    }

    public String getPlayer1ShipColor() {
        return player1ShipColor;
    }

    public String getPlayer2ShipColor() {
        return player2ShipColor;
    }

    public String getMapSize() {
        return mapSize;
    }

    public String getShipSet() {
        return shipSet;
    }

    public String getShootingTimer() {
        return shootingTimer;
    }

}