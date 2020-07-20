package unsw.dungeon;

public class SubGoal implements Goal, ObserverExit, ObserverBoulders, ObserverTreasure, ObserverEnemy {

    // one observer for all floorswitches 


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

    @Override
    public void update() {
        System.out.println("Setting the subgoal: true");
        goalCompleted = true;
    }

    @Override 
    public void update(String string) {
        if (string.equals("increment")) {
            incrementSwitches();
        } else if (string.equals("decrement")) {
            decrementSwitches();
        }
    }

    @Override
    public void updateTreasure() {
        numTreasure--;
        if (numTreasure == 0) {
            System.out.println("Collected all the treasure");
            goalCompleted = true;
            compositeGoal.processGoal();
        }
    }

    @Override
    public void updateEnemy() {
        numEnemies--;
        if (numEnemies == 0) {
            System.out.println("Killed all the enemies");
            goalCompleted = true;
            compositeGoal.processGoal();
        }

    }

    public void checkSwitches() {
        if (numSwitches == 0) {
            System.out.println("Setting the Boulder goal: true");
            goalCompleted = true;
            compositeGoal.processGoal();
        } else {
            goalCompleted = false;
        }
    }

    public void decrementSwitches() {
        numSwitches--;
        System.out.println("Decreasing num switches to: " + numSwitches);
        checkSwitches();
    }

    public void incrementSwitches() {
        numSwitches++;
        System.out.println("Increasing num switches to: " + numSwitches);
        checkSwitches();
    }

    

    
    

}