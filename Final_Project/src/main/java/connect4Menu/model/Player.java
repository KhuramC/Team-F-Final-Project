package connect4Menu.model;

public class Player {

	@Override
	public String toString() {
		return "Player [name=" + name + ", color=" + color + "]";
	}


	private String name;
	private PlayerColors color;
	
	
	public Player(String name, PlayerColors color) {
		this.name = name;
		this.color = color;
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

}
