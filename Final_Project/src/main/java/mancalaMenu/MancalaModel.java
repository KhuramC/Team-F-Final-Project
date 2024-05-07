package mancalaMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model for Mancala.
 */

/*
 * Representation of the Mancala game board and number for each pit
 * 
 * ---------------------------------- P2b| | 0 | 1 | 2 | 3 | 4 | 5 | | |
 * s1|-----------------------| s2| P1a| | 0 | 1 | 2 | 3 | 4 | 5 | |
 * ---------------------------------- Right pit is P1 Left pit is P2
 */

public class MancalaModel {
	/**
	 * Enum representing the state of the game.
	 */
	public static enum STATE {
		INPLAY, COMPLETE
	};

	// The state of the game.
	public STATE gameState;
	private static final int initialStones = 4;
	private static final int numPits = 6;

	protected int currentPlayer; // 1 for p1, 2 for p2.
	private int currPos;
	// pits for p1 and p2
	private int[] p1;
	private int[] p2;

	// stores for p2 and p2
	private int storeP1;
	private int storeP2;

	private int win;
	private boolean hasAnotherTurn;

	/**
	 * Constructor for MancalaModel. Initializes the game board and sets the current
	 * player to 1.
	 */
	public MancalaModel() {
		p1 = new int[numPits];
		p2 = new int[numPits];
		storeP1 = 0;
		storeP2 = 0;

		setGameState(STATE.INPLAY);

		Arrays.fill(p2, initialStones);
		Arrays.fill(p1, initialStones);
		for (int i = 0; i < p1.length; i++) {
			p1[i] = initialStones;
			p2[i] = initialStones;
		}
		currentPlayer = 1;
		win = 0;
	}

	/**
	 * Use to interact with the board.
	 * 
	 * @param pitNumber The number of the pit to move stones from.
	 * @return true if the move is legal, false otherwise.
	 */
	public boolean moveStone(int pitNumber) {

		// Check validity of moves
		int player;
		if (pitNumber >= 0 && pitNumber < numPits) {
			player = 1;
			if (p1[pitNumber] == 0) { // pit cannot be empty.
				return false;
			}
		} else if (pitNumber >= numPits && pitNumber < numPits * 2) {
			player = 2;
			pitNumber -= numPits;
			if (p2[pitNumber] == 0) { // pit cannot be empty.
				return false;
			}
		} else {
			return false; // Invalid pit number
		}
		if (player != currentPlayer) {
			return false;
		}

		if (player == 1) {
			distributeStones(p1, pitNumber, storeP1, p2, 2);
			if (hasAnotherTurn) {
				currentPlayer = 1;
			} else {
				currentPlayer = 2;
			}
		} else {
			distributeStones(p2, pitNumber, storeP2, p1, 1);
			if (hasAnotherTurn) {
				currentPlayer = 2;
			} else {
				currentPlayer = 1;
			}
		}

		System.out.println(Arrays.toString(p1) + "Store :" + storeP1);
		System.out.println(Arrays.toString(p2) + "Store :" + storeP2);
		System.out.println("Player " + currentPlayer);
		return true;
	}

	/**
	 * @return true if the current player gets another turn, false otherwise.
	 */
	public boolean isHasAnotherTurn() {
		return hasAnotherTurn;
	}

	/**
	 * Sets whether the current player gets another turn.
	 * 
	 * @param hasAnotherTurn true if the current player gets another turn, false
	 *                       otherwise.
	 */
	public void setHasAnotherTurn(boolean hasAnotherTurn) {
		this.hasAnotherTurn = hasAnotherTurn;
	}

	private void distributeStones(int[] currPits, int pitNumber, int currentStore, int[] oppositePits, int nextPlayer) {
		int stones = currPits[pitNumber]; // Get the stones from the starting pit
		currPits[pitNumber] = 0; // Empty the starting pit

		int i = pitNumber + 1; // Start index at the next pit
		boolean isOppositeSide = false; // Flag to track if we're on the opposite side
		hasAnotherTurn = false;

		while (stones > 0) {
			stones--; // Decrement stones to distribute

			if (i == 6 && !isOppositeSide) { // Reached end of current player's pits
				int storeStones = getStoreStones(currentPlayer); // Get current player's store stones
				if (currentPlayer == 1) {
					setStoreP1(storeStones + 1); // add 1 to current player's store
				} else {
					setStoreP2(storeStones + 1);
				}
				// give player another turn if last stone lands in store.
				if (stones == 0) {
					hasAnotherTurn = true;
				}

				i = 0; // Wrap around to the first pit on the opposite side
				isOppositeSide = true;
			} else if (isOppositeSide) {
				if (i == 6) { // Reached end of opposite side
					i = 0; // Wrap around to the first pit on the current player's side
					// Capture rule (optional)
					if (stones == 0 && oppositePits[5 - i] == 0) {
						currentStore += oppositePits[5 - i] + 1; // Capture stones from other side
						oppositePits[5 - i] = 0;
					} else {
						oppositePits[i] += 1; // Skip adding to store if capture happens
					}
				} else {
					oppositePits[i] += 1; // Add stone to opposite pit
					i++;
				}
			} else { // Not on the opposite side yet
				if (stones == 0 && currPits[i] == 0) { // Capture rule
					currentStore += oppositePits[5 - i] + 1; // Capture stones from other side
					oppositePits[5 - i] = 0;
				} else {
					currPits[i] += 1; // Add stone to current player's pit
				}
				i++;
			}
		}
	}

	/**
	 * Check if the game has been won. 
	 * set win to 1 for player 1 win,
	 * 2 for player 2 win, 
	 * 3 for draw, 
	 * 4 for no win
	 * 
	 */
	public void checkWin() {
		int p1Sum = Arrays.stream(p1).sum();
		int p2Sum = Arrays.stream(p2).sum();

		// Check if any player has run out of stones in their pits
		if (p1Sum == 0 || p2Sum == 0) {
			// If one player's pits are empty, capture remaining stones into their store
			setGameState(STATE.COMPLETE);
			storeRemainingStones();

			// Determine the winner based on the number of stones in the stores
			if (storeP1 > storeP2) {
				win = 1; // Player 1 wins
			} else if (storeP2 > storeP1) {
				win = 2; // Player 2 wins
			} else {
				win = 3; // Draw
			}
		}
		win = 4; // No win
	}

	/**
	 * helper function for checkWin. Clear p1 and p2, add extras to stores.
	 */
	private void storeRemainingStones() {
		// Capture remaining stones into the stores
		storeP1 += Arrays.stream(p1).sum();
		storeP2 += Arrays.stream(p2).sum();
		// Empty the pits
		Arrays.fill(p1, 0);
		Arrays.fill(p2, 0);
	}

	/**
	 * 
	 * @param player
	 * @param pitNumber
	 * @return Stones in specified pit
	 */
	public int getPitStones(int player, int pitNumber) {
		if (player == 1) {
			return p1[pitNumber];
		} else {
			return p2[pitNumber];
		}
	}

	/**
	 * Takes in player, returns the stones in their store.
	 * 
	 * @param player
	 * @return stones
	 */
	public int getStoreStones(int player) {
		if (player == 1) {
			return storeP1;
		} else {
			return storeP2;
		}
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
	 * @return the currentPlayer
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the currPos
	 */
	public int getCurrPos() {
		return currPos;
	}

	/**
	 * @param currPos the currPos to set
	 */
	public void setCurrPos(int currPos) {
		this.currPos = currPos;
	}

	/**
	 * @return the p1
	 */
	public int[] getP1() {
		return p1;
	}

	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(int[] p1) {
		this.p1 = p1;
	}

	/**
	 * @return the p2
	 */
	public int[] getP2() {
		return p2;
	}

	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(int[] p2) {
		this.p2 = p2;
	}

	/**
	 * @return the storeP1
	 */
	public int getStoreP1() {
		return storeP1;
	}

	/**
	 * @param storeP1 the storeP1 to set
	 */
	public void setStoreP1(int storeP1) {
		this.storeP1 = storeP1;
	}

	/**
	 * @return the storeP2
	 */
	public int getStoreP2() {
		return storeP2;
	}

	/**
	 * @param storeP2 the storeP2 to set
	 */
	public void setStoreP2(int storeP2) {
		this.storeP2 = storeP2;
	}

	/**
	 * @return the initialstones
	 */
	public static int getInitialstones() {
		return initialStones;
	}

	public void resetGame() {
		// TODO Auto-generated method stub

	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

}