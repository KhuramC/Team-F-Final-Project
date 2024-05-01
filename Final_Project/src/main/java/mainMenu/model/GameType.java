package mainMenu.model;

/**
 * An enum for the four games available to play. Every enum has an associated short description of the game.
 * @author Khuram C.
 */
public enum GameType {
	BATTLESHIP("A strategic guessing game where players try to "
			+ "sink each other's hidden ships on a grid-based board."),
	MASTERMIND("A code-breaking game where one player creates a sequence"
			+ " of colors, and the other attempts to guess it using logic and deduction."),
	MANCALA("A turn-based, ancient board game focused on collecting"
			+ " and sowing seeds across a board to capture as many as possible."),
	CONNECT4("A two-player strategy game where players aim to connect"
			+ " four of their colored discs in a row vertically, horizontally, or diagonally.");
	
	private String gameDescription;
	
	/**
	 * Parameterized constructor for the enum, requiring a concise description of the game be passed in.
	 * @param gameDesc String of game description.
	 * @author Khuram C.
	 */
	private GameType(String gameDesc) {
		gameDescription = gameDesc;
	}
	
	/**
	 * Returns a quick description of the game.
	 * @return String of game description.
	 * @author Khuram C.
	 */
	public String getGameDescription() {
		return gameDescription;
	}
}
