package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {
    
    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private Boolean impassible; 
    private String name;
    private Boolean item = false;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public void setXPos(int x) {
        x().set(x);
    }

    public void setYPos(int y) {
        y().set(y);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setItem(Boolean item) {
        this.item = item;
    }

    public Boolean getItem() {
        return item;
    }

    public int getId() {
        return -1;
    }

    public void setImpassible(Boolean impassible) {
        this.impassible = impassible;
    }

    public Boolean isImpassible() {
        if (impassible == true) {
            System.out.println("Found an impassible object!");
        }
        return impassible;
    }
    
    public void processItem(Entity entity, Dungeon dungeon) {
        dungeon.removeEntity(entity);

    }
    public Boolean isItem() {
        return item;
    }

    
    public void process(Player player) {
        return;
    }
}
