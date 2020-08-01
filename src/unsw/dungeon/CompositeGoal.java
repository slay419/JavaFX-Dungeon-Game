package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;

public class CompositeGoal implements Goal {

    private String name;
    private ArrayList<Goal> subGoals;
    private Dungeon dungeon;

    public CompositeGoal() {
        subGoals = new ArrayList<Goal>();
    }

    // Process goal checks if all subgoals are completed (return true). If all are
    // completed then it will return true
    public Boolean processGoal() {
        for (Goal s : subGoals) {
            if (!s.processGoal()) {
                return false;
            }
        }
        System.out.println("Completed all goals!");
        try {
            dungeon.showVictoryScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Adds a subgoal to the list of subgoals
     * @param subgoal The subgoal to be added
     */
    public void addGoal(Goal subgoal) {
        subGoals.add(subgoal);
    }

    /**
     * Checks if all other goals except for exit has been complete
     * @return
     */
    public Boolean allOtherGoalsComplete() {
        for (Goal g : subGoals) {
            if (g.getName().equals("exit")) {
                continue;
            }
            if (!g.processGoal()) {
                return false;
            }
        }
        return true;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public ArrayList<Goal> getSubGoals() {
        for (Goal g : subGoals) {
            System.out.println(g.getName());
        }
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
        System.out.println("this should not happen");
        return;
    }
    
}