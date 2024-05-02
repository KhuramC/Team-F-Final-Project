package connect4Menu;


import connect4Menu.control.Connect4MenuController;


public class Connect4Main {
	
	private Connect4Main() {
	}

	public static void startConnect4() {
		Connect4MenuController controller = new Connect4MenuController();
		controller.initiate();
	}

}
