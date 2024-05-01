package connect4Menu.model;

/**
 * An enum representing the colors that a Player can be. Colors have a value of 2 because these colors are meant 
 * for Player 2.
 * @author Khuram C.
 */
public enum Player2Colors implements PlayerColors{
	YELLOW("/connect4/images/connect4p2yellowsquare.drawio.png"),
	ORANGE("/connect4/images/connect4p2orangesquare.drawio.png"),
	PURPLE("/connect4/images/connect4p2purplesquare.drawio.png");
	

	private int allowedPlayer;
	private String filePath;
	
	/**
	 * Default Constructor for Player1Colors. All colors in this enum are only for Player 2.
	 * @author Khuram C.
	 */
	private Player2Colors(String filePath) {
		this.filePath = filePath;
		allowedPlayer = 2;
	}
	
	@Override
	public int getAllowedPlayer() {
		return allowedPlayer;
	}

	@Override
	public String getFilePath() {
		return filePath;
	}
	
}
