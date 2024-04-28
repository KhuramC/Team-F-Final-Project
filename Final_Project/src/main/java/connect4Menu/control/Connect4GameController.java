package connect4Menu.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import connect4Menu.model.Connect4GameModel;
import connect4Menu.model.Connect4MenuModel;
import connect4Menu.view.Connect4GameView;
import mvcinterfaces.MenuController;

public class Connect4GameController implements MenuController {
	
	private Connect4GameModel gameModel;
	private Connect4GameView gameView;

	public Connect4GameController(Connect4MenuModel menuModel) {
		this.gameModel = new Connect4GameModel(menuModel);
		this.gameView = new Connect4GameView(menuModel.getRowNum(),menuModel.getColNum(),menuModel.getTimerTime(),
				menuModel.getP1().getColor().getFilePath(),menuModel.getP2().getColor().getFilePath());
		if(menuModel.isTimer()) {
			this.gameModel.addObservertoTimer(gameModel);
			this.gameModel.addObservertoTimer(gameView);
		}

		this.gameView.setTimerLabelVisibility(menuModel.isTimer());
		this.gameView.addListenertoSelectionButtons(new SelectionButtonListener());
		this.gameModel.registerStartedTurnObserver(this.gameView);
		this.gameModel.registerSquarePlayedObserver(this.gameView);
		this.gameModel.registerEndGameObserver(this.gameView);
		this.gameModel.registerInvalidColObserver(this.gameView);
	}

	
	
	
	
	public class SelectionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			int colNum = Integer.parseInt(b.getText(),4,5,10) - 1;
			//System.out.println(colNum);
			gameModel.select(colNum);

			
		}
		
	}
	@Override
	public void initiate() {
		gameView.setVisible(true);
		gameModel.startTurn();
		
	}
	
	

}
