package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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

    private Label tutorialText = new Label();
    private Boolean isTutorial = false;
    private int tutorialNumber = 0;
    private SecretLevel secretLevel;

    private VictoryScreen victoryScreen;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setController(this);
        timer = new SimpleStringProperty("Time remaining: " + String.valueOf(dungeon.getTimer()));

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
        
        initialiseTimer();
        initialiseInventory();
        initialisePauseButton();
        initialiseGoalsUI();        

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }

    }

    private void initialiseTimer() {
        timerLabel = new Label();
        timerLabel.textProperty().bindBidirectional(timer);
        timerLabel.setAlignment(Pos.CENTER_RIGHT);
        timerLabel.setMinWidth(115);
        squares.add(timerLabel, dungeon.getWidth(), 0, 1, 1);
    }

    private void initialiseInventory() {
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
    }

    private void initialisePauseButton() {
        Button pause = new Button("Pause");
        squares.add(pause, dungeon.getWidth() - 2, dungeon.getHeight(), 2, 1);
        pause.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent T){
                try {
                    showPauseScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                };
            }
        });
    }

    public void resumeLevel() {
        currentLevel.start();
    }

    private void initialiseGoalsUI() {
        TreeView<String> treeView = new TreeView<String>();
        List<CompositeGoal> compositeGoals = dungeon.getCompositeGoals();
        CompositeGoal compositeGoal = compositeGoals.get(compositeGoals.size() - 1);

        TreeItem<String> rootGoal = new TreeItem<String>("Complete these goals!");
        rootGoal.setExpanded(true);
        
        TreeItem<String> subGoal = subTreeItem(rootGoal, compositeGoal);
        rootGoal.getChildren().add(subGoal);
        treeView.setRoot(rootGoal);

        squares.add(treeView, dungeon.getWidth(), 1, 1, dungeon.getHeight() + 1);
    }

    private TreeItem<String> subTreeItem(TreeItem<String> root, Goal goal) {
        if (goal instanceof CompositeGoal) {
            TreeItem<String> newRoot = new TreeItem<String>("Complete ALL of these");
            CompositeGoal g = (CompositeGoal) goal;
            for (Goal subGoal : g.getSubGoals()) {
                newRoot.getChildren().add(subTreeItem(newRoot, subGoal));
                newRoot.setExpanded(true);
            }
            root.getChildren().add(newRoot);
            root.setExpanded(true);
        } else if (goal instanceof CompositeOrGoal) {
            TreeItem<String> newRoot = new TreeItem<String>("Complete AT LEAST ONE of these");
            CompositeOrGoal g = (CompositeOrGoal) goal;
            for (Goal subGoal : g.getSubGoals()) {
                newRoot.getChildren().add(subTreeItem(newRoot, subGoal));
                newRoot.setExpanded(true);
            }
            root.getChildren().add(newRoot);
        } else {
            // Change the string name in this one
            String goalText = processGoalName(goal);
            TreeItem<String> leafGoal = new TreeItem<String>(goalText);
            return leafGoal;
        }
        return null;
    }

    private String processGoalName(Goal goal) {
        String goalName = goal.getName();
        String result = "";
        switch (goalName) {
        case "enemies":
            int enemyCount = dungeon.findEntities("enemy").size();
            if (enemyCount > 1) {
                result = "Kill " + enemyCount + " enemies!";
            } else if (enemyCount == 1) {
                result = "Kill the enemy!";
            }
            break;
        case "boulders":
            int switchCount = dungeon.findEntities("floorSwitch").size();
            result = "Trigger the " + switchCount + " switches!";
            break;
        case "treasure":
            int treasureCount = dungeon.findEntities("treasure").size();
            if (treasureCount > 1) {
                result = "Collect the " + treasureCount + " treasure piles!";
            } else if (treasureCount == 1) {
                result = "Collect the treasure pile!";
            }
            break;
        case "exit":
            result = "Find the exit!";
            break;
        }
        return result;
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
        if(isTutorial){
            tutorialNumber = tutorialNumber - 1;
            showTutorialScreen(tutorialNumber);
        }
        else{
            String fxmlLevel = currentLevel.getLevel();
            Level level = new Level(stage, fxmlLevel);
            level.start();
        }
    }

    public void goToMainMenu() throws IOException {
        MainMenu mainMenu = new MainMenu(stage);
        mainMenu.start();
    }

    public void showVictoryScreen() throws IOException {
        if(isTutorial && tutorialNumber < 11){
            showTutorialScreen(tutorialNumber);
        } 
        else{
            timeline.stop();
            victoryScreen = new VictoryScreen(stage, this);
            victoryScreen.setText("Victory!");
            victoryScreen.start();
        }
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
        victoryScreen.addResumeButton();
        victoryScreen.start();
    }

    public void showTutorialScreen(int tutorialNumber) throws IOException {
        timeline.stop();
        MainMenu menu = new MainMenu(stage);
        menu.getController().getNextTutorialLevel(tutorialNumber);
    }

    private void countdownTick() throws IOException {
        String message = timer.getValue();
        String substring = message.replace("Time remaining: ", "");
        int timerInt = Integer.parseInt(substring);
        timerInt--;
        if (timerInt == 10) {
            timerLabel.setTextFill(Paint.valueOf("red"));
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), timerLabel);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setCycleCount(Animation.INDEFINITE);
            fadeTransition.play();
        }
        if (timerInt == 0) {
            showDefeatScreen();
        }
        timerLabel.setText("Time remaining: " + String.valueOf(timerInt));
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
        int newCount = prevCount + 1;
        treasureCount.setText(String.valueOf(newCount));
    }
    
    public void updateChargesSwordUI(int charges){
        swordCharges.setText(Integer.toString(charges));
    }

    public void updateChargesPotionUI(int charges){
        potionCharges.setText(Integer.toString(charges));
    }

    public void showTutorialText(String text){
        tutorialText.setText(text);
        tutorialText.setTextFill(Color.WHITE);
        squares.add(tutorialText, 0 , 0, dungeon.getWidth(), 2);
    }

    public void setTutorial(Boolean isTutorial, int tutorialNumber){
        this.isTutorial = isTutorial;
        this.tutorialNumber = tutorialNumber;
    }

    public void pauseTimeline() {
        timeline.pause();
    }

    public void setSecretLevel(SecretLevel level) {
        this.secretLevel = level;
    }

    public void startSecretLevel(String level) throws IOException {
        this.secretLevel = new SecretLevel(stage, level);
        pauseTimeline();
        secretLevel.startSecretLevel(level);
    }
}
