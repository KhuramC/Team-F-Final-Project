package music;

/**
 * An enum representing the filepaths of sounds for different occasions.
 * @author Khuram C.
 */
public enum SoundLocations {
	//found at https://freesound.org/people/discokingmusic/sounds/271387/
	SQUAREPLAYED("connect4/music/placeConnect4Disc.wav");
	
	private String soundFilePath;
	
	
	/**
	 * Private Constructor to ensure every enum has an associated file path for sounds.
	 * @param sfp String for sounds.
	 * @author Khuram C.
	 */
	private SoundLocations(String sfp) {
		soundFilePath = sfp;
	}
	
	/**
	 * Gives the file path for the associated sound.
	 * @return String showing sound file path.
	 * @author Khuram C.
	 */
	public String getSoundFilePath() {
		return soundFilePath;
	}
}
