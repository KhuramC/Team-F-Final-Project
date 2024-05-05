package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.ObserverSquarePlayed;

public interface ISquarePlayedObservable {

	public void registerSquarePlayedObserver(ObserverSquarePlayed o);

	public void notifySquarePlayedObservers(Player p);

}
