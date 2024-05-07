package battleshipMenuTest.modelTest;

import org.junit.jupiter.api.Test;

import battleshipMenu.model.ShipSet;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for the ShipSet class.
 * 
 * @author Roney
 */
public class ShipSetTest {
	/**
     * Test the getShipList method.
     * 
     * @author Roney
     */
    @Test
    public void testGetShipList() {
        List<String> stealth = ShipSet.getShipList("STEALTH");
        assertEquals(5, stealth.size());
        assertEquals("Cruiser (2x1)", stealth.get(0));
        assertEquals("Cruiser (2x1)", stealth.get(1));
        assertEquals("Cruiser (2x1)", stealth.get(2));
        assertEquals("Submarine (3x1)", stealth.get(3));
        assertEquals("Destroyer (3x1)", stealth.get(4));
        
        List<String> normal = ShipSet.getShipList("NORMAL");
        assertEquals(5, normal.size());
        assertEquals("Cruiser (2x1)", normal.get(0));
        assertEquals("Submarine (3x1)", normal.get(1));
        assertEquals("Destroyer (3x1)", normal.get(2));
        assertEquals("Battleship (4x1)", normal.get(3));
        assertEquals("AircraftCarrier (5x1)", normal.get(4));
        
        List<String> massive = ShipSet.getShipList("MASSIVE");
        assertEquals(5, massive.size());
        assertEquals("AircraftCarrier (5x1)", massive.get(0));
        assertEquals("AircraftCarrier (5x1)", massive.get(1));
        assertEquals("Battleship (4x1)", massive.get(2));
        assertEquals("Battleship (4x1)", massive.get(3));
        assertEquals("Submarine (3x1)", massive.get(4));
    }
    /**
     * Test the getShip method.
     * 
     * @author Roney
     */

    @Test
    public void testGetShip() {
        ShipSet.Ship cruiser = ShipSet.getShip("Cruiser (2x1)");
        assertEquals("Cruiser", cruiser.getName());
        assertEquals(2, cruiser.getSize());
        
        ShipSet.Ship submarine = ShipSet.getShip("Submarine (3x1)");
        assertEquals("Submarine", submarine.getName());
        assertEquals(3, submarine.getSize());
        
        ShipSet.Ship destroyer = ShipSet.getShip("Destroyer (3x1)");
        assertEquals("Destroyer", destroyer.getName());
        assertEquals(3, destroyer.getSize());
        
        ShipSet.Ship battleship = ShipSet.getShip("Battleship (4x1)");
        assertEquals("Battleship", battleship.getName());
        assertEquals(4, battleship.getSize());
        
        ShipSet.Ship aircraftCarrier = ShipSet.getShip("AircraftCarrier (5x1)");
        assertEquals("Aircraft Carrier", aircraftCarrier.getName());
        assertEquals(5, aircraftCarrier.getSize());
        
        assertNull(ShipSet.getShip("Invalid Ship"));
    }
}