package musicTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import music.SoundLocations;

/**
 * Unit testing for the SoundLocations enum.
 * @author Khuram C.
 */
class SoundLocationsTest {



	/**
	 * Tests the getSoundFilePath method with the only SoundLocation.
	 * @author Khuram C.
	 */
	@Test
	void testGetSoundFilePath() {
		String expected = "connect4/music/placeConnect4Disc.wav";
		assertEquals(expected,SoundLocations.SQUAREPLAYED.getSoundFilePath());
	}

}
