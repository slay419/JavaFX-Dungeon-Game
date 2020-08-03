package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class VictoryScreenController {

    @FXML
    private Pane pane;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button restartButton;

    @FXML
    private Text textDisplay;

    private DungeonController controller;

    public VictoryScreenController(DungeonController controller) {
        this.controller = controller;
    }

    @FXML
    public void handleRestartButton() throws IOException {
        System.out.println("restart");
        controller.restartLevel();
    }

    @FXML
    public void handleMenuButton() throws IOException {
        System.out.println("main menu");
        controller.goToMainMenu();
    }

    public void setText(String text) {
        textDisplay.setText(text);
    }

    public void createResumeButton() {
        Button resumeButton = new Button("Resume");
        resumeButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent T){
                handleResumeButton();
            }
        });
        pane.getChildren().add(resumeButton);
        resumeButton.setTranslateX(275);
        resumeButton.setTranslateY(190);
    }

    private void handleResumeButton() {
        controller.resumeLevel();
    }

}