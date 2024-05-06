package connect4Menu.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import connect4Menu.model.player.Player;
import connect4Menu.view.observerinterfaces.*;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * A view from the MVC architecture used for actually playing a round of Connect4. The view observes the CountDownTimer
 * and the Connect4GameModel. Certain fields are protected instead of private to allow for testing.
 * @author Khuram C.
 */
public class Connect4GameView extends JFrame
		implements Observer, IStartedTurnObserver, ISquarePlayedObserver, IEndGameObserver, IInvalidColsObserver {

	private final int squareLen = 75;
	private final String emptySquarePath = "/connect4/images/connect4emptysquare.drawio.png";
	private JPanel contentPane = new JPanel();
	//for JUnit testing//
	protected JLabel timerLabel;
	protected JLabel endLabel;
	private JLabel turnLabel;
	private JLabel[][] board;
	private JButton[] selectionButtons;

	public Connect4GameView(int rowNum, int colNum, int timerTime) {

		setTitle("Connect 4"); // general settings//
		int windowWidth = squareLen * (colNum + 2);
		int windowHeight = squareLen * (rowNum + 3);
		
		
		
		setBounds(400, 0, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		int selectionButtonSize = 21;
		
		int timerLabelWidth = 85;
		int timerLabelx = windowWidth - squareLen-timerLabelWidth;
		this.timerLabel = new JLabel("Time left: " + Integer.toString(timerTime));
		timerLabel.setSize(timerLabelWidth, 52);
		timerLabel.setLocation(timerLabelx, squareLen - selectionButtonSize);
		timerLabel.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
		timerLabel.setVisible(true);
		contentPane.add(timerLabel);
		
		int turnLabelx = squareLen;
		this.turnLabel = new JLabel("Player 1's Turn");
		turnLabel.setSize(200, 52);
		turnLabel.setLocation(turnLabelx, squareLen - selectionButtonSize);
		turnLabel.setFont(new Font("Britannic Bold", Font.PLAIN, 14));
		turnLabel.setVisible(true);
		contentPane.add(turnLabel);

		int middleX = windowWidth/2;
		int endLabelWidth = 200;
		this.endLabel = new JLabel("");
		endLabel.setHorizontalAlignment(SwingConstants.CENTER);
		endLabel.setSize(endLabelWidth, 52);
		endLabel.setLocation(middleX-endLabelWidth/2 , squareLen - selectionButtonSize);
		endLabel.setFont(new Font("Britannic Bold", Font.ITALIC, 25));
		endLabel.setVisible(true);
		contentPane.add(endLabel);

		ImageIcon emptySquare = new ImageIcon(getClass().getResource(emptySquarePath));
		this.board = new JLabel[rowNum][colNum];
		this.selectionButtons = new JButton[colNum];
		
		for (int col = 0; col < board[0].length; col++) {
			JButton b = new JButton("Col " + Integer.toString(col + 1));
			b.setBounds(squareLen * (col + 1), (squareLen * 2) - selectionButtonSize, squareLen, selectionButtonSize);
			contentPane.add(b);
			selectionButtons[col] = b;
			for (int row = 0; row < board.length; row++) {
				JLabel testsquare = new JLabel(emptySquare);
				testsquare.setBackground(getBackground());
				testsquare.setBounds(squareLen * (col + 1), squareLen * (row + 2), squareLen, squareLen);
				contentPane.add(testsquare);
				board[row][col] = testsquare;
			}
		}
	}

	

	/**
	 * Adds a listener to the selectionButtons for whenever a column is selected.
	 * @param listener to listen to the SelectionButtons.
	 * @return boolean detailing successful adding.
	 * @author Khuram C.
	 */
	public boolean addListenertoSelectionButtons(ActionListener listener) {
		for (JButton b : selectionButtons) {
			b.addActionListener(listener);
		}
		return true;
	}

	/**
	 * Sets the visibility of the timerLabel based on the input.
	 * @param bool detailing timer visibility.
	 * @return boolean detailing successful setting.
	 * @author Khuram C.
	 */
	public boolean setTimerLabelVisibility(boolean bool) {
		timerLabel.setVisible(bool);
		return true;
	}
	
	
	/**
	 * Update from the CountDownTimer. Every regular interval(1 second in this case), the timer is visually updated.
	 * @author Khuram C.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {
			long remainingTime = (long) arg;
			timerLabel.setText("Time left: " + Long.toString(remainingTime));
			timerLabel.setVisible(true);
		}
	}

	/**
	 * Updates the turnLabel to say who's turn it is.
	 */
	@Override
	public boolean updatePlayerTurnText(Player p) {
		turnLabel.setText(p.getName() + "'s Turn");
		turnLabel.setVisible(true);
		return true;
	}

	/**
	 * Updates the given cell location with the Player's specified colored circle.
	 */
	@Override
	public boolean updateBoard(Player p, int[] selection) {
		String playerFilePath = p.getColor().getFilePath();
		ImageIcon playerSquare = new ImageIcon(getClass().getResource(playerFilePath));
		int row = selection[0];
		int col = selection[1];
		board[row][col].setIcon(playerSquare);
		return true;
	}

	/*
	 * Sets the turn and timerLabel invisible and then states who won or if a tie occurred.
	 */
	@Override
	public boolean updateTextWithWinner(Player p) {
		turnLabel.setVisible(false);
		timerLabel.setVisible(false);
		if (p == null) {
			endLabel.setText("It was a tie!");

		} else {
			endLabel.setText(p.getName() + " won!");
		}
		disableButtons();
		return true;
	}

	/**
	 * Disables all selectionButtons.
	 * @return boolean detailing successful disabling.
	 * @author Khuram C.
	 */
	private boolean disableButtons() {
		for (JButton b : selectionButtons) {
			b.setEnabled(false);
		}
		return true;
	}

	/**
	 * Disables the specified not invalid selectionButton.
	 */
	@Override
	public boolean updateButton(int invalidColNum) {
		selectionButtons[invalidColNum].setEnabled(false);
		return true;
	}
}
