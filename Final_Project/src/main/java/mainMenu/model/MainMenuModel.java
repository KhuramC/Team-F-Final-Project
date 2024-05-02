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
	 * A default constructor for the MainMenuModel. Because the gameType already has
	 * a default value, nothing is initialized.
	 * 
	 * @author Khuram C.
	 */
	public MainMenuModel() {
	}

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
