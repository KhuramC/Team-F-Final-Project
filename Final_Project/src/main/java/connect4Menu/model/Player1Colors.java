package connect4Menu.model;

/**
 * An enum representing the colors that a Player can be. Colors have a value of
 * 1 because these colors are meant for Player 1.
 * 
 * @author Khuram C.
 */
public enum Player1Colors implements PlayerColors {
	RED("/connect4/images/connect4p1redsquare.drawio.png"), BLACK("/connect4/images/connect4p1blacksquare.drawio.png"),
	GREEN("/connect4/images/connect4p1greensquare.drawio.png");

	private int allowedPlayer;
	private String filePath;

	/**
	 * Default Constructor for Player1Colors. All colors in this enum are only for
	 * Player 1.
	 * 
	 * @author Khuram C.
	 */
	private Player1Colors(String filePath) {
		this.filePath = filePath;

		allowedPlayer = 1;
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
