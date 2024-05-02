package connect4Menu.model;

public class Player {

	@Override
	public String toString() {
		return "Player [name=" + name + ", color=" + color + "]";
	}

	private String name;
	private PlayerColors color;
	private boolean isWinner = false;
	private boolean isTurn = false;
	private int playerNum;

	public Player(String name, PlayerColors color, int playerNum) {
		this.name = name;
		this.color = color;
		this.playerNum = playerNum;
	}

	public String getName() {
		return name;
	}

	public PlayerColors getColor() {
		return color;
	}

	public void setColor(PlayerColors color) {
		this.color = color;
	}

	public void setWinner() {
		isWinner = true;
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

}
