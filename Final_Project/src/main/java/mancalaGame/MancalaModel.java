package mancalaGame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Representation of the Mancala game board and number for each pit
 * 
 *    ------------------------------
 * P1 |	| 1 | 2 | 3 | 4 | 5 | 6 | 7  |
 * 	  | 13|-------------------| 6 |
 * P2 |   | 12|11 |10 | 9 | 8 |   |
 *    ------------------------------
 * 
 */
public class MancalaModel {

	public static enum STATE{
		STARTED, COMPLETE
	};
	
	public static enum Player{
		P1, P2
	};
	
	private Player currentPlayer;
	private STATE gameState;
	private int[] pits;
	
	public MancalaModel(int initialStonesPerPit) {
        // Initialize the pits with the specified number of stones
        pits = new int[14]; // 12 pits (6 per player) + 2 stores (mancalas)
        for (int i = 0; i < 14; i++) {
            pits[i] = initialStonesPerPit;
        }
        currentPlayer = Player.P1; // Player 1 starts
    }
	
	
	
	// Methods to handle game logic
	
	/*
	 * 
	 * @param pitIndex
	 */
	public void moveStones(int pitIndex) {
	
	}
	
	
}
