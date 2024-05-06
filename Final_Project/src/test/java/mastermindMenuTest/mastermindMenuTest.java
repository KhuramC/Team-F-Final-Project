package mastermindMenuTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import mastermindMenu.MastermindMenu;

/** 
 * @author Alon B.
 */
class mastermindMenuTest {

	/** 
	 * Made to test the one method in menu that is testable.
	 */
	@Test
	void testStartMastermindRuns() {
		assertDoesNotThrow(() -> MastermindMenu.startMastermind());
	}
}