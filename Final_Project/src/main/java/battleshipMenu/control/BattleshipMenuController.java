package battleshipMenu.control;

import battleshipMenu.*;
import battleshipMenu.model.*;
import battleshipMenu.view.*;

public class BattleshipMenuController {

    private BattleshipMenuModel model;
    private BattleshipMenuView view;

    public BattleshipMenuController(BattleshipMenuModel model, BattleshipMenuView view) {
        this.model = model;
        this.view = view;

        // Add action listener to the start game button
    }

    public void initiate() {
        view.setVisible(true);
    }
}