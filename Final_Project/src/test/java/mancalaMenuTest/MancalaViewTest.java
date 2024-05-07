package mancalaMenuTest;

import mancalaMenu.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.JButton;
import javax.swing.JLabel;

class MancalaViewTest {

    private MancalaView view;
    private MancalaController controller;
    private MancalaModel model;

    @BeforeEach
    void setUp() {
        model = new MancalaModel();
        controller = new MancalaController(model);
        view = new MancalaView(controller);
        controller.setView(view);
    }

    @Test
    void testInitializeUI() {
        assertNotNull(view.leftStore);
        assertNotNull(view.rightStore);
        assertNotNull(view.textField);
        assertNotNull(view.getPitButtons());
        assertEquals(12, view.getPitButtons().length);
    }

    @Test
    void testUpdateView() {
        // Simulate a move
        model.moveStone(0);

        view.updateView();

        // Check if the pit buttons are updated
        for (int i = 0; i < 6; i++) {
            JButton button = view.getPitButtons()[i];
            assertEquals("Pit " + (i + 7) + ": " + model.getPitStones(1, i), button.getText());
        }

        for (int i = 6; i < 12; i++) {
            JButton button = view.getPitButtons()[i];
            assertEquals("Pit " + (i - 5) + ": " + model.getPitStones(2, i - 6), button.getText());
        }

        // Check if the store labels are updated
        assertEquals("Left Mancala\n" + model.getStoreP2(), view.leftStore.getText());
        assertEquals("Right Mancala\n" + model.getStoreP1(), view.rightStore.getText());

        // Check if the turn text is updated
        String expectedTurnText = model.getCurrentPlayer() == 1 ? "Player 1's Turn." : "Player 2's Turn.";
        if (model.isHasAnotherTurn()) {
            expectedTurnText += " Landed in your Store. Another Turn!";
        }
        assertEquals(expectedTurnText, view.textField.getText());
    }

    @Test
    void testGetPitButtons() {
        JButton[] buttons = view.getPitButtons();
        assertNotNull(buttons);
        assertEquals(12, buttons.length);
    }
}