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
        Level basic = new Level(stage, "basic.json");
        basic.start();
    }

    @FXML
    public void handleMediumButton(ActionEvent event) throws IOException {
        Level medium = new Level(stage, "medium.json");
        medium.start();
    }

    @FXML
    public void handleAdvancedButton(ActionEvent event) throws IOException {
        Level advanced = new Level(stage, "secretLevel2.json");
        advanced.start();
    }

    @FXML
    public void handleTutorialButton(ActionEvent event) throws IOException {
        TutorialLevel tutorial = new TutorialLevel(stage, "tutorial1.json");
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

}