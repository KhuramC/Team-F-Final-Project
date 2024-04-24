package connect4Menu.model;

/**
 * An enum representing the colors that a Player can be. Colors have a value of 1 because these colors are meant 
 * for Player 1.
 * @author Khuram C.
 */
public enum Player1Colors implements PlayerColors {
	RED(),
	BLACK(),
	GREEN();
	
	private int allowedPlayer;

	/**
	 * Default Constructor for Player1Colors. All colors in this enum are only for Player 1.
	 * @author Khuram C.
	 */
	private Player1Colors() {
		allowedPlayer = 1;
	}
	
	@Override
	public int getAllowedPlayer() {
		return allowedPlayer;
	}	
}
