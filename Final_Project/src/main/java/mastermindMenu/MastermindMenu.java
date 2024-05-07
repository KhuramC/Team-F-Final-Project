package mastermindMenu;

import javax.swing.*;


import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;

/**
 * Provides a main entry point to start the Mastermind game.
 * This class initializes the game settings, game logic, and game controller,
 * and it sets up the game environment to be run on the Swing event dispatch thread.
 * @author Alon B.
 */
public class MastermindMenu{
	private GameSettings settings;
    private MastermindGame game;
    private GameController controller;

    	public MastermindMenu(GameSettings settings, MastermindGame game, GameController controller) {
            this.settings = settings;
            this.game = game;
            this.controller = controller;
	}
    	
//		/**
//   	 * Initializes and starts the Mastermind game by creating game settings,
//   	 * game logic, and the game controller. This method ensures the game runs
//   	 * on the Swing event dispatch thread, which is appropriate for GUI application
//   	 * start-up.
//   	 */

    	public static void startMastermind() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() { 
                    GameSettings settings = new GameSettings();
                    MastermindGame game = new MastermindGame(settings);
                    GameController controller = new GameController(game);
                    MastermindMenu menu = new MastermindMenu(settings, game, controller);
                    menu.startGame();
                }
            });
        }

		public void startGame() {
			controller.startGame();
			
		}
}

