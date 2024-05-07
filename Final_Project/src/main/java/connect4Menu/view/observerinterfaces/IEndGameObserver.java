package connect4Menu.view.observerinterfaces;

import connect4Menu.model.player.Player;

/**
 * An interface for an Observer that tracks when a Connect4 game has now ended.
 * @author Khuram C.
 */
public interface IEndGameObserver {

	/**
	 * Updates text to which Player won the game (or if a tie occurred).
	 * @param p Player that won. Null represents a tie.
	 * @return boolean detailing successful update.
	 * @author Khuram C.
	 */
	public boolean updateTextWithWinner(Player p);

}
