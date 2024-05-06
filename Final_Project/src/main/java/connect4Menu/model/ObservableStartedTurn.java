package connect4Menu.model;

import connect4Menu.model.player.Player;
import connect4Menu.view.ObserverStartedTurn;

public interface ObservableStartedTurn {

	public void registerStartedTurnObserver(ObserverStartedTurn o);

	public void notifyStartedTurnObservers(Player p);
}
