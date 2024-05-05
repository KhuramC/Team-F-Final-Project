package mainMenu.model;

import java.util.Observable;

/**
 * A Model from the MVC architecture for the main menu to select a game to play.
 * 
 * @author Khuram C.
 */
public class MainMenuModel extends Observable {

	private GameType gameChosen = GameType.BATTLESHIP; // default value

	/**
	 * Returns the game chosen to play.
	 * 
	 * @return gameType of game chosen.
	 * @author Khuram C.
	 */
	public GameType getGameChosen() {
		return gameChosen;
	}

	/**
	 * Sets the game to be played and notifies observers of the change.
	 * 
	 * @param gameChosen by the player.
	 * @return boolean detailing successful choosing of game.
	 * @author Khuram C.
	 */
	public boolean chooseGame(GameType gameChosen) {
		this.gameChosen = gameChosen;
		setChanged();
		notifyObservers(gameChosen);
		clearChanged();
		return true;
	}

}
