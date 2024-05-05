package mastermindMenu.MastermindGame;

import java.util.Random;

import mastermindMenu.gameSettings.GameSettings;


/******
 * @author Alon B.
 * This class represents the game logic for a Mastermind game session, managing the secret code,
 * the number of attempts, and game settings.
 ****/
public class MastermindGame {
    private GameSettings settings;
    private String[] secretCode;
    private int currentTry;
    
    
    /**
     * Constructs a Mastermind game instance with specific game settings.
     * @param settings The game settings including code length and color options.
     */
    public MastermindGame(GameSettings settings) {
        this.settings = settings;
        this.secretCode = new String[settings.getCodeLength()];
        resetGame();
    }
    /**
     * Resets the game by generating a new secret code and resetting the number of tries.
     * The secret code is randomly generated based on the color options provided in the game settings.
     */
    public void resetGame() {
        Random random = new Random();
        for (int i = 0; i < settings.getCodeLength(); i++) {
            secretCode[i] = settings.getColors()[random.nextInt(settings.getColors().length)];
        }
        currentTry = 0;
    }

    public String[] getSecretCode() {
        return secretCode;
    }

    public int getCurrentTry() {
        return currentTry;
    }

    public void incrementCurrentTry() {
        currentTry++;
    }

    public GameSettings getSettings() {
        return settings;
    }
}