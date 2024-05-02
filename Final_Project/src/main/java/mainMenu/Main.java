package mainMenu;

import mainMenu.control.*;

/**
 * Main class in which everything else starts.
 * @author Khuram C.
 */
public class Main {

	/**
	 * Main method in which the starting menu is shown to the user.
	 * @param args
	 * @author Khuram C.
	 */
	public static void main(String[] args) {
		MainMenuController controller = new MainMenuController();
		controller.initiate();
	}
}
