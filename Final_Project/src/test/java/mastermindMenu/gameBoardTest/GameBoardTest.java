package mastermindMenu.gameBoardTest;
import org.junit.Before;
import org.junit.Test;

import mastermindMenu.gameBoard.GameBoard;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameBoardTest {
	private GameBoard gameBoard;
    private ActionListener colorListener;
    private ActionListener submitListener;

    @Before
    public void setUp() {
        colorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do nothing
            }
        };
        submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Do nothing
            }
        };
        gameBoard = new GameBoard(5, 4, colorListener, submitListener);
    }

    @Test
    public void testConstructor() {
        assertNotNull(gameBoard);
        assertEquals(5, gameBoard.getComponentCount() / 5);
    }

    @Test
    public void testInitializeButtons() {
        JButton[][] buttons = gameBoard.getGuessButtons();
        assertEquals(5, buttons.length);
        assertEquals(4, buttons[0].length);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = buttons[i][j];
                assertNotNull(button);
                assertEquals(Color.WHITE, button.getBackground());
                assertEquals(new Dimension(50, 50), button.getPreferredSize());
                assertTrue(button.isEnabled() == (i == 0));
            }
            JButton submitButton = (JButton) gameBoard.getComponent(i * 5 + 4);
            assertNotNull(submitButton);
            assertEquals("Submit", submitButton.getText());
            assertEquals(String.valueOf(i), submitButton.getActionCommand());
        }
    }

    @Test
    public void testResetBoard() {
        gameBoard.resetBoard();
        JButton[][] buttons = gameBoard.getGuessButtons();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                JButton button = buttons[i][j];
                assertEquals(Color.WHITE, button.getBackground());
                assertTrue(button.isEnabled() == (i == 0));
            }
        }
    }

    @Test
    public void testSetRowEnabled() {
        gameBoard.setRowEnabled(2, true);
        JButton[][] buttons = gameBoard.getGuessButtons();
        for (int j = 0; j < 4; j++) {
            JButton button = buttons[2][j];
            assertTrue(button.isEnabled());
        }
        gameBoard.setRowEnabled(2, false);
        for (int j = 0; j < 4; j++) {
            JButton button = buttons[2][j];
            assertFalse(button.isEnabled());
        }
    }
}