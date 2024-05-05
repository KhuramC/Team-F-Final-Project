package battleshipMenuTest.modelTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import battleshipMenu.model.BattleshipGameModel;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BattleshipGameModel.
 * 
 * @author Roney
 */
class BattleshipGameModelTest {

    private BattleshipGameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = new BattleshipGameModel(10, 10);
    }

    /**
     * Tests the updatePlayer1GameBoardState method. erifying that it correctly updates player 1's game board state
 * with the provided ship placements.
     */
    @Test
    void testUpdatePlayer1GameBoardState() {
        Set<Point> placedShips = new HashSet<>();
        placedShips.add(new Point(0, 0));
        placedShips.add(new Point(1, 1));
        gameModel.updatePlayer1GameBoardState(placedShips);
        assertEquals("O", gameModel.getPlayer1GameBoardState()[0][0]);
        assertEquals("O", gameModel.getPlayer1GameBoardState()[1][1]);
    }
 // Test case for savePlayer1GameBoardState()
    @Test
    public void testSavePlayer1GameBoardState() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10);
        gameModel.savePlayer1GameBoardState();
        assertTrue(gameModel.isPlayer1GameBoardStateSaved());
    }

    // Test case for isPlayer1GameBoardStateSaved() when not saved
    @Test
    public void testIsPlayer1GameBoardStateSaved_NotSaved() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10);
        assertFalse(gameModel.isPlayer1GameBoardStateSaved());
    }

    // Test case for isPlayer1GameBoardStateSaved() when saved
    @Test
    public void testIsPlayer1GameBoardStateSaved_Saved() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10);
        gameModel.savePlayer1GameBoardState();
        assertTrue(gameModel.isPlayer1GameBoardStateSaved());
    }
    /**
     * Tests the updatePlayer2GameBoardState method.
     */
    @Test
    void testUpdatePlayer2GameBoardState() {
        Set<Point> placedShips = new HashSet<>();
        placedShips.add(new Point(3, 4));
        placedShips.add(new Point(5, 6));
        gameModel.updatePlayer2GameBoardState(placedShips);
        assertEquals("O", gameModel.getPlayer2GameBoardState()[3][4]);
        assertEquals("O", gameModel.getPlayer2GameBoardState()[5][6]);
    }
 // Test case for savePlayer1GameBoardState()
    @Test
    public void testSavePlayer2GameBoardState() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10);
        gameModel.savePlayer2GameBoardState();
        assertTrue(gameModel.isPlayer2GameBoardStateSaved());
    }

    // Test case for isPlayer1GameBoardStateSaved() when not saved
    @Test
    public void testIsPlayer2GameBoardStateSaved_NotSaved() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10);
        assertFalse(gameModel.isPlayer2GameBoardStateSaved());
    }

    // Test case for isPlayer1GameBoardStateSaved() when saved
    @Test
    public void testIsPlayer2GameBoardStateSaved_Saved() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10);
        gameModel.savePlayer1GameBoardState();
        assertTrue(gameModel.isPlayer2GameBoardStateSaved());
    }
    /**
     * Tests the updateShipsPlacedCount method.
     */
    @Test
    public void testUpdateShipsPlacedCount() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10); // Assuming 10x10 game board

        // Initially, no ships should be placed for player 1
        assertEquals(0, gameModel.getShipsPlacedCount());

        // Update the count of ships placed for player 1
        gameModel.updateShipsPlacedCount();

        // After updating, one ship should be placed for player 1
        assertEquals(1, gameModel.getShipsPlacedCount());
    }

    /**
     * Tests the getShipsPlacedCount method.
     */
    @Test
    public void testGetShipsPlacedCount() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10); // Assuming 10x10 game board

        // Initially, no ships should be placed for player 1
        assertEquals(0, gameModel.getShipsPlacedCount());
    }

    /**
     * Tests the updateShipsPlacedCountP2 method.
     */
    @Test
    public void testUpdateShipsPlacedCountP2() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10); // Assuming 10x10 game board

        // Initially, no ships should be placed for player 2
        assertEquals(0, gameModel.getShipsPlacedCountP2());

        // Update the count of ships placed for player 2
        gameModel.updateShipsPlacedCountP2();

        // After updating, one ship should be placed for player 2
        assertEquals(1, gameModel.getShipsPlacedCountP2());
    }

    /**
     * Tests the getShipsPlacedCountP2 method.
     */
    @Test
    public void testGetShipsPlacedCountP2() {
        BattleshipGameModel gameModel = new BattleshipGameModel(10, 10); // Assuming 10x10 game board

        // Initially, no ships should be placed for player 2
        assertEquals(0, gameModel.getShipsPlacedCountP2());
    }
    /**
     * Tests the isValidPlacement method.
     */
    @Test
    void testIsValidPlacement() {
        assertTrue(gameModel.isValidPlacement(0, 0, 3, true, 10, 10));
        assertTrue(gameModel.isValidPlacement(0, 0, 3, false, 10, 10));
        assertFalse(gameModel.isValidPlacement(8, 8, 3, true, 10, 10));
        assertFalse(gameModel.isValidPlacement(8, 8, 3, false, 10, 10));
    }

    /**
     * Tests the isValidPlacementP2 method.
     */
    @Test
    void testIsValidPlacementP2() {
        assertTrue(gameModel.isValidPlacementP2(0, 0, 3, true, 10, 10));
        assertTrue(gameModel.isValidPlacementP2(0, 0, 3, false, 10, 10));
        assertFalse(gameModel.isValidPlacementP2(8, 8, 3, true, 10, 10));
        assertFalse(gameModel.isValidPlacementP2(8, 8, 3, false, 10, 10));
    }

    /**
     * Tests the mapColor method.
     */
    @Test
    void testMapColor() {
        assertEquals("GREEN", gameModel.mapColor("Green").toString());
        assertEquals("YELLOW", gameModel.mapColor("Yellow").toString());
        assertEquals("java.awt.Color[r=128,g=0,b=128]", gameModel.mapColor("Purple").toString());
        assertEquals("ORANGE", gameModel.mapColor("Orange").toString());
        assertEquals("GRAY", gameModel.mapColor("Gray").toString());
        assertEquals("java.awt.Color[r=255,g=255,b=255]", gameModel.mapColor("Invalid").toString());
    };
    /**
     * Tests the getPlayer1GameBoardState method.
     */
    @Test
    public void testGetPlayer1GameBoardState() {
        int numRows = 10;
        int numCols = 10;
        BattleshipGameModel gameModel = new BattleshipGameModel(numRows, numCols); // Assuming 10x10 game board

        // Get player 1's game board state
        String[][] player1GameBoardState = gameModel.getPlayer1GameBoardState();

        // Verify dimensions
        assertEquals(numRows, player1GameBoardState.length);
        assertEquals(numCols, player1GameBoardState[0].length);

        // Verify initial state (all cells should be "~")
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                assertEquals("~", player1GameBoardState[i][j]);
            }
        }
    }

    /**
     * Tests the getPlayer2GameBoardState method.
     */
    @Test
    public void testGetPlayer2GameBoardState() {
        int numRows = 10;
        int numCols = 10;
        BattleshipGameModel gameModel = new BattleshipGameModel(numRows, numCols); // Assuming 10x10 game board

        // Get player 2's game board state
        String[][] player2GameBoardState = gameModel.getPlayer2GameBoardState();

        // Verify dimensions
        assertEquals(numRows, player2GameBoardState.length);
        assertEquals(numCols, player2GameBoardState[0].length);

        // Verify initial state (all cells should be "~")
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                assertEquals("~", player2GameBoardState[i][j]);
            }
        }
    }
}