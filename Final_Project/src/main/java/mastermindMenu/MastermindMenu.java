package mastermindMenu;

import javax.swing.*;

import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;


public class MastermindMenu{
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            GameSettings settings = new GameSettings();
	            MastermindGame game = new MastermindGame(settings);
	            GameController controller = new GameController(game);
	            controller.startGame();
	        });
	    }
	}

