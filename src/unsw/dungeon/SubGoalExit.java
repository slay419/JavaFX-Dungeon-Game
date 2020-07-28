package unsw.dungeon;

public class SubGoalExit implements Goal, ObserverExit {
    
    private Boolean goalCompleted;
    private String name;
    private Goal compositeGoal;
    
    public SubGoalExit() {
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

    //Sets the goal to complete if all othergoals are complete and the player reaches the exit
    @Override
    public void updateExit() {
        if (((CompositeGoal) compositeGoal).allOtherGoalsComplete()) {
            goalCompleted = true;
            compositeGoal.processGoal();
        } else {
            System.out.println("Need to complete other goals first");
        }
    }

}