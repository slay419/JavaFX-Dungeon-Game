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
    private Inventory inventory;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = new Inventory(this);
        name = "player";
    }
    // 0 is the top
    public void moveUp() {
        if (getY() > 0) {
            processMovement(getX(), getY() - 1);
        }
    }

    public void moveDown() {
        // if y value less than 17
        if (getY() < dungeon.getHeight() - 1) {
            processMovement(getX(), getY() + 1);
        }
    }

    // 0 is the left
    public void moveLeft() {
        if (getX() > 0) {
            processMovement(getX() - 1, getY());
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            processMovement(getX() + 1, getY());
        }
    }

    // Check what entity exists at the current tile 
    public void checkItem(Entity entity) {
        if (entity.isItem()) {
            inventory.add(entity);
            dungeon.removeEntity(entity);
        }
    }

    /**
     * Checking tiles of where the player will move
     * @param x - future x position 
     * @param y - future y position 
     */
    public void processMovement(int x, int y) {
        Entity entity = dungeon.getEntity(x, y);
        // Check if the next time is impassible or not 
        if (!checkImpassible(x, y)) {
            // Move the player if it's not impassible
            x().set(x);
            y().set(y);

            // Check if there is an item at the given tile 
            if (entity != null) {
                checkItem(entity);
            }
        } else {
            String name = entity.getName();
            processImpassible(name);
        }
    } 
    

    private void processImpassible(String name) {
        switch (name) {
        case "door":




        }
    }

    // Checks if there exists an impassible object at this location 
    public Boolean checkImpassible(int x, int y) {
        // Go to dungeon and check if there is an entity at xy
        List<Entity> levelEntities = dungeon.getEntities();
        
        for (Entity e : levelEntities) {
            //System.out.println("Checking name: " + e.getName());
            int entityX = e.getX();
            //System.out.println("entityX is: " + entityX);
            int entityY = e.getY();
            //System.out.println("entityY is: " + entityY);
            
            if (entityX == x && entityY == y && e.impassible) {
                System.out.println("Found an impassible object!");
                //Now we're going to check the type of impassible object
                if(e instanceof Door){
                    Door newDoor = (Door) e;
                    if (inventory.checkKey(newDoor.getId())) {
                        newDoor.openDoor();
                    }                   

                } else if (e instanceof Exit) {
                    System.out.println("reached the exit");
                }
                return true;
            }
        }   
        
        //System.out.println("returning false");
        return false;
    }

    private Boolean reachedExit() {
        // Loop through dungeon entities and find the exit 
        List<Entity> levelEntities = dungeon.getEntities();

        for (Entity e : levelEntities) {
             e.getName().equals("exit");
             return true;
        }
        return false;
    }
}
