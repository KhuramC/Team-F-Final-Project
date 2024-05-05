package connect4Menu.model.observableinterfaces;

import connect4Menu.view.observerinterfaces.IInvalidColsObserver;

public interface IInvalidColsObservable {

	public void registerInvalidColObserver(IInvalidColsObserver o);

	public void notifyInvalidColObservers(int colNum);

}
