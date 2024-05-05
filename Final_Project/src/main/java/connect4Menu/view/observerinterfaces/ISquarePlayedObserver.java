package connect4Menu.view.observerinterfaces;

import connect4Menu.model.player.Player;

public interface ISquarePlayedObserver {

	public void updateBoard(Player p, int[] selection);

}
