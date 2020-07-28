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
        System.out.println("Calling OR process goal");
        for (Goal s : subGoals) {
            if (s.processGoal()) {
                System.out.println("Completed one of the goals!");
                return true;
            }
        }
        return false;
    }

    public void addGoal(Goal subgoal) {
        System.out.println("Added or goal: " + subgoal.getName());
        subGoals.add(subgoal);
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