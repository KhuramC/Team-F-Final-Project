package battleshipMenu.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view class responsible for displaying the Battleship game menu.
 * This class extends JFrame to create a graphical user interface.
 */
public class BattleshipMenuView extends JFrame {
	   
    private JComboBox<String> p1ColorComboBox;
    private JComboBox<String> p2ColorComboBox;
    private JComboBox<String> selectBoardSizeComboBox;
    private JComboBox<String> selectTimerComboBox;
    private JComboBox<String> selectShipSetComboBox;
    private JTextArea explanationTextArea;
    public JButton startGameButton;
    private JPanel contentPane = new JPanel();

    /**
    * Constructor for the BattleshipMenuView class.
    * Sets up the graphical user interface elements.
    */
    public BattleshipMenuView() {
        setTitle("♦ Battleship ♦");
        setBounds(100, 100, 700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        explanationTextArea = new JTextArea();
        explanationTextArea.setBounds(50, 250, 550, 100); // Adjust position and size as needed
        explanationTextArea.setEditable(false); // Make it read-only
        explanationTextArea.setLineWrap(true); // Enable line wrapping
        explanationTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        explanationTextArea.setFont(explanationTextArea.getFont().deriveFont(Font.BOLD, 14));
        explanationTextArea.setText("Welcome To Battleship - Here, you will select your settings. 'Ship Set' is from smallest to largest (Stealth, Normal, Massive). Once you have selected your preferred settings, click start to proceed to the 'Ship Placement Phase'.         NOTE - Timer will not take effect until the 'Shooting Phase'. "); // Initial text
        contentPane.add(explanationTextArea);
        
        // Personal Options - MENU
        JLabel personalizeLabel = new JLabel("Personal Options");
        personalizeLabel.setBounds(125, 15, 150, 30);
        contentPane.add(personalizeLabel);
        // Personal Options - MENU
        JLabel p1ColorLabel = new JLabel("Player 1 Ship Color:");
        p1ColorLabel.setBounds(50, 50, 150, 30);
        contentPane.add(p1ColorLabel);
        setP1ColorComboBox(new JComboBox<>(new String[]{"Gray","Green", "Yellow", "Purple", "Orange"}));
        getP1ColorComboBox().setBounds(180, 50, 150, 30);
        contentPane.add(getP1ColorComboBox());
        // Personal Options - MENU
        JLabel p2ColorLabel = new JLabel("Player 2 Ship Color:");
        p2ColorLabel.setBounds(50, 100, 150, 30);
        contentPane.add(p2ColorLabel);
        setP2ColorComboBox(new JComboBox<>(new String[]{"Gray","Green", "Yellow", "Purple", "Orange"}));
        getP2ColorComboBox().setBounds(180, 100, 150, 30);
        contentPane.add(getP2ColorComboBox());

        // Game Options - MENU
        JLabel gameOptionLabel = new JLabel("Game Options");
        gameOptionLabel.setBounds(425, 15, 150, 30);
        contentPane.add(gameOptionLabel);
        // Game Options - MENU
        JLabel boardSizeLabel = new JLabel("Select Board Size:");
        boardSizeLabel.setBounds(350, 50, 150, 30);
        contentPane.add(boardSizeLabel);
        setSelectBoardSizeComboBox(new JComboBox<>(new String[]{"Small (7x7)", "Normal (10x10)", "Large (13x13)"}));
        getSelectBoardSizeComboBox().setBounds(480, 50, 150, 30);
        contentPane.add(getSelectBoardSizeComboBox());
        // Game Options - MENU
        JLabel timerLabel = new JLabel("Select Timer:");
        timerLabel.setBounds(350, 100, 150, 30);
        contentPane.add(timerLabel);
        setSelectTimerComboBox(new JComboBox<>(new String[]{"No Timer", "30 sec", "1 min"}));
        getSelectTimerComboBox().setBounds(480, 100, 150, 30);
        contentPane.add(getSelectTimerComboBox());
        // Game Options - MENU
        JLabel shipSet = new JLabel("Select Ship Set:");
        shipSet.setBounds(350, 150, 150, 30);
        contentPane.add(shipSet);
        setSelectShipSetComboBox(new JComboBox<>(new String[]{"Stealth", "Normal", "Massive"}));
        getSelectShipSetComboBox().setBounds(480, 150, 150, 30);
        contentPane.add(getSelectShipSetComboBox());

        // Start game button
        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(480, 500, 150, 30);
        contentPane.add(startGameButton);
    }
    
    /**
     * Adds an ActionListener to the startGameButton.
     * @param listener The ActionListener to be added.
     */
    public void addStartGameButtonListener(ActionListener listener) {
        startGameButton.addActionListener(listener);
    }
    /**
     * Gets the selected color for Player 1's ships.
     * @return The selected color for Player 1's ships.
     */
    public String getPlayer1Color() {
        return (String) getP1ColorComboBox().getSelectedItem();
    }
    /**
     * Gets the selected color for Player 2's ships.
     * @return The selected color for Player 2's ships.
     */
    public String getPlayer2Color() {
        return (String) getP2ColorComboBox().getSelectedItem();
    }
    /**
     * Gets the selected board size.
     * @return The selected board size.
     */
    public String getSelectedBoardSize() {
        return (String) getSelectBoardSizeComboBox().getSelectedItem();
    }  
    /**
     * Gets the selected timer option.
     * @return The selected timer option.
     */
    public String getSelectedTimer() {
        return (String) getSelectTimerComboBox().getSelectedItem();
    }
    /**
     * Gets the selected ship set.
     * @return The selected ship set.
     */
    public String getSelectedShipSet() {
        return (String) getSelectShipSetComboBox().getSelectedItem();
    }

    /**
     * Retrieves the JComboBox representing the selection of board size.
     * 
     * @return The JComboBox representing the selection of board size.
     */
    public JComboBox<String> getSelectBoardSizeComboBox() {
        return selectBoardSizeComboBox;
    }

    /**
     * Sets the JComboBox for selecting the board size.
     * 
     * @param selectBoardSizeComboBox The JComboBox to set for selecting the board size.
     */
    public void setSelectBoardSizeComboBox(JComboBox<String> selectBoardSizeComboBox) {
        this.selectBoardSizeComboBox = selectBoardSizeComboBox;
    }

    /**
     * Retrieves the JComboBox representing the selection of the timer option.
     * 
     * @return The JComboBox representing the selection of the timer option.
     */
    public JComboBox<String> getSelectTimerComboBox() {
        return selectTimerComboBox;
    }

    /**
     * Sets the JComboBox for selecting the timer option.
     * 
     * @param selectTimerComboBox The JComboBox to set for selecting the timer option.
     */
    public void setSelectTimerComboBox(JComboBox<String> selectTimerComboBox) {
        this.selectTimerComboBox = selectTimerComboBox;
    }

    /**
     * Retrieves the JComboBox representing the selection of the ship set.
     * 
     * @return The JComboBox representing the selection of the ship set.
     */
    public JComboBox<String> getSelectShipSetComboBox() {
        return selectShipSetComboBox;
    }

    /**
     * Sets the JComboBox for selecting the ship set.
     * 
     * @param selectShipSetComboBox The JComboBox to set for selecting the ship set.
     */
    public void setSelectShipSetComboBox(JComboBox<String> selectShipSetComboBox) {
        this.selectShipSetComboBox = selectShipSetComboBox;
    }

    /**
     * Retrieves the JComboBox representing the selection of Player 1's ship color.
     * 
     * @return The JComboBox representing the selection of Player 1's ship color.
     */
    public JComboBox<String> getP1ColorComboBox() {
        return p1ColorComboBox;
    }

    /**
     * Sets the JComboBox for selecting Player 1's ship color.
     * 
     * @param p1ColorComboBox The JComboBox to set for selecting Player 1's ship color.
     */
    public void setP1ColorComboBox(JComboBox<String> p1ColorComboBox) {
        this.p1ColorComboBox = p1ColorComboBox;
    }

    /**
     * Retrieves the JComboBox representing the selection of Player 2's ship color.
     * 
     * @return The JComboBox representing the selection of Player 2's ship color.
     */
    public JComboBox<String> getP2ColorComboBox() {
        return p2ColorComboBox;
    }

    /**
     * Sets the JComboBox for selecting Player 2's ship color.
     * 
     * @param p2ColorComboBox The JComboBox to set for selecting Player 2's ship color.
     */
    public void setP2ColorComboBox(JComboBox<String> p2ColorComboBox) {
        this.p2ColorComboBox = p2ColorComboBox;
    }
}
