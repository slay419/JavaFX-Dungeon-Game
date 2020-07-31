package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

    // private VictoryScreenController controller;
    private VictoryScreen victoryScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setController(this);
        // victoryScreen = new VictoryScreen(stage)
        // controller = new VictoryScreenController();
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
        victoryScreen.start();
        // Add victory screen to constructor and call screen.start()
    }

}
