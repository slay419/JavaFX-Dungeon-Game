package unsw.dungeon;

import java.util.ArrayList;

public class CompositeGoal implements Goal {

    private String name; 

    //private Boolean goalCompleted;

    private ArrayList<Goal> subGoals;

    public CompositeGoal() {
        subGoals = new ArrayList<Goal>();
    }

    public Boolean processGoal() {
        for (Goal s : subGoals) {
            if (!s.processGoal()) {
                return false;
            }
        }
        System.out.println("completed all goals!");
        return true;
    }

    public void addGoal(Goal subgoal) {
        subGoals.add(subgoal);
        System.out.println("goals so far:");
        for (Goal g : subGoals) {
            System.out.println("\t subgoal:" + g.getName());
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    
}