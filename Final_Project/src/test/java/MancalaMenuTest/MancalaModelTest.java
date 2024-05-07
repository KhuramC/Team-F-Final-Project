package mancalaMenu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mancalaMenu.*;

import org.junit.Before;
import org.junit.Test;

public class MancalaModelTest {

    private MancalaModel mancalaModel;

    @Before
    public void setUp() {
        mancalaModel = new MancalaModel();
    }

    @Test
    public void testInitialGameState() {
        assertEquals(MancalaModel.STATE.INPLAY, mancalaModel.getGameState());
        assertEquals(1, mancalaModel.getCurrentPlayer());
    }

    @Test
    public void testMoveStoneValidMove() {
        assertTrue(mancalaModel.moveStone(0));
        assertEquals(0, mancalaModel.getP1()[0]);
        assertEquals(5, mancalaModel.getP1()[1]);
        assertEquals(5, mancalaModel.getStoreP1());
        assertEquals(1, mancalaModel.getCurrentPlayer());
    }

    @Test
    public void testMoveStoneInvalidMove() {
        assertFalse(mancalaModel.moveStone(10)); // Invalid pit number
        assertFalse(mancalaModel.moveStone(-1)); // Invalid pit number
    }

    // Add more test cases as needed...

}
