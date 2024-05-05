package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.ISquarePlayedObserver;

public interface ISquarePlayedObservable {

	public void registerSquarePlayedObserver(ISquarePlayedObserver o);

	public void notifySquarePlayedObservers(Player p);

}
