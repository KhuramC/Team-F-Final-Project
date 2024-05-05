package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.ObserverStartedTurn;

public interface IStartedTurnObservable {

	public void registerStartedTurnObserver(ObserverStartedTurn o);

	public void notifyStartedTurnObservers(Player p);
}
