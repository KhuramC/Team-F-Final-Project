package connect4Menu.model.player;

/**
 * A class representing one of the players playing Connect4. Players have a name, color and number all associated with them.
 * They also have a field for if it is their turn currently.
 * @author Khuram C.
 */
public class Player {

	private String name;
	private IPlayerColors color;
	private boolean isTurn = false;
	private int playerNum;

	/**
	 * Parameterized Constructor for Player. 
	 * @param name name of Player.
	 * @param color color of Player.
	 * @param playerNum number of Player.
	 * @author Khuram C.
	 */
	public Player(String name, IPlayerColors color, int playerNum) {
		this.name = name;
		this.color = color;
		this.playerNum = playerNum;
	}

	/**
	 * Returns the name of the Player.
	 * @return string of Player's name.
	 * @author Khuram C.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the color of the Player.
	 * @return IPlayerColors of Player's color.
	 * @author Khuram C.
	 */
	public IPlayerColors getColor() {
		return color;
	}

	/**
	 * Sets the color of the Player.
	 * @param color to change to.
	 * @return boolean detailing successful set.
	 * @author Khuram C.
	 */
	public boolean setColor(IPlayerColors color) {
		this.color = color;
		return true;
	}

	/**
	 * Returns whether it is the Player's turn.
	 * @return boolean detailing turn status.
	 * @author Khuram C.
	 */
	public boolean isTurn() {
		return isTurn;
	}

	/**
	 * Sets the turn of the Player.
	 * @param bool detailing new turn status.
	 * @author Khuram C.
	 */
	public void setTurn(boolean bool) {
		isTurn = bool;
	}

	/**
	 * Gets the number of the Player.
	 * @return int detailing Player number.
	 * @author Khuram C.
	 */
	public int getPlayerNum() {
		return playerNum;
	}
	
	/**
	 * Overridden equals method.. Checks if they are the same object or have the same attributes, true is returned.
	 * Otherwise, false is returned.
	 * @author Khuram C.
	 */
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
