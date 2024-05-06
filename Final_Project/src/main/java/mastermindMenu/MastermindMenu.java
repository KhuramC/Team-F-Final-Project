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
	
    /**
     * Private constructor to prevent instantiation.
     */
	private MastermindMenu() {
	}
		
	
    	/**
    	 * Initializes and starts the Mastermind game by creating game settings,
    	 * game logic, and the game controller. This method ensures the game runs
    	 * on the Swing event dispatch thread, which is appropriate for GUI application
    	 * start-up.
    	 */
	    public static void startMastermind() {
	        SwingUtilities.invokeLater(new Runnable() {
	        
	        @Override
	        public void run() { 
	            GameSettings settings = new GameSettings(); // Initialize game settings
	            MastermindGame game = new MastermindGame(settings); // Creates game based on settings 
	            GameController controller = new GameController(game); // Make controller to handle game interactions
	            controller.startGame();
	        	}
	        });
	    }
	}

