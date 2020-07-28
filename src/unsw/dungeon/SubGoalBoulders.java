package unsw.dungeon;

public class SubGoalBoulders implements Goal, ObserverBoulders {
    
    private Boolean goalCompleted;
    private int numSwitches; 
    private String name;
    private Goal compositeGoal;
    
    public SubGoalBoulders() {
        this.numSwitches = 0;
        this.goalCompleted = false;
    }

    public void setCompositeGoal(Goal compositeGoal) {
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

    @Override 
    public void updateSwitches(String string) {
        if (string.equals("increment")) {
            incrementSwitches();
        } else if (string.equals("decrement")) {
            decrementSwitches();
        }
    }

    //Checks all switches have been triggered, sets goal to complete if they are
    private void checkSwitches() {
        System.out.println("Num: " + numSwitches + " left");
        if (numSwitches == 0) {
            goalCompleted = true;
            System.out.println("Activated all switches!");
            compositeGoal.processGoal();
        } else {
            goalCompleted = false;
        }
    }

    private void decrementSwitches() {
        numSwitches--;
        System.out.println("Activated a switch");
        checkSwitches();
    }

    private void incrementSwitches() {
        numSwitches++;
        System.out.println("Deactivated a switch");
        checkSwitches();
    }

}