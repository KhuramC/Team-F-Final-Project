package connect4Menu.model.player;

public class Player {

	private String name;
	private IPlayerColors color;
	private boolean isTurn = false;
	private int playerNum;

	public Player(String name, IPlayerColors color, int playerNum) {
		this.name = name;
		this.color = color;
		this.playerNum = playerNum;
	}

	public String getName() {
		return name;
	}

	public IPlayerColors getColor() {
		return color;
	}

	public void setColor(IPlayerColors color) {
		this.color = color;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean bool) {
		isTurn = bool;
	}

	public int getPlayerNum() {
		return playerNum;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		Player p = (Player) o;
		if(p == this) {
			return true;
		}
		
		if(p.name.equals(name) && p.getColor() == color && p.isTurn() == isTurn 
				&& p.playerNum == playerNum ) {
			return true;
		}
		return false;
		
	}

}
