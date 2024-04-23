package mainMenu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import connect4Menu.Connect4Main;
import mainMenu.model.*;
import mainMenu.view.*;
import mvcinterfaces.MenuController;

/**
 * A Controller from the MVC architecture for the main menu to select a game to play.
 * @author Khuram C.
 */
public class MainMenuController implements MenuController{
	
	private MainMenuModel model;
	private MainMenuStartView startView;

    /**
     * A parameterized constructor for the controller. There is no need for a default constructor since
     * the parameters and controller should always be created at the same time.
     * @param model to hold the data.
     * @param startView to show to the user.
     * @author Khuram C.
     */
	public MainMenuController(MainMenuModel model, MainMenuStartView startView) {
		this.model = model;
		this.startView = startView;
		this.startView.addListenertoGameChoicesBox(new GameChoicesBoxListener());
		this.startView.addListenertoStartGameButton(new StartGameButtonListener());
	}
	
	/**
	 * A subclass that listens to the gameChoicesBox, gets the game chosen, saves the choice in the model.
	 * @author Khuram C.
	 */
	public class GameChoicesBoxListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			GameType gameType = startView.getGameChoicesBoxChoice();
			model.setGameChosen(gameType);
		}	
	}
	
	/**
	 * A subclass that listens to the startGameButton, closes the current main menu, and opens up the game chosen.
	 * @author Khuram C.
	 */
	public class StartGameButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			startView.setVisible(false);
			GameType gameType = model.getGameChosen();
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
				Connect4Main.startConnect4();
				break;
			}
			startView.dispatchEvent(new WindowEvent(startView,WindowEvent.WINDOW_CLOSING));
		}			
	}
	
	/**
	 * 'Starts' the application by making the view visible to the user.
	 * @author Khuram C.
	 */
	public void initiate() {
		startView.setVisible(true);
	}

}
