package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory;

    private int prevX, prevY;

    private Boolean visitedSecret1, visitedSecret2;

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = new Inventory(this);
        setName("player");
        this.prevX = x;
        this.prevY = y;
        this.visitedSecret1 = false;
        this.visitedSecret2 = false;
    }

    public void setOldPosition() {
        prevX = getX();
        prevY = getY();
    }

    public void moveUp() {
        if (getY() > 0) {
            setOldPosition();
            processMovement(getX(), getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
            setOldPosition();
            processMovement(getX(), getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
            setOldPosition();
            processMovement(getX() - 1, getY());
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
            setOldPosition();
            processMovement(getX() + 1, getY());
        }
    }

    /**
     * Checking tiles of where the player will move
     * 
     * @param x - future x position
     * @param y - future y position
     */
    private void processMovement(int x, int y) {

        ArrayList<Entity> entityList = dungeon.getEntityList(x, y);

        // Check if the next tile is impassible or not
        if (!checkImpassible(x, y)) {
            // Move the player if it's not impassible
            setXPos(x);
            setYPos(y);
            processPotion();
        }
        // Calls the entity process method in the child class
        if (entityList != null) {
            for (Entity e : entityList) {
                e.process(this);
            }
        }
        // Enemies will move after the player
        moveEnemies();
    }

    /**
     * Checks the coordinates to see if the entity there is impassible and allows
     * player movement
     * 
     * @param x
     * @param y
     * @return
     */
    public Boolean checkImpassible(int x, int y) {
        ArrayList<Entity> entityList = dungeon.getEntityList(x, y);
        if (entityList == null) {
            return false;
        }
        for (Entity e : entityList) {
            if (e.isImpassible()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the item from the level
     * 
     * @param entity
     */
    public void pickUp(Entity entity) {
        dungeon.removeEntity(entity);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<Entity> getEntityList(int x, int y) {
        return dungeon.getEntityList(x, y);
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public Boolean visitedLevel1() {
        return visitedSecret1;
    }

    public void setVisitedLevel1(Boolean visited) {
        visitedSecret1 = visited;
    }

    public Boolean visitedLevel2() {
        return visitedSecret2;
    }

    public void setVisitedLevel2(Boolean visited) {
        visitedSecret2 = visited;
    }

    /**
     * Searches the level for a list of matching entities
     * 
     * @return
     */
    public ArrayList<Entity> levelEntites(String name) {
        return dungeon.findEntities(name);
    }

    /**
     * @return - true if the player has a sword or potion in their inventory
     */
    public Boolean isInvincible() {
        return inventory.hasPotion();
    }

    public Boolean isOnIce() {
        System.out.println("currently on: " + getX() + ", " + getY());
        ArrayList<Entity> entities = dungeon.getEntityList(getX(), getY());
        if (entities == null) {
            System.out.println("foudn null");
            return false;
        }
        Entity icyFloor = checkEntityList(entities, "icyFloor");
        if (icyFloor != null) {
            System.out.println("on ice");
            return true;
        }
        return false;
    }

    /**
     * Moves all the enemies in the dungeon
     */
    private void moveEnemies() {
        ArrayList<Entity> enemies = dungeon.findEntities("enemy");
        for (Entity e : enemies) {
            ((Enemy) e).processMovement(this);
        }
    }

    public void killEnemy(Entity enemy) {
        dungeon.removeEntity(enemy);
    }

    public void useSword() {
        inventory.useSword();
    }

    public Boolean hasSword() {
        return inventory.hasSword();
    }

    public void processPotion() {
        inventory.usePotion();
    }

    public void removeImage(Entity entity) {
        dungeon.removeImage(entity);
    }

    public void openDoor(Entity entity) {
        dungeon.openDoor(entity);
    }

    public void showVictoryScreen() throws IOException {
        dungeon.showVictoryScreen();
    }

    public void addImage(ImageView image, int x){
        dungeon.addImage(image, x);
    }

    public void removeImage(ImageView image, int x){
        dungeon.removeImage(image, x);
    }

    public void updateChargesTreasureUI(){
        dungeon.updateChargesTreasureUI();
    }

    public void updateChargesSwordUI(int charges){
        dungeon.updateChargesSwordUI(charges);
    }

    public void updateChargesPotionUI(int charges){
        dungeon.updateChargesPotionUI(charges);
    }

    public void moveToSecretLevel(String level) {
        dungeon.moveToSecretLevel(level);
    }

}