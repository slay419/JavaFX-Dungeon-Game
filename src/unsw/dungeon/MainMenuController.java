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
    private Level basic;
    private Level medium;
    private Level advanced;

    private TutorialLevel tutorial;
    private String[] textList = {"Test", "Are you awake? Better get moving shouldn't you?\n        Did you forget how to walk... \n                Use the up, down, left and right arrow keys."
    ,"                        The walls are alive.\nYou'll need to navigate them. \n                              The path isn't always clear..."
    ,"Sometimes a path will need to be made...\n              Are you strong enough?"
    ,"The path is blocked by a door. \n               But for every door, a key must exist."
    ,"It appears we aren't alone in this place. You can flee now, but I...\n won't always be here to protect\n                -you...\n"
    ,"Sometimes the 'Door' isn't a Door and\n       the 'Key' isn't a Key.\n               You and I, we don't get to hide..."
    ,"Pay attention of what you've been asked to do. \n       Sometimes there won't be a path out.."
    ,"Sometimes you'll require to use that brain of yours."
    ,"Othertimes you'll have to use your brawns."
    ,"Don't let the walls hold you back, fly through them."
    ,"Sometimes you'll get overwhelmed, here this should help."};

    @FXML
    private Button basicButton;

    @FXML
    private Button mediumButton;

    @FXML
    private Button advancedButton;

    @FXML
    private Button tutorialButton;

    public MainMenuController(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void handleBasicButton(ActionEvent event) throws IOException {
        basic = new Level(stage, "basic.json");
        setBasicLevel(basic);
        basic.start();
    }

    @FXML
    public void handleMediumButton(ActionEvent event) throws IOException {
        medium = new Level(stage, "medium.json");
        setMediumLevel(medium);
        medium.start();
    }

    @FXML
    public void handleAdvancedButton(ActionEvent event) throws IOException {
        advanced = new Level(stage, "secretLevel2.json");
        setAdvancedLevel(advanced);
        advanced.start();
    }

    @FXML
    public void handleTutorialButton(ActionEvent event) throws IOException {
        startTutorialLevel(1);
        tutorial = new TutorialLevel(stage, "tutorial1.json");
        tutorial.startTutorialLevel(1);
    }

    @FXML
    public void initialize(){
        Image basicImage = new Image((new File("examples/basic.png")).toURI().toString());
        ImageView basicImageView = new ImageView(basicImage);
        basicImageView.setFitHeight(152);
        basicImageView.setFitWidth(176);
        basicButton.setGraphic(basicImageView);

        Image mediumImage = new Image((new File("examples/medium.png")).toURI().toString());
        ImageView mediumImageView = new ImageView(mediumImage);
        mediumImageView.setFitHeight(152);
        mediumImageView.setFitWidth(176);
        mediumButton.setGraphic(mediumImageView);

        Image advancedImage = new Image((new File("examples/advanced.png")).toURI().toString());
        ImageView advancedImageView = new ImageView(advancedImage);
        advancedImageView.setFitHeight(152);
        advancedImageView.setFitWidth(176);
        advancedButton.setGraphic(advancedImageView);

    }

    public void setBasicLevel(Level basic){
        this.basic = basic;
    }

    public void setMediumLevel(Level medium){
        this.medium = medium;
    }

    public void setAdvancedLevel(Level advanced){
        this.advanced = advanced;
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