package unsw.dungeon;

import java.util.ArrayList;

public class CompositeOrGoal implements Goal {

    private String name; 
    private ArrayList<Goal> subGoals;

    public CompositeOrGoal() {
        subGoals = new ArrayList<Goal>();
    }

    @Override
    public Boolean processGoal() {
        for (Goal s : subGoals) {
            if (s.processGoal()) {
                return true;
            }
        }
        return false;
    }

    public void addGoal(Goal subgoal) {
        subGoals.add(subgoal);
    }

    public ArrayList<Goal> getSubGoals() {
        return subGoals;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setCompositeGoal(Goal compositeGoal) {
        return;
    }
    
}