package battleshipMenu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BattleshipMenuView extends JFrame {
    private JComboBox<String> selectOpponentComboBox;
    private JComboBox<String> selectShipColorComboBox;
    private JComboBox<String> selectBoardSizeComboBox;
    private JComboBox<String> selectTimerComboBox;
    private JComboBox<String> selectOpponentTypeComboBox;
    private JComboBox<String> selectShipSetComboBox;
    
    private JButton startGameButton;
    private JPanel contentPane = new JPanel();

    public BattleshipMenuView() {
        setTitle("♦ Battleship ♦");
        setBounds(100, 100, 700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Personal Options - MENU
        JLabel personalizeLabel = new JLabel("Personal Options");
        personalizeLabel.setBounds(125, 15, 150, 30);
        contentPane.add(personalizeLabel);

        JLabel opponentLabel = new JLabel("Player 1 Ship Color:");
        opponentLabel.setBounds(50, 50, 150, 30);
        contentPane.add(opponentLabel);
        selectOpponentComboBox = new JComboBox<>(new String[]{"Green", "Yellow", "Purple", "Orange"});
        selectOpponentComboBox.setBounds(180, 50, 150, 30);
        contentPane.add(selectOpponentComboBox);

        JLabel shipColorLabel = new JLabel("Player 2 Ship Color:");
        shipColorLabel.setBounds(50, 100, 150, 30);
        contentPane.add(shipColorLabel);
        selectShipColorComboBox = new JComboBox<>(new String[]{"Green", "Yellow", "Purple", "Orange"});
        selectShipColorComboBox.setBounds(180, 100, 150, 30);
        contentPane.add(selectShipColorComboBox);

        // Game Options - MENU
        JLabel gameOptionLabel = new JLabel("Game Options");
        gameOptionLabel.setBounds(425, 15, 150, 30);
        contentPane.add(gameOptionLabel);

        JLabel boardSizeLabel = new JLabel("Select Board Size:");
        boardSizeLabel.setBounds(350, 50, 150, 30);
        contentPane.add(boardSizeLabel);
        selectBoardSizeComboBox = new JComboBox<>(new String[]{"Small (7x7)", "Normal (10x10)", "Large (13x13)"});
        selectBoardSizeComboBox.setBounds(480, 50, 150, 30);
        contentPane.add(selectBoardSizeComboBox);

        JLabel timerLabel = new JLabel("Select Timer:");
        timerLabel.setBounds(350, 100, 150, 30);
        contentPane.add(timerLabel);
        selectTimerComboBox = new JComboBox<>(new String[]{"No Timer", "30 sec", "1 min"});
        selectTimerComboBox.setBounds(480, 100, 150, 30);
        contentPane.add(selectTimerComboBox);

        JLabel opponentLabel1 = new JLabel("Select Opponent:");
        opponentLabel1.setBounds(350, 150, 150, 30);
        contentPane.add(opponentLabel1);
        selectOpponentTypeComboBox = new JComboBox<>(new String[]{"Player vs Player", "Player vs Computer"});
        selectOpponentTypeComboBox.setBounds(480, 150, 150, 30);
        contentPane.add(selectOpponentTypeComboBox);

        JLabel shipSet = new JLabel("Select Ship Set:");
        shipSet.setBounds(350, 200, 150, 30);
        contentPane.add(shipSet);
        selectShipSetComboBox = new JComboBox<>(new String[]{"Stealth", "Normal", "Massive"});
        selectShipSetComboBox.setBounds(480, 200, 150, 30);
        contentPane.add(selectShipSetComboBox);

        // Start game button
        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(480, 500, 150, 30);
        contentPane.add(startGameButton);
    }
  

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BattleshipMenuView battleshipView = new BattleshipMenuView();
            battleshipView.setVisible(true);
        });
    }

    // Getter methods for selected options
    public String getSelectedOpponent() {
        return (String) selectOpponentComboBox.getSelectedItem();
    }

    public String getSelectedShipColor() {
        return (String) selectShipColorComboBox.getSelectedItem();
    }

    public String getSelectedBoardSize() {
        return (String) selectBoardSizeComboBox.getSelectedItem();
    }

    public String getSelectedTimer() {
        return (String) selectTimerComboBox.getSelectedItem();
    }

    public String getSelectedShipSet() {
        return (String) selectShipSetComboBox.getSelectedItem();
    }
}