package unsw.dungeon;

public class SubGoal implements Goal, ObserverExit, ObserverBoulders, ObserverTreasure, ObserverEnemy {

    private Boolean goalCompleted;

    private int numSwitches; 
    private int numTreasure;
    private int numEnemies;

    private String name;

    private CompositeGoal compositeGoal;
    
    public SubGoal() {
        this.numSwitches = 0;
        this.numTreasure = 0;
        this.numEnemies = 0;
        this.goalCompleted = false;
    }

    public void setCompositeGoal(CompositeGoal compositeGoal) {
        this.compositeGoal = compositeGoal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Boolean processGoal() {
        return goalCompleted;
    }

    public void setNumSwitches(int numSwitches) {
        this.numSwitches = numSwitches;
    }

    public void setNumTreasure(int numTreasure) {
        this.numTreasure = numTreasure;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    //Sets the goal to complete if all othergoals are complete and the player reaches the exit
    @Override
    public void updateExit() {
        if (compositeGoal.allOtherGoalsComplete()) {
            goalCompleted = true;
        } else {
            System.out.println("Need to complete other goals first");
        }
        
    }

    @Override 
    public void updateSwitches(String string) {
        if (string.equals("increment")) {
            incrementSwitches();
        } else if (string.equals("decrement")) {
            decrementSwitches();
        }
    }
    
    //Decrements treasure and sets the goal to complete if the player collects all the treasure
    @Override
    public void updateTreasure() {
        numTreasure--;
        if (numTreasure == 0) {
            goalCompleted = true;
            compositeGoal.processGoal();
        }
    }

    //Decrements enemy count and sets goal to complete if count reaches 0 (all enemies dead)
    @Override
    public void updateEnemy() {
        numEnemies--;
        if (numEnemies == 0) {
            goalCompleted = true;
            compositeGoal.processGoal();
        }

    }

    //Checks all switches have been triggered, sets goal to complete if they are
    public void checkSwitches() {
        if (numSwitches == 0) {
            goalCompleted = true;
            compositeGoal.processGoal();
        } else {
            goalCompleted = false;
        }
    }

    public void decrementSwitches() {
        numSwitches--;
        checkSwitches();
    }

    public void incrementSwitches() {
        numSwitches++;
        checkSwitches();
    }
}