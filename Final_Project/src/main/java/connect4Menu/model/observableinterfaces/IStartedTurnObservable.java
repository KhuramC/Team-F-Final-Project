package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.IStartedTurnObserver;

public interface IStartedTurnObservable {

	public void registerStartedTurnObserver(IStartedTurnObserver o);

	public void notifyStartedTurnObservers(Player p);
}
