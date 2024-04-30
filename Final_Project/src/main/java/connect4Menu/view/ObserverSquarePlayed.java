package connect4Menu.view;

import connect4Menu.model.Player;

public interface ObserverSquarePlayed {
	
	public void updateBoard(Player p,int[] selection);

}
