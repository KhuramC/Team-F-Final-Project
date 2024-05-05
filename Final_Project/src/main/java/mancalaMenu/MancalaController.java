package mancalaMenu;


import java.awt.BorderLayout;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MancalaController {
	
	private MancalaModel model;
	private MancalaView gameView;
	
    private Scanner scanner;
	/**
	 * Default constructor for controller.
	 * 
	 * @author Hipolito S.
	 */
    public MancalaController(MancalaModel m) {
        this.model = m;
        this.gameView = new MancalaView(m);
        scanner = new Scanner(System.in);
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
//          gameView.update(model);
        	gameView.updateView(model.getPits());
        } else {
        	JOptionPane.showMessageDialog(this.gameView, "Invalid Move!");
        }
    }
    
    private void displayBoard() {
        int[] pits = model.getPits();
        System.out.println("  ----------------------------------");
        System.out.println("P1 |    | " + pits[0] + " | " + pits[1] + " | " + pits[2] + " | " + pits[3] + " | " + pits[4] + " | " + pits[5] + " |    |");
        System.out.println("   | " + pits[13] + " |-----------------------| " + pits[6] + " |");
        System.out.println("P2 |    | " + pits[12] + " | " + pits[11] + " | " + pits[10] + " | " + pits[9] + " | " + pits[8] + " | " + pits[7] + " |    |");
        System.out.println("  ----------------------------------");
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
        rungame();
    }

	private void rungame() {
        while (model.getGameState() != MancalaModel.STATE.COMPLETE) {
            displayBoard();
            System.out.println("Enter the index of the pit to move stones from: ");
            int pitIndex = scanner.nextInt();
            boolean isValidMove = model.moveStones(pitIndex);
            if (!isValidMove) {
                System.out.println("Invalid Move!");
            }
        }
        displayBoard();
        System.out.println("Game Over!");
		
	}
}
