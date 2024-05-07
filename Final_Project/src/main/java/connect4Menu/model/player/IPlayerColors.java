package connect4Menu.model.player;

/**
 * An interface for enums to do with PlayerColors. This is to allow for
 * different enums to exist depending on the player and be in separate
 * JComboBoxes but still have one field for their color in the Player class.
 * 
 * @author Khuram C.
 */
public interface IPlayerColors {

	/**
	 * Returns the player the color is associated with.
	 * 
	 * @return int representing player number.
	 * @author Khuram C.
	 */
	public int getAllowedPlayer();

	/**
	 * Returns the filePath associated with the color.
	 * @return string representing color
	 * @author Khuram C.
	 */
	public String getFilePath();

}
