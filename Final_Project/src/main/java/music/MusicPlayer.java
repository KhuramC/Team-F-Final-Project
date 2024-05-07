package music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * A Singleton class for playing music. Music files should be .wav and in the resource folder.
 * @author Khuram C.
 */
public class MusicPlayer {

	private Clip musicClip;
	private static MusicPlayer instance = null;
	
	private MusicPlayer() {	
	}
	
	/**
	 * Gives an instance of the MusicPlayer to the user.
	 * @return instance of MusicPlayer.
	 * @author Khuram C.
	 */
	public static MusicPlayer getInstance() {
		if(instance == null) {
			instance = new MusicPlayer();
		}
		return instance;
	}
	
	/**
	 * Attempts to play and loop music at the given path (must be .wav folder).
	 * @param musicFilePath String representing path in the resources folder.
	 * @return boolean detailing success or not.
	 * @author Khuram C.
	 */
	public boolean playMusic(String musicFilePath) {
		try {
			AudioInputStream musicStream =  AudioSystem.getAudioInputStream(
					getClass().getClassLoader().getResourceAsStream(musicFilePath));
			musicClip = AudioSystem.getClip();
			musicClip.open(musicStream);
			musicClip.loop(Clip.LOOP_CONTINUOUSLY);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	/**
	 * Pauses the music if possible. 
	 * @return boolean detailing successful stopping.
	 * @author Khuram C.
	 */
	public boolean pauseMusic() {
		if(musicClip == null) {
			return false;
		}
		musicClip.stop();
		return true;
	}
	
	/**
	 * Resumes the music if possible.
	 * @return boolean detailing successful resuming.
	 * @author Khuram C.
	 */
	public boolean resumeMusic() {
		if(musicClip == null) {
			return false;
		}
		musicClip.loop(Clip.LOOP_CONTINUOUSLY);
		return true;
	}
}
