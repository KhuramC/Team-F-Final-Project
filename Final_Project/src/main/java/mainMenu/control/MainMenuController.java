package mainMenu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import mainMenu.model.*;
import mainMenu.view.*;

public class MainMenuController {
	
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
			
			switch (gameType) {
			case BATTLESHIP:
				System.out.println("You have chosen battleship!");
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
				//start code to open new gui
				break;
			}
			startView.dispatchEvent(new WindowEvent(startView,WindowEvent.WINDOW_CLOSING));
		}			
	}
		
	
	
	public void initate() {
		startView.setVisible(true);
	}

}
