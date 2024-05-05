package connect4Menu.model.observableinterfaces;

import connect4Menu.view.ObserverInvalidCols;

public interface IInvalidColsObservable {

	public void registerInvalidColObserver(ObserverInvalidCols o);

	public void notifyInvalidColObservers(int colNum);

}
