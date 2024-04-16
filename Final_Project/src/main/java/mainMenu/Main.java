package mainMenu;

import javax.swing.SwingUtilities;
import mainMenu.model.*;
import mainMenu.view.*;
import mainMenu.control.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainMenuModel model = new MainMenuModel();
				MainMenuStartView startView = new MainMenuStartView();
				MainMenuController controller = new MainMenuController(model,startView);
				controller.initate();	
			}
			
		});

	}

}
