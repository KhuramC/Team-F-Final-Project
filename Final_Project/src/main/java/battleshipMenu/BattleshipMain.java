package battleshipMenu;

import javax.swing.SwingUtilities;
import battleshipMenu.control.*;
import battleshipMenu.model.*;
import battleshipMenu.view.*;

public class BattleshipMain {
    private BattleshipMain() {
    }

    public static void startBattleship() {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BattleshipMenuModel model = new BattleshipMenuModel();
                BattleshipMenuView startView = new BattleshipMenuView();
                BattleshipMenuController controller = new BattleshipMenuController(model, startView);
                controller.initiate();
            }
        });
    }
}

