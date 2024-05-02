import java.util.Random;

import mastermindMenu.gameSettings.GameSettings;

public class MastermindGame {
    private GameSettings settings;
    private String[] secretCode;
    private int currentTry;

    public MastermindGame(GameSettings settings) {
        this.settings = settings;
        this.secretCode = new String[settings.getCodeLength()];
        resetGame();
    }

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