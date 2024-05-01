package connect4Menu.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import connect4Menu.model.Player;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Connect4GameView extends JFrame implements Observer,
ObserverStartedTurn,ObserverSquarePlayed,ObserverEndGame,ObserverInvalidCols{
	
	private final int squareLen = 75;
	private final String emptySquarePath = "/connect4/images/connect4emptysquare.drawio.png";
	private JPanel contentPane = new JPanel();
	private final String p1SquarePath;
	private final String p2SquarePath;
	private JLabel timerLabel;
	private JLabel turnLabel;
	private JLabel endLabel;
	//private JLabel 
	private JLabel[][] board;
	private JButton[] selectionButtons;

	public Connect4GameView(int rowNum, int colNum, int timerTime, String p1FilePath, String p2FilePath) {
		p1SquarePath = p1FilePath;
		p2SquarePath = p2FilePath;
		setTitle("Connect 4"); //general settings//
		//setBounds(300,0,1000,1000);
		int windowWidth = squareLen*(colNum+2);
		int windowHeight= squareLen*(rowNum+3);
		int timerLabelx = windowWidth-175;
		int turnLabelx = 75;
		int selectionButtonSize = 21;
		setBounds(300,0,windowWidth,windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		this.timerLabel = new JLabel("Time left: " + Integer.toString(timerTime));
		timerLabel.setSize(200, 52);
		timerLabel.setLocation(timerLabelx, squareLen-selectionButtonSize);
		timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		timerLabel.setVisible(true);
		contentPane.add(timerLabel);
		
		this.turnLabel = new JLabel("Player 1's Turn");
		turnLabel.setSize(200, 52);
		turnLabel.setLocation(turnLabelx, squareLen-selectionButtonSize);
		turnLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		turnLabel.setVisible(true);
		contentPane.add(turnLabel);
		
		this.endLabel = new JLabel("");
		endLabel.setSize(200, 52);
		endLabel.setLocation(windowWidth/2, squareLen-selectionButtonSize);
		endLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		endLabel.setVisible(true);
		contentPane.add(endLabel);
		

		ImageIcon emptySquare = new ImageIcon(getClass().getResource(emptySquarePath));
		this.board = new JLabel[rowNum][colNum];
		this.selectionButtons = new JButton[colNum];
		
		for(int col = 0; col < board[0].length; col++) {
			JButton b = new JButton("Col " + Integer.toString(col+1));
			b.setBounds(squareLen*(col+1), (squareLen*2)-selectionButtonSize, squareLen, selectionButtonSize);
			contentPane.add(b);
			selectionButtons[col] = b;
			for(int row = 0; row < board.length; row++) {
				JLabel testsquare = new JLabel(emptySquare);
//				if(row + col == 2) {
//					testsquare = new JLabel(new ImageIcon(getClass().getResource(p1SquarePath)));
//					
//				}
//				if(row + col == 3) {
//					testsquare = new JLabel(new ImageIcon(getClass().getResource(p2SquarePath)));
//					
//				}
				
				testsquare.setBounds(squareLen*(col+1), squareLen*(row+2), squareLen, squareLen);
				contentPane.add(testsquare);
				board[row][col] = testsquare;
			}
			
		}
//		JLabel testsquare = new JLabel(emptySquare);
//		testsquare.setBounds(75, 75, squareLen, squareLen);
//		contentPane.add(testsquare);

	}
	
	public void setTimerLabelVisibility(boolean bool) {
		timerLabel.setVisible(bool);
		
	}
	
	public void addListenertoSelectionButtons(ActionListener listener) {
		for(JButton b: selectionButtons) {
			b.addActionListener(listener);
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg!=null) {
			long remainingTime = (long) arg;
			timerLabel.setText("Time left: " + Long.toString(remainingTime));
			timerLabel.setVisible(true);
			
		}else {
			//for whenever time runs out
		}
		
	}

	@Override
	//from observerStartedTurn
	public void updatePlayerTurnText(Player p) {
		turnLabel.setText(p.getName() +"'s Turn");
		turnLabel.setVisible(true);
	}

	@Override
	public void updateBoard(Player p, int[] selection) {
		String playerFilePath = p.getColor().getFilePath();
		ImageIcon playerSquare = new ImageIcon(getClass().getResource(playerFilePath));
		int row = selection[0];
		int col = selection[1];
		board[row][col].setIcon(playerSquare);
		
	}

	@Override
	public void updateText(Player p) {
		if(p == null) {
			endLabel.setText("It was a tie!");
			
		}else {
			endLabel.setText(p.getName() +" won!");
		}
		disableButtons();
		
		
		
	}
	
	private void disableButtons() {
		for(JButton b : selectionButtons) {
			b.setEnabled(false);
		}
	}

	@Override
	public void updateButton(int invalidColNum) {
		selectionButtons[invalidColNum].setEnabled(false);
		
	}
}
