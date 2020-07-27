package unsw.dungeon;

public class SubGoalExit implements Goal, ObserverExit {
    
    private Boolean goalCompleted;

    private String name;

    private CompositeGoal compositeGoal;
    
    public SubGoalExit() {
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

    //Sets the goal to complete if all othergoals are complete and the player reaches the exit
    @Override
    public void updateExit() {
        if (compositeGoal.allOtherGoalsComplete()) {
            goalCompleted = true;
        } else {
            System.out.println("Need to complete other goals first");
        }
    }

}