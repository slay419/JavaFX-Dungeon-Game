package unsw.dungeon;

import java.util.ArrayList;
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
        setName("player");
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
    public void processItem(Entity entity) {
        if (entity.isItem()) {
            inventory.add(entity);
            //dungeon.removeEntity(entity);
        }
    }

    /**
     * Checking tiles of where the player will move
     * @param x - future x position 
     * @param y - future y position 
     */
    public void processMovement(int x, int y) {
        // Check if the next tile is impassible or not 
        if (!checkImpassible(x, y)) {
            // Move the player if it's not impassible
            setXPos(x);
            setYPos(y);
        }
        // Always process the tile the player would move/has moved to
        
        // This line basically replaces all switch statements
        // Calls the entity process method in the child class
        Entity entity = dungeon.getEntity(x, y);
        if (entity != null) {
            System.out.println("Found entity: " + entity.getName());
            entity.process(this);  
        } 
        //dungeon.displayEntities();
    } 

    /**
     * Checks the coordinates to see if the entity there is impassible and allows 
     * player movement
     * @param x
     * @param y
     * @return
     */
    public Boolean checkImpassible(int x, int y) {
        //Entity entity = dungeon.getEntity(x, y);
        System.out.println("==============");
        ArrayList<Entity> entityList = dungeon.getEntityList(x, y);
        System.out.println(entityList);
        if (entityList == null) {
            System.out.println("found nothing");
            return false;
        }
        for (Entity e : entityList) {
            if (e.isImpassible()) {
                return true;
            }
            
        }
        System.out.println("All entities here were passible");
        System.out.println("=======================");
        return false;
    }

    /**
     * Removes the item from the level
     * @param entity
     */
    public void pickUp(Entity entity) {
        dungeon.removeEntity(entity);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Entity getEntity(int x, int y){
        return dungeon.getEntity(x, y);
    }

    public ArrayList<Entity> getEntityList(int x, int y){
        return dungeon.getEntityList(x, y);
    }

    /**
     * Searches the level for a list of matching entities
     * @return
     */
    public ArrayList<Entity> levelEntites(String name) {
        return dungeon.findEntities(name);
    }
   
}
