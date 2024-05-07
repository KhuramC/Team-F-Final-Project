package connect4MenuTest.modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connect4Menu.model.CountDownTimer;

/**
 * Unit testing for the CountDownTimer and its methods. Due to 
 * @author Khuram C.
 */
class CountDownTimerTest extends CountDownTimer {
	
	private CountDownTimerTest downTimer = null;
	private static int timerTime = 3;

	public CountDownTimerTest() {
		super(timerTime);
	}

	@BeforeEach
	void setUp() throws Exception {
		downTimer = new CountDownTimerTest();
	}

	/**
	 * Tests the StartTimer method. We expect the timer to be running, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testStartTimer() {
		boolean expected = true;
		downTimer.startTimer();
		assertEquals(expected,downTimer.timer.isRunning());
	}
	
	/**
	 * Tests the actionPerformed method. After the timerTime gets to 0 (so after at least 3 seconds, but thread.sleep isn't always
	 * consistent so we make it 5 just in case), we expect the timer to not be running, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testActionPerformed() {
		downTimer.startTimer();
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
		}
		boolean expected = false;
		assertEquals(expected,downTimer.timer.isRunning());
		
	}
	
	/**
	 * Tests the pauseTimer method. We expect the timer to not be running, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testPauseTimer() {
		boolean expected = false;
		downTimer.startTimer();
		downTimer.pauseTimer();
		assertEquals(expected,downTimer.timer.isRunning());
	}

}
