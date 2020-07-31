package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private Stage stage;
    private List<ImageView> initialEntities;
    private Player player;
    private Dungeon dungeon;
    private Level currentLevel;

    private StringProperty timer;
    private Label timerLabel;

    private VictoryScreen victoryScreen;
    private DefeatScreen defeatScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setController(this);
        timer = new SimpleStringProperty(String.valueOf(dungeon.getTimer()));

        Timeline timeline = new Timeline();
        EventHandler<ActionEvent> countdownEvent = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent T) {
                try {
                    countdownTick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        KeyFrame keyframe = new KeyFrame(Duration.millis(1000), countdownEvent);
        timeline.getKeyFrames().add(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        timerLabel = new Label();
        timerLabel.textProperty().bindBidirectional(timer);
        
        squares.add(timerLabel, dungeon.getWidth(), dungeon.getHeight());
        /*
        This will be used for adding an inventory/goals bar
        int y = dungeon.getHeight();
        for (int x = 0; x < 4; x++) {
            squares.add(new ImageView(ground), x, y);
        }
        */
        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setCurrentLevel(Level level) {
        currentLevel = level;
    }

    public void removeImage(Entity entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        for (ImageView i : initialEntities) {
            if (i.getId().equals(entity.getName()) && GridPane.getColumnIndex(i).equals(entityX)
                    && GridPane.getRowIndex(i).equals(entityY)) {
                squares.getChildren().remove(i);
                initialEntities.remove(i);
                break;
            }
        }
    }

    public void openDoor(Entity entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        Image openDoorImage = new Image((new File("images/open_door.png")).toURI().toString());
        ImageView view = new ImageView(openDoorImage);

        for (ImageView i : initialEntities) {
            if (entity.getName().equals("door") && GridPane.getColumnIndex(i).equals(entityX)
                    && GridPane.getRowIndex(i).equals(entityY)) {
                squares.getChildren().remove(i);
                initialEntities.remove(i);
                squares.add(view, entityX, entityY);
                break;
            }
        }

    }

    public void restartLevel() throws IOException {
        String fxmlLevel = currentLevel.getLevel();
        Level level = new Level(stage, fxmlLevel);
        level.start();
    }

    public void goToMainMenu() throws IOException {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.start();
    }

    public void showVictoryScreen() throws IOException {
        victoryScreen = new VictoryScreen(stage, this);
        victoryScreen.setText("Victory!");
        victoryScreen.start();
        // Add victory screen to constructor and call screen.start()
    }

    public void showDefeatScreen() throws IOException {
        victoryScreen = new VictoryScreen(stage, this);
        victoryScreen.setText("Defeat!");
        victoryScreen.start();
    }

    private void countdownTick() throws IOException {
        int timerInt = Integer.parseInt(timer.getValue());
        timerInt--;
        if (timerInt == 0) {
            showDefeatScreen();
        }
        timerLabel.setText(String.valueOf(timerInt));
    }

}
