package mancalaMenu;

import javax.swing.SwingUtilities;

import mancalaMenu.*;

// MVC design pattern

public class MancalaMain {

	public static void startMancala(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// give arguments to mancala controller
				MancalaView view = new MancalaView();
				MancalaModel model = new MancalaModel();
				MancalaController controller = new MancalaController(model, view);
//				view
//				model
				
				controller.initiate();
			}
		});
	}
}
