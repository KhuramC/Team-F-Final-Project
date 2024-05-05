package connect4MenuTest.viewTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.view.Connect4SettingsView;

/**
 * Unit testing for Connect4SettingsView and its methods. Due to restrictions placed on the class, the view needs to be extended
 * to test certain methods. the addListener methods are tested (indirectly) within the Connect4SettingsController class.
 * @author Khuram C.
 */
class Connect4SettingsViewTest extends Connect4SettingsView{
	
	private Connect4SettingsViewTest view = null;

	@BeforeEach
	void setUp() throws Exception {
		view = new Connect4SettingsViewTest();
	}

	/**
	 * Tests the getTimerTextFieldValue method. The default is the minimum timer time, so that is what we expect.
	 * @author Khuram C.
	 */
	@Test
	void testGetTimerTextFieldValue() {
		String expected = Integer.toString(Connect4SettingsModel.minTimerTime);
		assertEquals(expected,view.getTimerTextFieldValue());
	}
	
	/**
	 * Tests the getTimerSliderValue method. The default is the minimum timer time, so that is what we expect.
	 * @author Khuram C.
	 */
	@Test
	void testGetTimerSliderValue() {
		int expected = Connect4SettingsModel.minTimerTime;
		assertEquals(expected,view.getTimerSliderValue());
	}
	
	/**
	 * Tests the changeErrorLabelText method. This requires inheriting from the Connect4SettingsView to get access to it.
	 * @author Khuram C.
	 */
	@Test
	void testChangeErrorLabelText() {
		String expected = "test";
		view.changeErrorLabelText(expected);
		assertEquals(expected,view.errorLabel.getText());
	}
	
	/**
	 * Tests the branch of the update method in which a boolean is passed. The error label's visibility is directly tied to it
	 * so we should be able to test based on that. This requires inheriting from the Connect4SettingsView to get access to it.
	 * @param expected visibility of errorLabel.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestUpdateIsTimer")
	void testUpdateIsTimer(boolean expected) {
		view.update(null, expected);
		assertEquals(expected,view.errorLabel.isVisible());
	}
	
	/**
	 * Returns a stream of true and false.
	 * @return true and false, in that order.
	 * @author Khuram C.
	 */
	static Stream<Boolean> provideArgumentsforTestUpdateIsTimer(){
		return Stream.of(new Boolean[] {true,false});
	}
	
	/**
	 * Tests the branch of the update method in which an int is passed. The timerSlider is supposed to be updated, so we should
	 * be able to see a change from it.
	 * @author Khuram C.
	 */
	@Test
	void testUpdateTimerTime() {
		int expectedTimerTime = 23;
		view.update(null,expectedTimerTime);
		assertEquals(expectedTimerTime,view.getTimerSliderValue());
	}

}
