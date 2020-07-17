package unsw.dungeon;

import unsw.dungeon.Entity;


public class Treasure extends Entity {

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
     * @param inventory
     */
    @Override
    public void process(Player player) {
        Inventory inventory = player.getInventory();
        inventory.add(this);
        System.out.println("Added treasure number: " + inventory.countTreasure() + " to inventory!");
        //dungeon.removeEntity(entity);
    }

    
}