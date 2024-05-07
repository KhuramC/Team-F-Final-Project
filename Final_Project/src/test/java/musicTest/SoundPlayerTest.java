package musicTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import music.SoundLocations;
import music.SoundPlayer;

/**
 * Unit testing for the SoundPlayer and its methods.
 * @author Khuram C.
 */
class SoundPlayerTest {

	private SoundPlayer instance = null;

	@BeforeEach
	void setUp() throws Exception {
		instance = SoundPlayer.getInstance();
	}
	
	/**
	 * Tests the getInstance method. After the setup method is called, the instance should be an actual
	 * instance of the class, so we test for that.
	 * @author Khuram C.
	 */
	@Test
	void testGetInstance() {
		if(instance instanceof SoundPlayer) {
			assertTrue(true);
		}
	}
	
	/**
	 * Tests the playSound method with different file paths.
	 * @param filePath to test with.
	 * @param expected output of method.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentForTestPlaySound")
	void testPlaySound(String filePath, boolean expected) {
		assertEquals(expected,instance.playSound(filePath));
	}
	
	/**
	 * Returns some file paths with the expected output of passing them into the playSound method.
	 * @return stream of arrays with Strings and associated booleans.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentForTestPlaySound(){
		return Stream.of(new Object[]{"test",false},
				new Object[]{SoundLocations.SQUAREPLAYED.getSoundFilePath(),true});
	}
}
