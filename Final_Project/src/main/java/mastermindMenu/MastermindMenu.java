package mastermindMenu;

import javax.swing.*;


import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;


public class MastermindMenu{
	
	
	private MastermindMenu() {
	}
		
	
	    public static void startMastermind() {
	        SwingUtilities.invokeLater(new Runnable() {
	        
	        @Override
	        public void run() {
	            GameSettings settings = new GameSettings();
	            MastermindGame game = new MastermindGame(settings);
	            GameController controller = new GameController(game);
	            controller.startGame();
	        	}
	        });
	    }
	}

