package connect4Menu;

import javax.swing.SwingUtilities;

import connect4Menu.control.Connect4MenuController;
import connect4Menu.model.Connect4MenuModel;
import connect4Menu.view.Connect4SettingsMenuView;



public class Connect4Main {

	private Connect4Main() {
	}
	
	public static void startConnect4() {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					Connect4MenuModel model = new Connect4MenuModel();
					Connect4SettingsMenuView startView = new Connect4SettingsMenuView();
					Connect4MenuController controller = new Connect4MenuController(model,startView);
					controller.initiate();	
				}	
			});

		
		
	}

}
