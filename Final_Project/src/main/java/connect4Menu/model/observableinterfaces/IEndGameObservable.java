package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.IEndGameObserver;

/**
 * An interface for an Observable that tracks when a Connect4 game has now ended.
 * @author Khuram C.
 */
public interface IEndGameObservable {

	/**
	 * Registers an Observer to watch this Observable for when a game has now ended.
	 * @param o Observer to observe Observable.
	 * @return boolean detailing successful registration.
	 * @author Khuram C.
	 */
	public boolean registerEndGameObserver(IEndGameObserver o);

	/**
	 * Notifies all Observers that have been registered that a game has now ended.
	 * @param p Player that won. Null represents a tie.
	 * @return boolean detailing successful notification.
	 * @author Khuram C.
	 */
	public boolean notifyEndGameObservers(Player p);
}
