package battleshipMenuTest.modelTest;

import org.junit.Test;

import battleshipMenu.model.BattleshipGameSettings;

import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test cases for the BattleshipGameSettings class.
 */
public class BattleshipGameSettingsTest {

    /**
     * Tests the getPlayer1ShipColor method.
     */
    @Test
    public void testGetPlayer1ShipColor() {
        String player1ShipColor = "Red";
        String player2ShipColor = "Blue";
        String mapSize = "Large";
        String shipSet = "Standard";
        String shootingTimer = "60s";

        BattleshipGameSettings settings = new BattleshipGameSettings(player1ShipColor, player2ShipColor, mapSize, shipSet, shootingTimer);

        assertEquals(player1ShipColor, settings.getPlayer1ShipColor());
    }

    /**
     * Tests the getPlayer2ShipColor method.
     */
    @Test
    public void testGetPlayer2ShipColor() {
        String player1ShipColor = "Red";
        String player2ShipColor = "Blue";
        String mapSize = "Large";
        String shipSet = "Standard";
        String shootingTimer = "60s";

        BattleshipGameSettings settings = new BattleshipGameSettings(player1ShipColor, player2ShipColor, mapSize, shipSet, shootingTimer);

        assertEquals(player2ShipColor, settings.getPlayer2ShipColor());
    }

    /**
     * Tests the getMapSize method.
     */
    @Test
    public void testGetMapSize() {
        String player1ShipColor = "Red";
        String player2ShipColor = "Blue";
        String mapSize = "Large";
        String shipSet = "Standard";
        String shootingTimer = "60s";

        BattleshipGameSettings settings = new BattleshipGameSettings(player1ShipColor, player2ShipColor, mapSize, shipSet, shootingTimer);

        assertEquals(mapSize, settings.getMapSize());
    }

    /**
     * Tests the getShipSet method.
     */
    @Test
    public void testGetShipSet() {
        String player1ShipColor = "Red";
        String player2ShipColor = "Blue";
        String mapSize = "Large";
        String shipSet = "Standard";
        String shootingTimer = "60s";

        BattleshipGameSettings settings = new BattleshipGameSettings(player1ShipColor, player2ShipColor, mapSize, shipSet, shootingTimer);

        assertEquals(shipSet, settings.getShipSet());
    }

    /**
     * Tests the getShootingTimer method.
     */
    @Test
    public void testGetShootingTimer() {
        String player1ShipColor = "Red";
        String player2ShipColor = "Blue";
        String mapSize = "Large";
        String shipSet = "Standard";
        String shootingTimer = "60s";

        BattleshipGameSettings settings = new BattleshipGameSettings(player1ShipColor, player2ShipColor, mapSize, shipSet, shootingTimer);

        assertEquals(shootingTimer, settings.getShootingTimer());
    }
}