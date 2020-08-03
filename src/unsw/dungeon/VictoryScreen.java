package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VictoryScreen {

    private Stage stage;
    private String title;
    private VictoryScreenController controller;
    private Scene scene;

    public VictoryScreen(Stage stage, DungeonController dungeonController) throws IOException{
        this.stage = stage;
        title = "Victory Screen";
        
        controller = new VictoryScreenController(dungeonController);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("victoryScreen.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void setText(String text) {
        controller.setText(text);
    }

    public VictoryScreenController getController() {
        return controller;
    }

    public void addResumeButton() {
        controller.createResumeButton();
    }
}