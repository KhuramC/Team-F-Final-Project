package mancalaMenu;

/**
 * Model for Mancala.
 */

/*
 * Representation of the Mancala game board and number for each pit
 * 
 *    ----------------------------------
 * P1 |	  | 0 | 1 | 2 | 3 | 4 | 5 |   |
 * 	  | 13|-----------------------| 6 |
 * P2 |   | 12|11 |10 | 9 | 8 | 7 |   |
 *    ----------------------------------
 *  Right pit is P1 Left pit is P2
 */
public class MancalaModel {
	
	public static enum STATE{
		STARTED, INPLAY, COMPLETE
	};
	
	public static enum Player{
		P1, P2
	};
	
	private Player currentPlayer;
	public STATE gameState;
	private int[] pits;
	private static final int PitCount = 14;
	// define position of mancalas.
	private static final int LeftMancala = 13;
	private static final int RightMancala = 6;
	private static final int initialStoneCount = 4;
	
	public MancalaModel() {
        // Initialize the pits with the specified number of stones
        pits = new int[PitCount]; // 12 pits (6 per player) + 2 stores (mancalas)
        for (int i = 0; i < PitCount; i++) {
        	if (i == LeftMancala || i == RightMancala) {
				pits[i] = 0;
        		continue;
			}
        	pits[i] = initialStoneCount;
        }
        this.setGameState(STATE.STARTED);
        currentPlayer = Player.P1; // Player 1 starts
    }
	
	
	
	// Methods to handle game logic
	
	/**
	 * Moves stones from the selected pit and distributes them on the board.
	 * @param pitIndex of the selected pit.
	 * @return True on success false if invalid
	 */
	public boolean moveStones(int pitIndex) {
		// Check move validity.
		if (!isValidMove(pitIndex) && (this.getGameState() != STATE.INPLAY)) {
			return false;
		}
		
	    int stonesToMove = pits[pitIndex];
	    pits[pitIndex] = 0;
	    int currentPit = distributeStones(stonesToMove, getNextPit(pitIndex));
	        
	    // Check for capture.
	    if (isCapture(currentPlayer, currentPit)) {
	    	System.out.println("BRUHODUIFJIE");

	    	pits[getOppositePit(currentPit)] = 0;
	    	moveStones(currentPit);
	    }
		
		// Check if game is over
		if (isGameOver()) {
			collectRemainingStones();
			gameState = STATE.COMPLETE;
		} else { // Switch turns if one side isn't empty at end of turn.
            if (!isCapture(currentPlayer, currentPit)) {
                currentPlayer = currentPlayer == Player.P1 ? Player.P2 : Player.P1;
            }
		}
		return true;
	}


	/**
	 * Collect the remaining stones from opponent's side and return them to their mancala.
	 */
	private void collectRemainingStones() {
		Player opponent = currentPlayer == Player.P1 ? Player.P2 : Player.P1;
	    for (int i = getPlayerPitStartIndex(opponent); i < getPlayerPitEndIndex(opponent); i++) {
	        pits[getPlayerPitEndIndex(opponent)] += pits[i];
	        pits[i] = 0;
	    }
	}

	/**
	 * 
	 * @return
	 */
	private boolean isGameOver() {
	    boolean allEmpty = true;
	    for (int i = getPlayerPitStartIndex(currentPlayer); i < getPlayerPitEndIndex(currentPlayer); i++) {
	        if (pits[i] > 0) {
	            allEmpty = false;
	            break;
	        }
	    }
	    return allEmpty;
	}

	/**
	 * 	Returns true if capture
	 * @param currentPlayer
	 * @param stonesToMove
	 * @return true if capture-able
	 */
	private boolean isCapture(Player currentPlayer, int currentPit) {
	    int oppositePit = getOppositePit(currentPit);

	    System.out.println("Current Pit: " + currentPit + ", Opposite Pit: " + oppositePit);

	    // Check if the current pit is on the player's side and it's empty
	    if ((currentPlayer == Player.P1 && currentPit >= 0 && currentPit < 6 && pits[currentPit] == 1) ||
	        (currentPlayer == Player.P2 && currentPit >= 7 && currentPit < 13 && pits[currentPit] == 1)) {
	        System.out.println("Current pit is empty.");
	        // Check if the opposite pit on the opponent's side has stones
	        if (pits[oppositePit] > 0) {
	            System.out.println("Opposite pit has stones.");
	            return true; // Capture occurs
	        }
	    }
	    return false; // No capture
	}


	/**
	 * 
	 * @param currentPit
	 * @return
	 */
	private int getOppositePit(int currentPit) {
		return PitCount - currentPit - 2;
	}



	/**
	 * 
	 * @param stonesToMove
	 * @param currentPit
	 * @return 
	 */
	private int distributeStones(int stonesToMove, int currentPit) {
		while (stonesToMove > 0) {
	        stonesToMove--;
	        pits[currentPit]++;
			currentPit = getNextPit(currentPit);
	        // Skip opponent's mancala
	        if (isOpponentMancala(currentPit)) {
	            continue;
	        }
		}
		return currentPit;
	}

	/**
	 * 
	 * @param currentPit
	 * @return boolean
	 */
	private boolean isOpponentMancala(int pit) {
	    if (currentPlayer == Player.P1 && pit == LeftMancala) {
	        return true; // Player 2's Mancala
	    } else if (currentPlayer == Player.P2 && pit == RightMancala) {
	        return true; // Player 1's Mancala
	    } else {
	        return false; // Not an opponent's Mancala
	    }
	}


	/**
	 * Return the index of the next pit.
	 * @param currentPit
	 * @return next pit
	 */
	private int getNextPit(int currentPit) {
		return (currentPit + 1) % PitCount;
	}


	/**
	 * Check the validity of moving stones of index.
	 * @param pitIndex
	 * @return Boolean
	 */
	public boolean isValidMove(int pitIndex) {
		return currentPlayer == Player.P1 && pitIndex < RightMancala && pits[pitIndex] > 0 || 
				currentPlayer == Player.P2 && pitIndex > RightMancala && pits[pitIndex] > 0;
	}

	
	
	/**
	 * 
	 * @param currentPlayer
	 * @return
	 */
	private int getPlayerPitEndIndex(Player currentPlayer) {
		if (currentPlayer == Player.P1) {
			return RightMancala;
		} else {
			return LeftMancala;
		}
	}


	/**
	 * 
	 * @param currentPlayer
	 * @return
	 */
	public static int getPlayerPitStartIndex(Player currentPlayer) {
		if (currentPlayer == Player.P1) {
			return 0;
		} else {
			return RightMancala + 1;
		}
	}

	// Getters and setters

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
	


	/**
	 * @return the pitcount
	 */
	public static int getPitcount() {
		return PitCount;
	}



	/**
	 * @return the leftmancala
	 */
	public static int getLeftmancala() {
		return LeftMancala;
	}



	/**
	 * @return the rightmancala
	 */
	public static int getRightmancala() {
		return RightMancala;
	}



	/**
	 * @return the initialstonecount
	 */
	public static int getInitialstonecount() {
		return initialStoneCount;
	}	
}