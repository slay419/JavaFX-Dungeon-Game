package unsw.dungeon;

public class SubGoalEnemy implements Goal, ObserverEnemy {
    
    private Boolean goalCompleted;
    private int numEnemies;
    private String name;
    private CompositeGoal compositeGoal;
    
    public SubGoalEnemy() {
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

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    //Decrements enemy count and sets goal to complete if count reaches 0 (all enemies dead)
    @Override
    public void updateEnemy() {
        numEnemies--;
        if (numEnemies == 0) {
            goalCompleted = true;
            compositeGoal.processGoal();
        }
        System.out.println("Killed an enemy!");
    }

}