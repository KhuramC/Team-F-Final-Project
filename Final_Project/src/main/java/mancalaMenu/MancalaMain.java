package mancalaMenu;

import javax.swing.SwingUtilities;

import mancalaMenu.MancalaController;
import mancalaMenu.MancalaView;
// MVC design pattern

public class MancalaMain {
	
	private MancalaMain() {
	}
	public void initiate() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MancalaView mancalaView = new MancalaView(null);
				mancalaView.setVisible(true);
			}
		});
		
	}

}