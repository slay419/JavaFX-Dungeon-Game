package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuController {

    private Stage stage;
    private Level advanced;
    private Level boulders;
    private Level maze;

    private Level tutorial;
    private String[] textList = {"Test", "Are you awake? Better get moving shouldn't you?\n        Did you forget how to walk... \n                Use the up, down, left and right arrow keys."
    ,"                        The walls are alive.\nYou'll need to navigate them. \n                              The path isn't always clear..."
    ,"Sometimes a path will need to be made...\n              Are you strong enough?"
    ,"The path is blocked by a door. \n               But for every door, a key must exist."
    ,"It appears we aren't alone in this place. You can flee now, but\n       -I...\n won't always be here to protect\n                -you...\n"
    ,"Sometimes the 'Door' isn't a Door\n         and\nthe 'Key' isn't a Key.\n               You and I, we don't get to hide..."
    ,"Pay attention of what you've been asked to do. \n       Sometimes there won't be a path out.."
    ,"Sometimes you'll require to use that brain of yours."
    ,"Othertimes you'll have to use your brawns."
    ,"Don't let the walls hold you back, fly through them."
    ,"Sometimes you'll get overwhelmed, here this should help."};

    @FXML
    private Button advancedButton;

    @FXML
    private Button bouldersButton;

    @FXML
    private Button mazeButton;

    @FXML
    private Button tutorialButton;

    public MainMenuController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void handleAdvancedButton(ActionEvent event) throws IOException {
        advanced = new Level(stage, "advanced.json");
        setAdvancedLevel(advanced);
        advanced.start();
    }

    @FXML
    public void handleBouldersButton(ActionEvent event) throws IOException {
        boulders = new Level(stage, "boulders.json");
        setBouldersLevel(boulders);
        boulders.start();
    }

    @FXML
    public void handleMazeButton(ActionEvent event) throws IOException {
        maze = new Level(stage, "maze.json");
        setMazeLevel(maze);
        maze.start();
    }

    @FXML
    public void handleTutorialButton(ActionEvent event) throws IOException {
        startTutorialLevel(1);
    }
    @FXML
    public void initialize(){
        Image advancedImage = new Image((new File("examples/advanced.png")).toURI().toString());
        ImageView advancedImageView = new ImageView(advancedImage);
        advancedImageView.setFitHeight(152);
        advancedImageView.setFitWidth(176);
        advancedButton.setGraphic(advancedImageView);

        Image bouldersImage = new Image((new File("examples/boulders.png")).toURI().toString());
        ImageView bouldersImageView = new ImageView(bouldersImage);
        bouldersImageView.setFitHeight(152);
        bouldersImageView.setFitWidth(176);
        bouldersButton.setGraphic(bouldersImageView);

        Image mazeImage = new Image((new File("examples/maze.png")).toURI().toString());
        ImageView mazeImageView = new ImageView(mazeImage);
        mazeImageView.setFitHeight(152);
        mazeImageView.setFitWidth(176);
        mazeButton.setGraphic(mazeImageView);

    }

    public void setAdvancedLevel(Level advanced){
        this.advanced = advanced;
    }

    public void setBouldersLevel(Level boulders){
        this.boulders = boulders;
    }

    public void setMazeLevel(Level maze){
        this.maze = maze;
    }

    public void startTutorialLevel(int levelNumber) throws IOException{
        String level = "tutorial" + levelNumber + ".json";
        tutorial = new Level(stage, level);
        tutorial.setTutorial(true, levelNumber);
        tutorial.getController().showTutorialText(textList[levelNumber]);
        tutorial.start();
    }

    public void getNextTutorialLevel(int levelNumber) throws IOException {
        levelNumber = levelNumber + 1;
        startTutorialLevel(levelNumber);
    }
}