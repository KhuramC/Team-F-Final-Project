package battleshipMenu.model;

import java.util.List;

import battleshipMenu.control.*;
import battleshipMenu.view.*;

public class BattleshipMenuModel {
    
    private ShipColor player1ShipColor;
    private ShipColor player2ShipColor;
    private MapSize mapSize;
    private List<String> shipSet;
    private String shootingTimer;

    public BattleshipMenuModel() {
        // Set default values
        player1ShipColor = ShipColor.GREEN;
        player2ShipColor = ShipColor.GREEN; // Default to the same color for both players
        mapSize = MapSize.SMALL_7x7;
        shipSet = ShipSet.STEALTH;
        shootingTimer = "No Timer";
    }

    public ShipColor getPlayer1ShipColor() {
        return player1ShipColor;
    }

    public void setPlayer1ShipColor(ShipColor player1ShipColor) {
        this.player1ShipColor = player1ShipColor;
        printCurrentState();
    }

    public ShipColor getPlayer2ShipColor() {
        return player2ShipColor;
    }

    public void setPlayer2ShipColor(ShipColor player2ShipColor) {
        this.player2ShipColor = player2ShipColor;
        printCurrentState();
    }

    public MapSize getMapSize() {
        return mapSize;
    }

    public void setMapSize(MapSize mapSize) {
        this.mapSize = mapSize;
        printCurrentState();
    }

    public List<String> getShipSet() {
        return shipSet;
    }

    public void setShipSet(List<String> shipSet) {
        this.shipSet = shipSet;
        printCurrentState();
    }

    public String getShootingTimer() {
        return shootingTimer;
    }

    public void setShootingTimer(String shootingTimer) {
        this.shootingTimer = shootingTimer;
        printCurrentState();
    }

    // Method to update game settings
    public void updateGameSettings(BattleshipGameSettings gameSettings) {
        this.player1ShipColor = ShipColor.valueOf(gameSettings.getPlayer1ShipColor().toUpperCase());
        this.player2ShipColor = ShipColor.valueOf(gameSettings.getPlayer2ShipColor().toUpperCase());
        this.mapSize = MapSize.fromString(gameSettings.getMapSize());
        // Assuming shipSet is a List<String> representing the ship configurations
        this.shipSet = ShipSet.getShipList(gameSettings.getShipSet());
        this.shootingTimer = gameSettings.getShootingTimer();
        printCurrentState();
    }

    // Method to start the game
    public void startGame() {
        // Implement game start logic here
        System.out.println("Starting the game...");
    }

    // Method to print the current state of the model
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