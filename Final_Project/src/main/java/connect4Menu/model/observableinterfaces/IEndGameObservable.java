package connect4Menu.model.observableinterfaces;

import connect4Menu.model.player.Player;
import connect4Menu.view.ObserverEndGame;

public interface IEndGameObservable {

	public void registerEndGameObserver(ObserverEndGame o);

	public void notifyEndGameObservers(Player p);

}
