package connect4MenuTest.controlTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import connect4Menu.control.Connect4SettingsController;
import connect4Menu.model.Connect4SettingsModel;
import connect4Menu.model.player.IPlayerColors;
import connect4Menu.model.player.Player1Colors;

/**
 * Unit testing for the Connect4SettingsController. Inheritance is required to get access to the subclasses.
 * @author Khuram C.
 */
class Connect4SettingsControllerTest extends Connect4SettingsController {
	
	private Connect4SettingsControllerTest controller;

	@BeforeEach
	void setUp() throws Exception {
		controller = new Connect4SettingsControllerTest();
	}

	/**
	 * Tests the testParseForTime method with different inputs. What is returned changes depending on what occurs and is 
	 * compared with what is expected.
	 * @param expected text returned.
	 * @param timerTextFieldText text held in the timerTextField at the time.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestParseForTime")
	void testParseForTime(String expected, String timerTextFieldText) {
		TimerTextFieldListener l = new TimerTextFieldListener();
		String actual = l.parseForTimerTime(timerTextFieldText);
		assertEquals(expected,actual);
	}
	
	
	/**
	 * Provides a stream of string arrays.
	 * @return stream of string arrays.
	 * @author Khuram C.
	 */
	static Stream<String[]> provideArgumentsforTestParseForTime(){
		String invalidTimerTimeExceptionText = "Put in an integer from " + Integer.toString(Connect4SettingsModel.minTimerTime) 
				+ " to " + Integer.toString(Connect4SettingsModel.maxTimerTime) + "s.";
		
		return Stream.of(new String[]{"",Integer.toString(Connect4SettingsModel.minTimerTime)},
				new String[]{invalidTimerTimeExceptionText,Integer.toString(Connect4SettingsModel.minTimerTime-1)},		
				new String[]{"",Integer.toString(Connect4SettingsModel.maxTimerTime)},	
				new String[]{invalidTimerTimeExceptionText,Integer.toString(Connect4SettingsModel.maxTimerTime+1)},
				new String[]{"Put in an integer!","failure test"});
	}
	
	/**
	 * Tests the changeColor method. We expecte true to be returned, so that is what we assert.
	 * @author Khuram C.
	 */
	@Test
	void testChangeColor() {
		JComboBox<IPlayerColors> box = new JComboBox<>();
		box.setModel(new DefaultComboBoxModel<>(Player1Colors.values()));
		PlayerColorsComboBoxListener l = new PlayerColorsComboBoxListener();
		assertTrue(l.changeColor(box));
		
	}
	
	/**
	 * Tests the startConnect4Round method. We expect true to be returned, so that is what we assert.
	 * @author Khuram C.
	 */
	@Test
	void testStartConnect4Round() {
		StartGameButtonListener l = new StartGameButtonListener();
		assertTrue(l.startConnect4Round());
		
	}
	
	/**
	 * Tests the parseButtontoSetBoardSize method with the different sizes available.
	 * @param text text of button.
	 * @param expected output of method.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsforTestParseButtontoSetBoardSize")
	void testParseButtontoSetBoardSize(String text, boolean expected) {
		JRadioButton b = new JRadioButton();
		b.setText(text);
		SizeRadioButtonListener l = new SizeRadioButtonListener();
		assertTrue(l.parseButtontoSetBoardSize(b));	
	}
	
	/**
	 * Returns a stream of arrays of the different sizes with true booleans.
	 * @return stream of strings with true booleans.
	 * @author Khuram c.
	 */
	static Stream<Object[]> provideArgumentsforTestParseButtontoSetBoardSize(){
		return Stream.of(new Object[]{"4x5",true},
				new Object[]{"5x6",true},
				new Object[]{"6x7(Standard)",true},
				new Object[]{"7x8",true},
				new Object[]{"8x8",true},
				new Object[]{"7x9",true});
	}
	
	/**
	 * Tests the initiate method within the controller. We expect true to be returned, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testInitiate() {
		boolean expected = controller.initiate();
		assertTrue(expected);
	}
	


}
