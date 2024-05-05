package battleshipMenuTest.modelTest;

import org.junit.jupiter.api.Test;

import battleshipMenu.model.ShipColor;

import static org.junit.jupiter.api.Assertions.*;

public class ShipColorTest {
    
    @Test
    public void testEnumValues() {
        assertEquals(5, ShipColor.values().length);
        assertEquals(ShipColor.GRAY, ShipColor.valueOf("GRAY"));
        assertEquals(ShipColor.GREEN, ShipColor.valueOf("GREEN"));
        assertEquals(ShipColor.PURPLE, ShipColor.valueOf("PURPLE"));
        assertEquals(ShipColor.ORANGE, ShipColor.valueOf("ORANGE"));
        assertEquals(ShipColor.YELLOW, ShipColor.valueOf("YELLOW"));
    }
}