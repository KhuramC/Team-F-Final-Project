package connect4Menu.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Timer;

public class CountDownTimer extends Observable implements ActionListener {

	private int timerTime;
	private long startTime;
	private long remainingTime;
	private Timer timer;

	public CountDownTimer(int timerTime) {
		this.timerTime = timerTime;
		this.timer = new Timer(1000, this);
		timer.setInitialDelay(0);
	}

	public void startTimer() {
		startTime = System.currentTimeMillis();
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		long currentTime = System.currentTimeMillis();
		long timeDiff = (currentTime - startTime) / 1000;
		this.remainingTime = timerTime - timeDiff;
		setChanged();
		notifyObservers(remainingTime);
		clearChanged();
		System.out.println(remainingTime);
		if (remainingTime <= 0) {
			timer.stop();
			setChanged();
			notifyObservers();
			clearChanged();

		}

	}

	public void pauseTimer() {
		timer.stop();
	}

}
