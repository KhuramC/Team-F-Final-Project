package connect4Menu;


import connect4Menu.control.Connect4SettingsController;


public class Connect4Main {
	
	private Connect4Main() {
	}

	public static void startConnect4() {
		Connect4SettingsController controller = new Connect4SettingsController();
		controller.initiate();
	}

}
