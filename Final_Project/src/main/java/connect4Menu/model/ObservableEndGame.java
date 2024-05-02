package connect4Menu.model;

import connect4Menu.view.ObserverEndGame;

public interface ObservableEndGame {

	public void registerEndGameObserver(ObserverEndGame o);

	public void notifyEndGameObservers(Player p);

}
