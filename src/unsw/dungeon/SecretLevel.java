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
        this.start();
        getController().pauseTimeline();
    }
}