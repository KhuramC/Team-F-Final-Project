package battleshipMenuTest.modelTest;

import org.junit.jupiter.api.Test;

import battleshipMenu.model.MapSize;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Tests for the MapSize enum.
 * 
 * @author Roney
 */
public class MapSizeTest {
	/**
     * Tests the enum values of MapSize.
     * 
     * @author Roney
     */
    @Test
    public void testEnumValues() {
        assertEquals(3, MapSize.values().length);
        assertEquals(MapSize.SMALL_7x7, MapSize.valueOf("SMALL_7x7"));
        assertEquals(MapSize.NORMAL_10x10, MapSize.valueOf("NORMAL_10x10"));
        assertEquals(MapSize.LARGE_13x13, MapSize.valueOf("LARGE_13x13"));
    }
    /**
     * Tests the fromString method of MapSize.
     * 
     * @author Roney
     */
    @Test
    public void testFromString() {
        assertEquals(MapSize.SMALL_7x7, MapSize.fromString("Small (7x7)"));
        assertEquals(MapSize.NORMAL_10x10, MapSize.fromString("Normal (10x10)"));
        assertEquals(MapSize.LARGE_13x13, MapSize.fromString("Large (13x13)"));
        
        assertThrows(IllegalArgumentException.class, () -> MapSize.fromString("Invalid Size"));
    }
}