package mancalaMenu;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Representation of the Mancala game board and number for each pit
 * 
 *    ----------------------------------
 * P1 |	  | 1 | 2 | 3 | 4 | 5 | 6 | 7  |
 * 	  | 14|-----------------------|    |
 * P2 |   | 13|12 |11 | 10 | 9 | 8 |   |
 *    ----------------------------------
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
	private static final int PitCount = 14;
	
	public MancalaModel() {
        // Initialize the pits with the specified number of stones
        pits = new int[PitCount]; // 12 pits (6 per player) + 2 stores (mancalas)
        for (int i = 0; i < PitCount; i++) {
            pits[i] = PitCount;
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



	/**
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}



	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}



	/**
	 * @return the gameState
	 */
	public STATE getGameState() {
		return gameState;
	}



	/**
	 * @param gameState the gameState to set
	 */
	public void setGameState(STATE gameState) {
		this.gameState = gameState;
	}



	/**
	 * @return the pits
	 */
	public int[] getPits() {
		return pits;
	}



	/**
	 * @param pits the pits to set
	 */
	public void setPits(int[] pits) {
		this.pits = pits;
	}
	
	
}
