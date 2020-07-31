package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    private Level advanced;
    private Level boulders;
    private Level maze;

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*primaryStage.setTitle("Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();*/
        MainMenu mainMenu = new MainMenu(primaryStage);
        /*
        advanced = new Level(primaryStage, "advanced.json");
        boulders = new Level(primaryStage, "boulders.json");
        maze = new Level(primaryStage, "maze.json");
        mainMenu.getController().setAdvancedLevel(advanced);
        mainMenu.getController().setBouldersLevel(boulders);
        mainMenu.getController().setMazeLevel(maze);
        */
        mainMenu.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
