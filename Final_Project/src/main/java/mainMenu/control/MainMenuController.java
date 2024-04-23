package mainMenu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import connect4Menu.Connect4Main;
import mainMenu.model.*;
import mainMenu.view.*;
import mvcinterfaces.MenuController;

public class MainMenuController implements MenuController{
	
	private MainMenuModel model;
	private MainMenuStartView startView;

	public MainMenuController(MainMenuModel model, MainMenuStartView startView) {
		this.model = model;
		this.startView = startView;
		// TODO Auto-generated constructor stub
		this.startView.addListenertoChooseGameButton(new GameButtonListener());
	}
	
	
	public class GameButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GameType gameType = startView.getGameChoicesBoxChoice();
			System.out.println(gameType);
			startView.setVisible(false);
			model.setGameChosen(gameType);
			switch (gameType) {
			case BATTLESHIP:
				System.out.println("You have chosen Battleship!");
				//start code to open new gui
				break;
			case MASTERMIND:
				System.out.println("You have chosen Mastermind!");
				//start code to open new gui
				break;
			case MANCALA:
				System.out.println("You have chosen Mancala!");
				//start code to open new gui
				break;
			case CONNECT4:
				System.out.println("You have chosen Connect4!");
				Connect4Main.startConnect4();
				break;
			}
			startView.dispatchEvent(new WindowEvent(startView,WindowEvent.WINDOW_CLOSING));
			
		}			
	}
	
	public void initiate() {
		startView.setVisible(true);
	}

}
