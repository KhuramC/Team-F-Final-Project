package connect4Menu.view.observerinterfaces;

import connect4Menu.model.player.Player;

/**
 * An interface for an Observer that tracks when a square has been played by a Player.
 * @author Khuram C.
 */
public interface ISquarePlayedObserver {

	/**
	 * Updates the visual board with the given Player's selection.
	 * @param p Player that played.
	 * @param selection int array representing location. First is row number, second is column number.
	 * @return boolean detailing successful update.
	 * @author Khuram C.
	 */
	public boolean updateBoard(Player p, int[] selection);

}
