package connect4Menu.model;

/**
 * An enum representing the colors that a Player can be. Colors have a value of 2 because these colors are meant 
 * for Player 2.
 * @author Khuram C.
 */
public enum Player2Colors implements PlayerColors{
	YELLOW(),
	BLUE(),
	PURPLE();
	

	private int allowedPlayer;
	
	/**
	 * Default Constructor for Player1Colors. All colors in this enum are only for Player 2.
	 * @author Khuram C.
	 */
	private Player2Colors() {
		allowedPlayer = 2;
	}
	
	@Override
	public int getAllowedPlayer() {
		return allowedPlayer;
	}
}
