package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class VictoryScreenController {

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

}