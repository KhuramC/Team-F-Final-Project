package music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A Singleton class for playing shorter sound effects. Sound files should be .wav and in the resource folder.
 * @author Khuram C.
 */
public class SoundPlayer {
	
	private static SoundPlayer instance = null;

	private SoundPlayer() {
	}
	
	/**
	 * Gives an instance of the SoundPlayer to the user.
	 * @return instance of SoundPlayer.
	 * @author Khuram C.
	 */
	public static SoundPlayer getInstance() {
		if(instance == null) {
			instance = new SoundPlayer();
		}
		return instance;
	}
	
	/**
	 * Attempts to play a sound at the given path (must be .wav folder).
	 * @param soundFilePath String representing path in resources folder.
	 * @return boolean detailing success or not.
	 * @author Khuram C.
	 */
	public boolean playSound(String soundFilePath) {
		try {
			AudioInputStream musicStream =  AudioSystem.getAudioInputStream(
					getClass().getClassLoader().getResourceAsStream(soundFilePath));
			Clip soundClip = AudioSystem.getClip();
			soundClip.open(musicStream);
			soundClip.loop(0);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
}
