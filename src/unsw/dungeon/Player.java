package unsw.dungeon;


import java.util.List;

import javafx.beans.property.IntegerProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        name = "player";
    }
    // 0 is the top
    public void moveUp() {
        if (getY() > 0) {
            if (!checkImpassible(getX(), getY() - 1)) {
                y().set(getY() - 1);
            }
        }
    }

    public void moveDown() {
        // if y value less than 17
        if (getY() < dungeon.getHeight() - 1) {
            if (!checkImpassible(getX(), getY() + 1)) {
                y().set(getY() + 1);
            }
        }
    }

    // 0 is the left
    public void moveLeft() {
        if (getX() > 0) {
            if (!checkImpassible(getX() - 1, getY())) {
                x().set(getX() - 1);
            }
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            if (!checkImpassible(getX() + 1, getY())) {
                x().set(getX() + 1);
            }
        }
    }

    // Checks if there exists an impassible object at this location 
    public Boolean checkImpassible(int x, int y) {
        // Go to dungeon and check if there is an entity at xy
        List<Entity> levelEntities = dungeon.getEntities();
        
        for (Entity e : levelEntities) {
            System.out.println("Checking name: " + e.getName());
            int entityX = e.getX();
            System.out.println("entityX is: " + entityX);
            int entityY = e.getY();
            System.out.println("entityY is: " + entityY);
            
            if (entityX == x && entityY == y && e.impassible) {
                System.out.println("Found an impassible object!");
                return true;
            }
            
        }   
        
        System.out.println("returning false");
        return false;
    }
}
