package mainMenu;

import javax.swing.SwingUtilities;
import mainMenu.model.*;
import mainMenu.view.*;
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
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainMenuModel model = new MainMenuModel();
				MainMenuStartView startView = new MainMenuStartView();
				MainMenuController controller = new MainMenuController(model,startView);
				controller.initiate();	
			}
		});
	}
}
