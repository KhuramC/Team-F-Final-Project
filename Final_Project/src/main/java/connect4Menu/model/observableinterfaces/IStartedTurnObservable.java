package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.IStartedTurnObserver;

/**
 * An interface for an Observable that tracks when a new turn has started.
 * @author Khuram C.
 */
public interface IStartedTurnObservable {

	/**
	 * Registers an observer to watch this Observable for when a turn starts.
	 * @param o Observer to observe Observable.
	 * @return boolean detailing successful registration.
	 * @author Khuram C.
	 */
	public boolean registerStartedTurnObserver(IStartedTurnObserver o);

	/**
	 * Notifies all Observers that have been registered that a turn has started.
	 * @param p Player whose turn has started.
	 * @return boolean detailing successful notification.
	 * @author Khuram C.
	 */
	public boolean notifyStartedTurnObservers(Player p);
}
