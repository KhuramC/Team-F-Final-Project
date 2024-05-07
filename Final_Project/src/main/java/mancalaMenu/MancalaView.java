package mancalaMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A graphical user interface view for the Mancala game.
 */
public class MancalaView extends JPanel {
	private static final long serialVersionUID = 1L;
	private MancalaController controller;
	private JButton[] pitButtons;

	public JLabel leftStore;
	public JLabel rightStore;
	public JLabel textField;

	/**
	 * Constructs a new MancalaView with the given controller.
	 *
	 * @param controller The MancalaController instance to handle user interactions.
	 */
	public MancalaView(MancalaController controller) {
		this.controller = controller;
		initializeUI();
	}

	/**
	 * Initializes the user interface components.
	 */
	private void initializeUI() {
		setLayout(new BorderLayout(0, 0));

		leftStore = new JLabel("Left Mancala\n0");
		add(leftStore, BorderLayout.WEST);

		rightStore = new JLabel("Right Mancala\n0");
		add(rightStore, BorderLayout.EAST);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 6, 0, 0));

		textField = new JLabel();
		add(textField, BorderLayout.NORTH);
		textField.setHorizontalAlignment(SwingConstants.CENTER);

		pitButtons = new JButton[12]; // Initialize pitButtons array

		for (int i = 0; i < 6; i++) {
			JButton btnNewButton = new JButton("Pit " + (i + 1) + ": " + controller.getModel().getPitStones(2, i));
			panel.add(btnNewButton);
			pitButtons[i] = btnNewButton; // Add button to pitButtons array
			final int ind = i;
			btnNewButton.addActionListener(e -> controller.clickPit(ind));
		}

		for (int i = 5; i >= 0; i--) {
			JButton btnNewButton = new JButton("Pit " + (i + 7) + ": " + controller.getModel().getPitStones(1, i));
			panel.add(btnNewButton);
			pitButtons[i + 6] = btnNewButton; // Add button to pitButtons array
			final int ind = i + 6;
			btnNewButton.addActionListener(e -> controller.clickPit(ind));
		}
	}

	/**
	 * Updates the view to reflect the current game state.
	 */
	public void updateView() {
		// Update text on buttons
		for (int i = 5; i >= 0; i--) {
			pitButtons[i + 6].setText("Pit " + (i + 1) + ": " + controller.getModel().getPitStones(2, i));
		}
		for (int i = 0; i < 6; i++) {
			pitButtons[i].setText("Pit " + (i + 7) + ": " + controller.getModel().getPitStones(1, i));
		}

		// Update stores
		leftStore.setText("Left Mancala\n" + controller.getModel().getStoreP2());
		rightStore.setText("Right Mancala\n" + controller.getModel().getStoreP1());

		// Say who's turn it is.
		String turnText = controller.getModel().getCurrentPlayer() == 1 ? "Player 1's Turn." : "Player 2's Turn.";
		if (controller.getModel().isHasAnotherTurn()) {
			turnText = turnText + " Landed in your Store. Another Turn!";
		}
		textField.setText(turnText);

		revalidate(); // Re-layout the components
		repaint(); // Repaint the components
	}

	/**
	 * Returns the array of pit buttons.
	 *
	 * @return The array of JButton objects representing the pits.
	 */
	public JButton[] getPitButtons() {
		return pitButtons;
	}
}