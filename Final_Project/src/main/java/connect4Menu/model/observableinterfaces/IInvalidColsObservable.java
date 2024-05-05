package connect4Menu.model.observableinterfaces;

import connect4Menu.view.observerinterfaces.IInvalidColsObserver;

/**
 * An interface for an Observable that tracks when a column is now invalid to select.
 * @author Khuram C.
 */
public interface IInvalidColsObservable {

	/**
	 * Registers an Observer to watch this Observable for when a column is now invalid to select.
	 * @param o Observer to observe Observable.
	 * @return boolean detailing successful registration.
	 * @author Khuram C.
	 */
	public boolean registerInvalidColObserver(IInvalidColsObserver o);

	/**
	 * Notifies all Observers that have been registered that a column is now invalid to select.
	 * @param colNum number of now invalid column.
	 * @return boolean detailing successful notification.
	 * @author Khuram C.
	 */
	public boolean notifyInvalidColObservers(int colNum);

}
