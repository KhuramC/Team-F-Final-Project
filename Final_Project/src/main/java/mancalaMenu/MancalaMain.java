package mancalaMenu;

import javax.swing.SwingUtilities;

import mancalaMenu.*;

// MVC design pattern

public class MancalaMain {

	public static void startMancala(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MancalaController controller = 
								new MancalaController();// give arguments to mancala controller
//				view
//				model
				
				controller.initiate();
			}
		});
	}
}
