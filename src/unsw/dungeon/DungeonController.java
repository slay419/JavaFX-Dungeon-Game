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
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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

    private Timeline timeline;
    private StringProperty timer;
    private Label timerLabel;

    private Label treasureCount = new Label("0");
    private Label swordCharges = new Label("0");
    private Label potionCharges = new Label("0");

    private VictoryScreen victoryScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setController(this);
        timer = new SimpleStringProperty(String.valueOf(dungeon.getTimer()));

        timeline = new Timeline();
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
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // initialise goal 

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        timerLabel = new Label();
        timerLabel.textProperty().bindBidirectional(timer);
        
        squares.add(timerLabel, dungeon.getWidth(), dungeon.getHeight());
        
        //This will be used for adding an inventory/goals bar
        //Silhouettes
        Image keyDark = new Image((new File("images/keyDark.png")).toURI().toString());
        Image treasureDark = new Image((new File("images/treasureDark.png")).toURI().toString());
        Image swordDark = new Image((new File("images/swordDark.png")).toURI().toString());
        Image potionDark = new Image((new File("images/potionDark.png")).toURI().toString());

        int floor = dungeon.getHeight();

        squares.add(new ImageView(keyDark), 0, floor);
        squares.add(new ImageView(treasureDark), 1, floor);
        squares.add(new ImageView(swordDark), 2, floor);
        squares.add(new ImageView(potionDark), 3, floor);
        squares.add(treasureCount, 1, floor + 1);
        squares.add(swordCharges, 2, floor + 1);
        squares.add(potionCharges, 3, floor + 1);

        //Add pause button
        Button pause = new Button("Pause");
        squares.add(pause, 4, dungeon.getHeight(), 2, 1);
        pause.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent T){
                try {
                    showPauseScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                };
            }
        });
        TreeItem<String> goals = new TreeItem<String>("Goals to do!");
        goals.setExpanded(true);
        
        List<CompositeGoal> goalList = dungeon.getCompositeGoals();
        for (CompositeGoal g : goalList) {
            goals.getChildren().add(new TreeItem<String>(g.getName()));
        }
        TreeView<String> treeView = new TreeView<String>(goals);
        squares.add(treeView, dungeon.getWidth(), 0, 1, dungeon.getHeight());

        /*
        for (CompositeGoal g : goalList) {
            System.out.println("goal: " + g.getName());
            TreeView<String> treeView = new TreeView<String>(new TreeItem<String>(g.getName()));
            squares.add(treeView, dungeon.getWidth(), 0, 1, dungeon.getHeight());
            //goals.getChildren().add(new TreeItem<String>(g.getName()));
        }
        */
        /*
        goals.getChildren().addAll(
            new TreeItem<String>("Test \u2713"),
            new TreeItem<String>("Item 2")
            //new TreeItem<String>("Item 3")
        );
        
        
        
        //Add goals
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
        timeline.stop();
        victoryScreen = new VictoryScreen(stage, this);
        victoryScreen.setText("Victory!");
        victoryScreen.start();
    }

    public void showDefeatScreen() throws IOException {
        timeline.stop();
        victoryScreen = new VictoryScreen(stage, this);
        victoryScreen.setText("Defeat!");
        victoryScreen.start();
    }

    public void showPauseScreen() throws IOException {
        timeline.stop();
        victoryScreen = new VictoryScreen(stage, this);
        victoryScreen.setText("Paused");
        victoryScreen.start();
    }

    private void countdownTick() throws IOException {
        int timerInt = Integer.parseInt(timer.getValue());
        //System.out.println("time left: " + timerInt);
        timerInt--;
        if (timerInt == 0) {
            System.out.println("Ran out of time!");
            showDefeatScreen();
        }
        timerLabel.setText(String.valueOf(timerInt));
    }

    public void startCountdown() {
        timeline.play();
    }

    public void addImage(ImageView image, int x) {
        squares.add(image, x, dungeon.getHeight());
    }

    public void removeImage(ImageView image, int x){
        squares.getChildren().remove(image);
    }

    public void updateChargesTreasureUI(){
        int prevCount = Integer.valueOf(treasureCount.getText());
        System.out.println("Prev count is: " + prevCount);
        int newCount = prevCount + 1;
        System.out.println("New count is : " + newCount);
        treasureCount.setText(String.valueOf(newCount));
    }
    
    public void updateChargesSwordUI(int charges){
        swordCharges.setText(Integer.toString(charges));
    }

    public void updateChargesPotionUI(int charges){
        potionCharges.setText(Integer.toString(charges));
    }

}
