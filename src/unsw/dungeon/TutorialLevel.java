package unsw.dungeon;

import java.io.IOException;

import javafx.stage.Stage;

public class TutorialLevel extends Level {
    
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
    
    public TutorialLevel(Stage stage, String level) throws IOException {
        super(stage, level);
    }

    public void getNextTutorialLevel(Stage stage, int levelNumber) throws IOException {
        levelNumber = levelNumber + 1;
        startTutorialLevel(levelNumber);
    }

    public void startTutorialLevel(int levelNumber) throws IOException{
        String level = "tutorial" + levelNumber + ".json";
        TutorialLevel newLevel = new TutorialLevel(getStage(), level);
        newLevel.setTutorial(true, levelNumber);
        newLevel.getController().showTutorialText(textList[levelNumber]);
        newLevel.start();
    }

    public void setTutorial(Boolean isTutorial, int levelNumber){
        getController().setTutorial(isTutorial, levelNumber, this);
    }

}