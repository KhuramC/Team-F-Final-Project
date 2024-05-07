package mancalaMenu;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The MancalaController class manages the game logic and user interactions for
 * the Mancala game. It communicates with the MancalaModel and MancalaView to
 * update the game state and display.
 */
public class MancalaController {
	private MancalaModel model;
	private MancalaView view;

	/**
	 * Constructs a new MancalaController with the specified model.
	 *
	 * @param model The MancalaModel instance representing the game state.
	 */
	public MancalaController(MancalaModel model) {
		this.model = model;
	}

	/**
	 * Sets the view associated with this controller.
	 *
	 * @param view The MancalaView instance to be set.
	 */
	public void setView(MancalaView view) {
		this.view = view;
		this.view.updateView(); // Initial update of the view
	}

	/**
	 * Retrieves the MancalaModel associated with this controller.
	 *
	 * @return The MancalaModel instance.
	 */
	public MancalaModel getModel() {
		return model;
	}

	/**
	 * Handles a player's move when a pit is clicked.
	 *
	 * @param pitIndex The index of the clicked pit (0-5).
	 */
	public void clickPit(int pitIndex) {
		// Ensure that the move is legal
		if (!model.moveStone(pitIndex)) {
			handleIllegalMove();
		}

		// Check if the game is complete
		model.checkWin();
		if (model.getGameState() == MancalaModel.STATE.COMPLETE) {
			handleGameWon();
		}

		// Update the view
		view.updateView();
	}

	/**
	 * Displays a warning message for an illegal move.
	 */
	private void handleIllegalMove() {
		JOptionPane.showMessageDialog(null, "Illegal Move. Choose another pit!", "Illegal Move",
				JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Displays a message indicating the winner of the game.
	 */
	private void handleGameWon() {
		int win = model.getWin();
		switch (win) {
		case 1:
			JOptionPane.showMessageDialog(view, "Player 1 Wins!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(view, "Player 2 Wins!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(view, "It's a Tie!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
		default:
			break;
		}
	}

	/**
	 * Starts the Mancala game by creating and displaying the game window.
	 */
	public void startGame() {
		JFrame frame = new JFrame("Mancala");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(view, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}