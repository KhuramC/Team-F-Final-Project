package connect4Menu.view.observerinterfaces;

import connect4Menu.model.player.Player;

/**
 * An interface for an Observer that tracks when a new turn has started.
 * @author Khuram C.
 */
public interface IStartedTurnObserver {

	/**
	 * Updates text for who's turn it currently is.
	 * @param p Player whose turn it now is.
	 * @return boolean detailing successful update.
	 * @author Khuram C.
	 */
	public boolean updatePlayerTurnText(Player p);

}
