package mainMenu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


import battleshipMenu.BattleshipMain;
import javax.swing.SwingUtilities;


import connect4Menu.Connect4Main;
import mainMenu.model.*;
import mainMenu.view.*;
import mancalaMenu.MancalaMain;
import mancalaMenu.MancalaModel;
import mastermindMenu.MastermindMenu;
import music.MusicLocations;
import music.MusicPlayer;
import mvcinterfaces.MenuController;

/**
 * A Controller from the MVC architecture for the main menu to select a game to
 * play.
 * 
 * @author Khuram C.
 */
public class MainMenuController implements MenuController {

	private MainMenuModel model;
	private MainMenuView startView;


	/**
	 * A default constructor for the controller. Creates the associated model and
	 * view for the Main Menu and adds it as an observer for the model. The
	 * associated listeners for the view are also added.
	 * 
	 * @author Khuram C.
	 */
	public MainMenuController() {
		this.model = new MainMenuModel();
		this.startView = new MainMenuView();
		model.addObserver(startView);
		startView.addListenertoGameChoicesBox(new GameChoicesBoxListener());
		startView.addListenertoStartGameButton(new StartGameButtonListener());
	}

	/**
	 * A subclass that listens to the gameChoicesBox, gets the game chosen, saves
	 * the choice in the model.
	 * 
	 * @author Khuram C.
	 */
	public class GameChoicesBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameType gameChosen = startView.getGameChoicesBoxChoice();
			model.chooseGame(gameChosen);
		}
	}

	/**
	 * A subclass that listens to the startGameButton, closes the current main menu,
	 * and opens up the game chosen.
	 * 
	 * @author Khuram C.
	 */
	public class StartGameButtonListener implements ActionListener {
		

		@Override
		public void actionPerformed(ActionEvent e) {
			GameType gameType = model.getGameChosen();
			startGame(gameType);	
		}
		
		/**
		 * Method to actually start the game chosen. Makes the view invisible, officially closes it, and starts the
		 * associated game.
		 * @param gameChosen GameType to play.
		 * @return boolean detailing successful start of game.
		 */
		public boolean startGame(GameType gameChosen) {
			startView.setVisible(false);
			startView.dispatchEvent(new WindowEvent(startView, WindowEvent.WINDOW_CLOSING));
			switch (gameChosen) {
			case BATTLESHIP:
				System.out.println("You have chosen Battleship!");
				BattleshipMain.startBattleship();
				break;
			case MASTERMIND:
				System.out.println("You have chosen Mastermind!");
<<<<<<< HEAD
				MastermindMenu.startMastermind(); 	//start code to open new gui
=======
				MastermindMenu.startMastermind();   //start code to open new gui
>>>>>>> 72bd0b20f4247d49cf8836fdb0372575ac8b25a7
				break;
			case MANCALA:
				System.out.println("You have chosen Mancala!");
				MancalaMain.startMancala();
				break;
			case CONNECT4:
				Connect4Main.startConnect4();
				break;
			}
			MusicPlayer.getInstance().pauseMusic();
			return true;
		}
	}

	/**
	 * Starts the application by making the view visible to the user.
	 * 
	 * @author Khuram C.
	 */
	public boolean initiate() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MusicPlayer.getInstance().playMusic(MusicLocations.MAINMENU.getMusicFilePath());
				startView.setVisible(true);
			}
		});
		return true;
	}
}
