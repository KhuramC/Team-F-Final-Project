package musicTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import music.MusicLocations;

/**
 * Unit testing for the MusicLocations enum.
 * @author Khuram C.
 */
class MusicLocationsTest {

	/**
	 * Tests the getMusicFilePath with different MusicLocations.
	 * @param location MusicLocation to be tested.
	 * @param expected filePath.
	 * @author Khuram C.
	 */
	@ParameterizedTest
	@MethodSource("provideArgumentsForTestGetMusicFilePath")
	void testGetMusicFilePath(MusicLocations location, String expected) {
		assertEquals(expected,location.getMusicFilePath());
	}
	
	/**
	 * Returns a stream of arrays with each MusicLocation and their expected file paths.
	 * @return stream of MusicLocations with associated file paths.
	 * @author Khuram C.
	 */
	static Stream<Object[]> provideArgumentsForTestGetMusicFilePath(){
		return Stream.of(new Object[]{MusicLocations.MAINMENU,"connect4/music/2-19 Venus Lighthouse.wav"},
				new Object[]{MusicLocations.CONNECT4SETTINGS,"connect4/music/4-14 Trouble is Brewing.wav"},
				new Object[]{MusicLocations.CONNECT4GAME,"connect4/music/4-12 Mars Lighthouse.wav"},
				new Object[]{MusicLocations.CONNECT4WIN,"connect4/music/4-21 The Golden Sun Rises.wav"},
				new Object[]{MusicLocations.CONNECT4TIE,"connect4/music/3-31 Ruins of Lemuria.wav"});	
	}
}
