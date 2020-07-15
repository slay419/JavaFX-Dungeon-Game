package unsw.dungeon;

import unsw.dungeon.Entity;


public class Treasure extends Entity {

    /**
     * @param x X coordinate of the key (Starting from 0 Left to right)
     * @param y Y coordinate of the key (Starting from 0 Top to bottom)
     */
    public Treasure(int x, int y) {
        super(x, y);
        impassible = false;
    }
    
}