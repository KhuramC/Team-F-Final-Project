package connect4Menu.model;

import connect4Menu.view.ObserverInvalidCols;

public interface ObservableInvalidCols {

	public void registerInvalidColObserver(ObserverInvalidCols o);

	public void notifyInvalidColObservers(int colNum);

}
