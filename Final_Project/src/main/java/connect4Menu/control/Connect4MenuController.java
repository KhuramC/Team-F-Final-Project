package connect4Menu.control;

import connect4Menu.model.Connect4MenuModel;
import connect4Menu.view.Connect4MenuView;

public class Connect4MenuController {
	
	public Connect4MenuModel model;
	public Connect4MenuView view;

	public Connect4MenuController(Connect4MenuModel model, Connect4MenuView view) {
		this.model = model;
		this.view = view;
		// TODO Auto-generated constructor stub
	}
	
	public void initiate() {
		view.setVisible(true);
	}

}
