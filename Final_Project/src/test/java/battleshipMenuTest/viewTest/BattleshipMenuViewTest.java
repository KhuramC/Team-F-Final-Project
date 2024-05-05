package battleshipMenuTest.viewTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import battleshipMenu.control.BattleshipMenuController;
import battleshipMenu.model.BattleshipMenuModel;
import battleshipMenu.view.BattleshipMenuView;

/**
 * Unit tests for BattleshipMenuView.
 * 
 * @author Roney
 */
public class BattleshipMenuViewTest {

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
     * Tests the addStartGameButtonListener method.
     * 
     * @see BattleshipMenuView#addStartGameButtonListener(ActionListener)
     */
    @Test
    public void testAddStartGameButtonListener() {
        // Simulate adding a listener
        view.addStartGameButtonListener(controller.new StartGameButtonListener());

        // Verify that the listener is added
        assertNotNull(view.startGameButton.getActionListeners(), "Listener should be added to the start game button");
    }

    /**
     * Tests the getPlayer1Color method.
     * 
     * @see BattleshipMenuView#getPlayer1Color()
     */
    @Test
    public void testGetPlayer1Color() {
        
    	
    	// Set player 1 color
        String color = "Green";
        view.getP1ColorComboBox().setSelectedItem(color);

        // Verify that the correct player 1 color is retrieved
        assertEquals(color, view.getPlayer1Color(), "Player 1 color should match the selected color");
    }

    /**
     * Tests the getPlayer2Color method.
     * 
     * @see BattleshipMenuView#getPlayer2Color()
     */
    @Test
    public void testGetPlayer2Color() {
        // Set player 2 color
        String color = "Yellow";
        view.getP2ColorComboBox().setSelectedItem(color);

        // Verify that the correct player 2 color is retrieved
        assertEquals(color, view.getPlayer2Color(), "Player 2 color should match the selected color");
    }

    /**
     * Tests the getSelectedBoardSize method.
     * 
     * @see BattleshipMenuView#getSelectedBoardSize()
     */
    @Test
    public void testGetSelectedBoardSize() {
        // Set selected board size
        String size = "Normal (10x10)";
        view.getSelectBoardSizeComboBox().setSelectedItem(size);

        // Verify that the correct board size is retrieved
        assertEquals(size, view.getSelectedBoardSize(), "Selected board size should match the selected item");
    }

    /**
     * Tests the getSelectedTimer method.
     * 
     * @see BattleshipMenuView#getSelectedTimer()
     */
    @Test
    public void testGetSelectedTimer() {
        // Set selected timer
        String timer = "30 sec";
        view.getSelectTimerComboBox().setSelectedItem(timer);

        // Verify that the correct timer option is retrieved
        assertEquals(timer, view.getSelectedTimer(), "Selected timer option should match the selected item");
    }

    /**
     * Tests the getSelectedShipSet method.
     * 
     * @see BattleshipMenuView#getSelectedShipSet()
     */
    @Test
    public void testGetSelectedShipSet() {
        // Set selected ship set
        String shipSet = "Normal";
        view.getSelectShipSetComboBox().setSelectedItem(shipSet);

        // Verify that the correct ship set is retrieved
        assertEquals(shipSet, view.getSelectedShipSet(), "Selected ship set should match the selected item");
    }
}