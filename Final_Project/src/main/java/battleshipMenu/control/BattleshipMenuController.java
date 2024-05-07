package battleshipMenu.control;

import battleshipMenu.model.BattleshipGameSettings;
import battleshipMenu.model.BattleshipMenuModel;
import battleshipMenu.view.BattleshipMenuView;
import battleshipMenu.view.BattleshipPlacePhase;
import battleshipMenu.model.BattleshipGameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controller class for the Battleship menu.
 * @author Roney
 */
public class BattleshipMenuController {

    private BattleshipMenuModel model;
    private BattleshipMenuView view;
    private BattleshipGameModel gameModel;
    /**
     * Constructor for the BattleshipMenuController.
     * @param model The BattleshipMenuModel.
     * @param view The BattleshipMenuView.
     * 
     * @author Roney
     */
    public BattleshipMenuController(BattleshipMenuModel model, BattleshipMenuView view) {
        this.model = model;
        this.view = view;

        // Add action listener to the start game button
        view.addStartGameButtonListener(new StartGameButtonListener());
    }
    /**
     * Initiates the Battleship menu view.
     * 
     * @author Roney
     */
    public void initiate() {
        view.setVisible(true);
    }

    /**
     * ActionListener for the start game button.
     * 
     * @author Roney
     */
    public class StartGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Extract selected options from the view
            String player1ShipColor = view.getPlayer1Color();
            String player2ShipColor = view.getPlayer2Color();
            String selectedBoardSize = view.getSelectedBoardSize();
            String shootingTimer = view.getSelectedTimer();
            String selectedShipSet = view.getSelectedShipSet();

            // Create game settings object
            BattleshipGameSettings gameSettings = new BattleshipGameSettings(player1ShipColor, player2ShipColor, selectedBoardSize, selectedShipSet, shootingTimer);

            // Update model with selected game settings
            model.updateGameSettings(gameSettings);

         // Initialize BattleshipGameModel
            BattleshipGameModel gameModel = new BattleshipGameModel(model.getMapSize().getRows(), model.getMapSize().getCols());
            // Open the BattleshipPlacePhaseView
            BattleshipPlacePhase placePhaseView = new BattleshipPlacePhase(model.getMapSize().getRows(), model.getMapSize().getCols(), selectedShipSet, player1ShipColor, player2ShipColor, shootingTimer, gameModel); // Instantiate the place phase view
            placePhaseView.setVisible(true);
        }
    }
}