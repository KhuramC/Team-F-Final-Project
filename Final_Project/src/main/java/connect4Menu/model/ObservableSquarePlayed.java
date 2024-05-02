package connect4Menu.model;

import connect4Menu.view.ObserverSquarePlayed;

public interface ObservableSquarePlayed {

	public void registerSquarePlayedObserver(ObserverSquarePlayed o);

	public void notifySquarePlayedObservers(Player p);

}
