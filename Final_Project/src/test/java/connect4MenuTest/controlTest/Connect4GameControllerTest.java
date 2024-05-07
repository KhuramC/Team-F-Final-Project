package connect4MenuTest.controlTest;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connect4Menu.control.Connect4GameController;
import connect4Menu.model.Connect4SettingsModel;

/**
 * Unit testing for Connect4GameController and its methods. Inheritance is required to access the subclasses.
 * @author Khuram C.
 */
class Connect4GameControllerTest extends Connect4GameController {

	private Connect4GameControllerTest controller = null;
	private static Connect4SettingsModel settingsModel = new Connect4SettingsModel();
	
	
	public Connect4GameControllerTest() {
		super(settingsModel);
	}
	

	@BeforeEach
	void setUp() throws Exception {
		settingsModel = new Connect4SettingsModel();
		settingsModel.toggleTimer();
		controller = new Connect4GameControllerTest();
	}

	/**
	 * Test for the parseForColNum method in the SelectionButtonListener. True is expected to be returned, 
	 * so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testParseForColNum() {
		JButton b = new JButton();
		b.setText("Col 3");
		SelectionButtonListener l = new SelectionButtonListener();
		assertTrue(l.parseForColNum(b));
	}
	
	/**
	 * Test for the initate method. We expect true to be returned, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testInitiate() {
		boolean expected = true;
		assertEquals(expected,controller.initiate());
	}

}
