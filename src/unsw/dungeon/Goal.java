package unsw.dungeon;

public class Goal implements Observer {
    private int numTreasure; 
    private Subject inventory;

    public Goal(Subject inventory) {
        this.inventory = inventory;
        inventory.register(this);
    }

    @Override
    public void update(int numTreasure) {
        setNumTreasure(numTreasure);
    }

    public void setNumTreasure(int numTreasure) {
        this.numTreasure = numTreasure;
    }
}