package connect4Menu.view;

import connect4Menu.model.player.Player;

public interface ObserverSquarePlayed {

	public void updateBoard(Player p, int[] selection);

}
