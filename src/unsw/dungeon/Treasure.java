package unsw.dungeon;

import java.util.ArrayList;

public class Treasure extends Entity implements SubjectTreasure {

    ArrayList<ObserverTreasure> observers = new ArrayList<ObserverTreasure>();

    /**
     * @param x X coordinate of the treasure (Starting from 0 Left to right)
     * @param y Y coordinate of the treasure (Starting from 0 Top to bottom)
     */
    public Treasure(int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("treasure");
        setItem(true);
    }

    /**
     * Processed when the player collides with the treasure entity 
     * Treasure is added to the inventory and removed from the level
     * The observer is then notified
     * @param inventory
     */
    @Override
    public void process(Player player) {
        Inventory inventory = player.getInventory();
        inventory.add(this);
        notifyObserver();
    }

    @Override
    public void register(ObserverTreasure o) {
        observers.add(o);

    }

    @Override
    public void unregister(ObserverTreasure o) {
        observers.remove(o);

    }

    @Override
    public void notifyObserver() {
        for (ObserverTreasure o : observers) {
            o.updateTreasure();
        }

    }
}