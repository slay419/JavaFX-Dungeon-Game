package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SubGoalEnemy implements Goal, ObserverEnemy {
    
    private Boolean goalCompleted;
    private int numEnemies;
    private String name;
    private Goal compositeGoal;

    private IntegerProperty numEnemiesKilled;
    
    public SubGoalEnemy() {
        this.numEnemies = 0;
        this.goalCompleted = false;
        this.numEnemiesKilled = new SimpleIntegerProperty();
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

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    public void bindEnemyCount(IntegerProperty numEnemiesKilled) {
        numEnemiesKilled.bindBidirectional(this.numEnemiesKilled);
    } 

    //Decrements enemy count and sets goal to complete if count reaches 0 (all enemies dead)
    @Override
    public void updateEnemy() {
        numEnemies--;
        numEnemiesKilled = new SimpleIntegerProperty(numEnemies);
        if (numEnemies == 0) {
            goalCompleted = true;
            compositeGoal.processGoal();
        }
        System.out.println("Killed an enemy!");
    }

}