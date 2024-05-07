package music;

/**
 * An enum representing the filepaths for music for different times.
 * @author Khuram C.
 */
public enum MusicLocations {
	//All from Golden Sun 1 & 2 from the GBA//
	MAINMENU("connect4/music/2-19 Venus Lighthouse.wav"),
	CONNECT4SETTINGS("connect4/music/4-14 Trouble is Brewing.wav"),
	CONNECT4GAME("connect4/music/4-12 Mars Lighthouse.wav"),
	CONNECT4WIN("connect4/music/4-21 The Golden Sun Rises.wav"),
	CONNECT4TIE("connect4/music/3-31 Ruins of Lemuria.wav");
	
	
	private String musicFilePath;
	
	/**
	 * Private Constructor to ensure every enum has an associated file path for music.
	 * @param s String for music.
	 * @author Khuram C.
	 */
	private MusicLocations(String s) {
		musicFilePath = s;
	}
	
	/**
	 * Gives the file path for the associated music time/location.
	 * @return String showing music file path.
	 * @author Khuram C.
	 */
	public String getMusicFilePath() {
		return musicFilePath;
	}

}
