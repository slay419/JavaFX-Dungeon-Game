package unsw.dungeon;

import java.io.IOException;

import javafx.stage.Stage;

public class SecretLevel extends Level {

    String currentLevel;
    
    public SecretLevel(Stage stage, String level) throws IOException {
        super(stage, level);
        currentLevel = level;
        getController().setSecretLevel(this);
    }

    public void startSecretLevel(String level) throws IOException {
        Level secretLevel = new Level(getStage(), level);
        secretLevel.setSecret(true);
        secretLevel.start();
        getController().pauseTimeline();
    }

    public void showNextSecretLevel() {
        if (currentLevel.equals("secretLevel1.json")) {
            System.out.println("trying to find next level");
        }
    }
}