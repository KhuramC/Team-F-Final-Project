package battleshipMenuTest.modelTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import battleshipMenu.model.BattleshipGameSettings;
import battleshipMenu.model.BattleshipMenuModel;
import battleshipMenu.model.MapSize;
import battleshipMenu.model.ShipColor;
import battleshipMenu.model.ShipSet;

/**
 * JUnit test cases for the BattleshipMenuModel class.
 * @author Roney
 */
public class BattleshipMenuModelTest {
    
    private BattleshipMenuModel model;
    
    @BeforeEach
    public void setUp() {
        model = new BattleshipMenuModel();
    }
    /**
     * Test case for default values of the BattleshipMenuModel.
     * @author Roney
     */
    @Test
    public void testDefaultValues() {
        assertEquals(ShipColor.GREEN, model.getPlayer1ShipColor());
        assertEquals(ShipColor.GREEN, model.getPlayer2ShipColor());
        assertEquals(MapSize.SMALL_7x7, model.getMapSize());
        assertEquals(ShipSet.STEALTH, model.getShipSet());
        assertEquals("No Timer", model.getShootingTimer());
    }
    /**
     * Test case for setting player 1 ship color in the BattleshipMenuModel. 
     * @author Roney
     */
    @Test
    public void testSetPlayer1ShipColor() {
        model.setPlayer1ShipColor(ShipColor.GRAY);
        assertEquals(ShipColor.GRAY, model.getPlayer1ShipColor());
    }
    /**
     * Test case for setting player 2 ship color in the BattleshipMenuModel. 
     * @author Roney
     */
    @Test
    public void testSetPlayer2ShipColor() {
        model.setPlayer2ShipColor(ShipColor.ORANGE);
        assertEquals(ShipColor.ORANGE, model.getPlayer2ShipColor());
    }
    /**
     * Test case for setting map size in the BattleshipMenuModel. 
     * @author Roney
     */
    @Test
    public void testSetMapSize() {
        model.setMapSize(MapSize.NORMAL_10x10);
        assertEquals(MapSize.NORMAL_10x10, model.getMapSize());
    }
    /**
     * Test case for setting ship set in the BattleshipMenuModel. 
     * @author Roney
     */
    @Test
    public void testSetShipSet() {
        model.setShipSet(ShipSet.NORMAL);
        assertEquals(ShipSet.NORMAL, model.getShipSet());
    }
    /**
     * Test case for setting shooting timer in the BattleshipMenuModel. 
     * @author Roney
     */
    @Test
    public void testSetShootingTimer() {
        model.setShootingTimer("5 Minutes");
        assertEquals("5 Minutes", model.getShootingTimer());
    }
    /**
     * Test case for updating game settings in the BattleshipMenuModel. 
     * It validates if the update is reflected correctly. 
     * This test ensures the updateGameSettings method works as expected. 
     * It verifies if the settings are updated properly after applying the changes.
     * 
     * @author Roney
     */
    @Test
    public void testUpdateGameSettings() {
        BattleshipGameSettings gameSettings = new BattleshipGameSettings("Purple", "Yellow", "Normal (10x10)", "Normal", "5 Minutes");
        model.updateGameSettings(gameSettings);
        
        assertEquals(ShipColor.PURPLE, model.getPlayer1ShipColor());
        assertEquals(ShipColor.YELLOW, model.getPlayer2ShipColor());
        assertEquals(MapSize.NORMAL_10x10, model.getMapSize());
        assertEquals(ShipSet.NORMAL, model.getShipSet());
        assertEquals("5 Minutes", model.getShootingTimer());
    }
    /**
     * Test case for starting game in BattleshipMenuModel
     * 
     * @author Roney
     */
    @Test
    public void testStartGame() {
        // This test doesn't validate the game start logic, just that the method executes without error
        assertDoesNotThrow(() -> model.startGame());
    }
}