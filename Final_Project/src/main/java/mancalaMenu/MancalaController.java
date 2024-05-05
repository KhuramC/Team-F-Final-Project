package mancalaMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mancalaMenu.MancalaModel;
import mancalaMenu.MancalaView;


public class MancalaController {
	
	private MancalaModel model;
	private MancalaView gameView;
	
	/**
	 * Default constructor for controller.
	 * 
	 * @author Hipolito S.
	 */
    public MancalaController(MancalaModel m) {
        this.model = m;
//        this.gameView = new MancalaView(this.model);
    }

	/**
     * Attempts to make a move on the board for the current player.
     * 
     * @param pitIndex The index of the pit the current player wants to move stones from.
     * @return True if the move was successful, false otherwise.
     */
    public void makeMove(int pitIndex) {
        if (model.moveStones(pitIndex)) {
          // Move successful, update view
          gameView.update(model);
        } else {
        	
        }
    }
    
    /**
     * 'Starts' the application by making the view visible to the user.
     * 
     * 
     */
    public void startGame() {
        JFrame frame = new JFrame("Mancala");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.add(gameView, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
