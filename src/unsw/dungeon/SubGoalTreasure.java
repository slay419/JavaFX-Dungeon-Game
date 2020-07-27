package unsw.dungeon;

public class SubGoalTreasure implements Goal, ObserverTreasure {
    
    private Boolean goalCompleted;
    private int numTreasure;
    private String name;
    private CompositeGoal compositeGoal;
    
    public SubGoalTreasure() {
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

    public void setNumTreasure(int numTreasure) {
        this.numTreasure = numTreasure;
    }

    //Decrements treasure and sets the goal to complete if the player collects all the treasure
    @Override
    public void updateTreasure() {
        numTreasure--;
        if (numTreasure == 0) {
            goalCompleted = true;
            System.out.println("Collected all the treasure!");
            compositeGoal.processGoal();
        }
    }
}