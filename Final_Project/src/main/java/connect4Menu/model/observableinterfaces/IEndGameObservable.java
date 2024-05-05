package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.IEndGameObserver;

public interface IEndGameObservable {

	public void registerEndGameObserver(IEndGameObserver o);

	public void notifyEndGameObservers(Player p);

}
