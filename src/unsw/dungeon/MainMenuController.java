package unsw.dungeon;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainMenuController {

    private Level advanced;
    private Level boulders;
    private Level maze;

    @FXML
    private Button advancedButton;

    @FXML
    private Button bouldersButton;

    @FXML
    private Button mazeButton;

    @FXML
    public void handleAdvancedButton(ActionEvent event){
        advanced.start();
    }

    @FXML
    public void handleBouldersButton(ActionEvent event){
        boulders.start();
    }

    @FXML
    public void handleMazeButton(ActionEvent event){
        maze.start();
    }

    @FXML
    public void initialize(){
        Image advancedImage = new Image((new File("examples/advanced.png")).toURI().toString());
        ImageView advancedImageView = new ImageView(advancedImage);
        advancedImageView.setFitHeight(130);
        advancedImageView.setFitWidth(150);
        advancedButton.setGraphic(advancedImageView);

        Image bouldersImage = new Image((new File("examples/boulders.png")).toURI().toString());
        ImageView bouldersImageView = new ImageView(bouldersImage);
        bouldersImageView.setFitHeight(130);
        bouldersImageView.setFitWidth(150);
        bouldersButton.setGraphic(bouldersImageView);

        Image mazeImage = new Image((new File("examples/maze.png")).toURI().toString());
        ImageView mazeImageView = new ImageView(mazeImage);
        mazeImageView.setFitHeight(130);
        mazeImageView.setFitWidth(150);
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
}