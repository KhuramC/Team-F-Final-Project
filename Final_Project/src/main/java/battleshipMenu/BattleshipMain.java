package battleshipMenu;

import java.util.List;

import javax.swing.SwingUtilities;

import battleshipMenu.control.*;
import battleshipMenu.model.*;
import battleshipMenu.view.*;

public class BattleshipMain {

    private BattleshipMain() {
    }

    public static void startBattleship() {
        // Print ship sets
        printShipSets();

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

    // Method to print ship sets
    private static void printShipSets() {
        System.out.println("Ship Sets:");
        System.out.println("Stealth Set:");
        printShipSet(ShipSet.STEALTH);
        System.out.println("\nNormal Set:");
        printShipSet(ShipSet.NORMAL);
        System.out.println("\nMassive Set:");
        printShipSet(ShipSet.MASSIVE);
        System.out.println();
    }

    // Method to print a ship set
    private static void printShipSet(List<String> shipSet) {
        for (String ship : shipSet) {
            System.out.println(ship);
        }
    }
}