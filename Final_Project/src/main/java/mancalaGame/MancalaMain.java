package mancalaGame;

import javax.swing.SwingUtilities;

import mancalaGame.*;

// MVC design pattern

public class MancalaMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MancalaController controller = 
								new MancalaController();// give arguments to mancala controller
				
//				controller.initiate();
			}
		});
	}
}
