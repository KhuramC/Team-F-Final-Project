package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.ISquarePlayedObserver;

/**
 * An interface for an Observable that tracks when a square has been played by a Player.
 * @author Khuram C.
 */
public interface ISquarePlayedObservable {

	/**
	 * Registers an Observer to watch this Observable for when a square has been played by a Player.
	 * @param o Observer to observe Observable.
	 * @return boolean detailing successful registration.
	 * @author Khuram C.
	 */
	public boolean registerSquarePlayedObserver(ISquarePlayedObserver o);

	/**
	 * Notifies all Observers that have been registered that a square has been played by a Player.
	 * @param p Player who played.
	 * @return boolean detailing successful notification.
	 * @author Khuram C.
	 */
	public boolean notifySquarePlayedObservers(Player p);
}
