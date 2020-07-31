package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenu {
    private Stage stage;
    private String title;
    private MainMenuController controller;
    private Scene scene;

    private Level advanced;
    private Level boulders;
    private Level maze;

    public MainMenu(Stage stage) throws IOException{
        this.stage = stage;
        title = "Main Menu";
        
        controller = new MainMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);

        advanced = new Level(stage, "advanced.json");
        boulders = new Level(stage, "boulders.json");
        maze = new Level(stage, "maze.json");
        getController().setAdvancedLevel(advanced);
        getController().setBouldersLevel(boulders);
        getController().setMazeLevel(maze);
    }

    public void start(){
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public MainMenuController getController(){
        return controller;
    }
    
}