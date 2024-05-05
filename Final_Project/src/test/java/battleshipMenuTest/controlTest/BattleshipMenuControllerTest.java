package battleshipMenuTest.controlTest;

import battleshipMenu.control.BattleshipMenuController;
import battleshipMenu.model.BattleshipGameModel;
import battleshipMenu.model.BattleshipMenuModel;
import battleshipMenu.view.BattleshipMenuView;
import battleshipMenu.view.BattleshipPlacePhase;
import battleshipMenuTest.viewTest.BattleshipPlacePhaseTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BattleshipMenuController.
 * 
 * @author Roney
 */
public class BattleshipMenuControllerTest {

    private BattleshipMenuModel model;
    private BattleshipMenuView view;
    private BattleshipMenuController controller;

    @BeforeEach
    public void setUp() {
        model = new BattleshipMenuModel();
        view = new BattleshipMenuView();
        controller = new BattleshipMenuController(model, view);
    }

    /**
     * Tests the initiate method.
     *
     */
    @Test
    public void testInitiate() {
        controller.initiate();
        assertTrue(view.isVisible(), "View should be visible after initiation");
    }

    /**
     * Tests the StartGameButtonListener subclass's actionPerformed method.
     * Verifies that the game settings are updated and the BattleshipPlacePhaseView is opened.
     * 
     */
    @Test
    public void testStartGameButtonListenerActionPerformed() {
        BattleshipMenuController.StartGameButtonListener listener = controller.new StartGameButtonListener();

        // Simulate button click event
        listener.actionPerformed(null);

        // Verify that the BattleshipPlacePhase is opened
        assertNotNull(new BattleshipPlacePhase(10, 10,  "Massive", "Purple",  "Green", "No Timer", new BattleshipGameModel(10, 10)), "BattleshipPlacePhase should be opened");
    }
}