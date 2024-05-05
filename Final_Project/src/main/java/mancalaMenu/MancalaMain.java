package mancalaMenu;

import javax.swing.SwingUtilities;


public class MancalaMain {
	
	private MancalaMain() {
	}
	
    public static void startMancala() {
        SwingUtilities.invokeLater(new Runnable() {
        
        @Override
        public void run() { 
            MancalaModel model = new MancalaModel(); // add parameters later.
            MancalaController controller = new MancalaController(model);
            controller.startGame();
        	}
        });
    }
}