package connect4Menu.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

/**
 * Timer for one's turn if wanted. It is observable and has multiple observers.
 * @author Khuram C.
 */
public class CountDownTimer extends Observable implements ActionListener {

	private int timerTime;
	private long startTime;
	private long remainingTime;
	//for JUnit testing//
	protected Timer timer;

	/**
	 * A parameterized Constructor. One cannot have a timer without an associated timerTime.
	 * @param timerTime length of the timer.
	 * @author Khuram C.
	 */
	public CountDownTimer(int timerTime) {
		this.timerTime = timerTime;
		this.timer = new Timer(1000, this); //1000 ms or 1 second//
		timer.setInitialDelay(0);
	}

	/**
	 * Starts the timer.
	 * @return boolean detailing success.
	 * @author Khuram C.
	 */
	public boolean startTimer() {
		startTime = System.currentTimeMillis();
		timer.start();
		return true;
	}

	/**
	 * At every regular interval(1 second), it'll calculate how much time is left and notify the observers of such.
	 * If there is no time left, it will stop the timer and also notify the observers of the same thing.
	 * @author Khuram C.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		long currentTime = System.currentTimeMillis();
		long timeDiff = (currentTime - startTime) / 1000;
		remainingTime = timerTime - timeDiff;
		setChanged();
		notifyObservers(remainingTime);
		clearChanged();
		if (remainingTime <= 0) {
			timer.stop();
			setChanged();
			notifyObservers();
			clearChanged();

		}

	}

	/**
	 * Pauses the timer.
	 * @author Khuram C.
	 */
	public void pauseTimer() {
		timer.stop();
	}

}
