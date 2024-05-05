package battleshipMenuTest.viewTest;

import battleshipMenu.model.BattleshipGameModel;
import battleshipMenu.model.BattleshipGameSettings;
import battleshipMenu.view.BattleshipPlacePhase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BattleshipPlacePhase class.
 * 
 * @author Roney
 */
public class BattleshipPlacePhaseTest {

    private BattleshipPlacePhase placePhase;

    @BeforeEach
    public void setUp() {
    	// Create a mock BattleshipGameModel for testing
        BattleshipGameModel model = new BattleshipGameModel(10,10);
        // Set up the BattleshipPlacePhase with mock data
        placePhase = new BattleshipPlacePhase(10, 10, "Massive", "Purple", "Green", "No Timer", model);
    }

    /**
     * Tests the initialization of the game board panel.
     */
    @Test
    public void testInitializeGameBoardPanel() {
        assertNotNull(placePhase.getGameBoardPanel(), "Game board panel should not be null");
    }

    /**
     * Tests the initialization of the "Done" button.
     */
    @Test
    public void testInitializeDoneButton() {
        assertNotNull(placePhase.getDoneButton(), "Done button should not be null");
    }

    /**
     * Tests the initialization of the explanation text area.
     */
    @Test
    public void testInitializeExplanationTextArea() {
        assertNotNull(placePhase.getExplanationTextArea(), "Explanation text area should not be null");
    }

    /**
     * Tests the initialization of the player label.
     */
    @Test
    public void testInitializePlayerLabel() {
        assertNotNull(placePhase.getPlayerLabel(), "Player label should not be null");
    }

    /**
     * Tests the initialization of the place label.
     */
    @Test
    public void testInitializePlaceLabel() {
        assertNotNull(placePhase.getPlaceLabel(), "Place label should not be null");
    }

    /**
     * Tests the initialization of the column labels.
     */
    @Test
    public void testInitializeColumnLabels() {
        assertNotNull(placePhase.getColumnLabels(), "Column labels should not be null");
        assertEquals(10, placePhase.getColumnLabels().length, "Number of column labels should be 10");
    }

    /**
     * Tests the initialization of the row labels.
     */
    @Test
    public void testInitializeRowLabels() {
        assertNotNull(placePhase.getRowLabels(), "Row labels should not be null");
        assertEquals(10, placePhase.getRowLabels().length, "Number of row labels should be 10");
    }

    /**
     * Tests the initialization of the cell buttons.
     */
    @Test
    public void testInitializeCellButtons() {
        assertNotNull(placePhase.getBoardCells(), "Cell buttons should not be null");
        assertEquals(10, placePhase.getBoardCells().length, "Number of rows of cell buttons should be 10");
        assertEquals(10, placePhase.getBoardCells()[0].length, "Number of columns of cell buttons should be 10");
    }

    /**
     * Tests the initialization of the rotate button.
     */
    @Test
    public void testInitializeRotateButton() {
        assertNotNull(placePhase.getRotateButton(), "Rotate button should not be null");
    }

    /**
     * Tests the initialization of the ship combo box.
     */
    @Test
    public void testInitializeShipComboBox() {
        assertNotNull(placePhase.getShipComboBox(), "Ship combo box should not be null");
    }

    /**
     * Tests the addition of ships to the combo box.
     */
    @Test
    public void testAddShipsToComboBox() {
        assertEquals(5, placePhase.getShipComboBox().getItemCount(), "Number of items in combo box should be 5");
    }

    /**
     * Tests the mapping of color names to Color objects.
     */
    @Test
    public void testMapColor() {
        Color color = placePhase.mapColor("Gray");
        assertNotNull(color, "Color object should not be null");
        assertEquals(Color.GRAY, color, "Color should match expected color");
    }
}