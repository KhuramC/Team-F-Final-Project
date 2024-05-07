package musicTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import music.MusicLocations;
import music.MusicPlayer;

/**
 * Unit testing for the MusicPlayer and its methods.
 */
class MusicPlayerTest {

	private MusicPlayer instance = null;

	@BeforeEach
	void setUp() throws Exception {
		instance = MusicPlayer.getInstance();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		instance = null;
	}

	@Test
	void testGetInstance() {
		if(instance instanceof MusicPlayer) {
			assertTrue(true);
		}
	}
	
	
	
	/**
	 * Tests the playMusic method with different file paths.
	 * @param filePath to test with.
	 * @param expected output of method.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentForTestPlayMusic")
	void testPlayMusic(String filePath, boolean expected) {
		assertEquals(expected,instance.playMusic(filePath));
	}
	
	/**
	 * Returns some file paths with the expected output of passing them into the playMusic method.
	 * @return stream of arrays with Strings and associated booleans.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentForTestPlayMusic(){
		return Stream.of(new Object[]{"test",false},
				new Object[]{MusicLocations.CONNECT4SETTINGS.getMusicFilePath(),true});
	}
	
	/**
	 * Tests the pauseMusic method. Should return true while music is playing.
	 * @author Khuram C.
	 */
	@Test
	void testPauseMusic() {
		instance.playMusic(MusicLocations.CONNECT4SETTINGS.getMusicFilePath());
		assertEquals(true,instance.pauseMusic());
	}
	
	/**
	 * Tests the resumeMusic method. Should return true when music has now paused.
	 * @author Khuram C.
	 */
	@Test
	void testResumeMusic() {
		instance.playMusic(MusicLocations.CONNECT4SETTINGS.getMusicFilePath());
		instance.pauseMusic();
		assertEquals(true,instance.resumeMusic());
	}
	
	
	
	
	

}
