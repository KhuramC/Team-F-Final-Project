package mancalaMenu;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MancalaController {
    private MancalaModel model;
    private MancalaView view;

    public MancalaController(MancalaModel model) {
        this.model = model;
    }

    public void setView(MancalaView view) {
        this.view = view;
        this.view.updateView(); // Initial update of the view
    }

    public MancalaModel getModel() {
        return model;
    }

    public void clickPit(int pitIndex) {
    	// it is always indexed 0-5
    	System.out.println("PitIndex" + pitIndex);
    	
    	if (!model.moveStone(pitIndex)) {
    		handleIllegalMove();
    	}
    	model.checkWin();
        if (model.getGameState() == MancalaModel.STATE.COMPLETE) {
        	;
            handleGameWon();
        }
        view.updateView();
    }

    private void handleIllegalMove() {
    	JOptionPane.showMessageDialog(null, "Illegal Move. Choose another pit!", "Illegal Move", JOptionPane.WARNING_MESSAGE);
	}

    private void handleGameWon() {
    	int win = model.getWin();
    	switch (win) {
		case 1:
			JOptionPane.showMessageDialog(view, "P1 Wins!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 2:
			JOptionPane.showMessageDialog(view, "P2 Wins!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
			break;
		case 3:
			JOptionPane.showMessageDialog(view, "Tie!", "You Won!", JOptionPane.INFORMATION_MESSAGE);
		default:
			break;
		}
    }

	public void startGame() {
      JFrame frame = new JFrame("Mancala");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600, 400);
      frame.getContentPane().setLayout(new BorderLayout());
      frame.getContentPane().add(view, BorderLayout.CENTER);
      frame.setVisible(true);
		
	}
}