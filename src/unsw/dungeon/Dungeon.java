/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void removeEntity(Entity entity) {
        System.out.println("removed entity: " + entity.getName());
        entities.remove(entity);
    }

    // Checks what entity exists at the given tile
    public Entity getEntity(int x, int y) {
        for (Entity e : entities) {
            int entityX = e.getX();
            int entityY = e.getY();
            
            if (entityX == x && entityY == y && !(e instanceof Player)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Entity> getEntityList(int x, int y) {
        for (Entity e : entities) {
            int entityX = e.getX();
            int entityY = e.getY();
            System.out.println("Entity: " + e.getName() + " is at: (" + entityX + ", " + entityY + ")");
            
            ArrayList<Entity> eList = new ArrayList<>();
            if (entityX == x && entityY == y && !(e instanceof Player)) {
                eList.add(e);
                System.out.println("Added entity: " + e.getName() + " to the list");
            }
            if(!eList.isEmpty()){
                return eList;
            }
        }
        return null;
    }

    public void displayEntities() {
        for (Entity e: entities) {
            if (e.getName().equals("wall")) {
                continue;
            }
            System.out.println(e.getName() + "(" + e.getX() + ", " + e.getY() + ")");
        }
    }

    /**
     * Loops through the list of entities and returns a list of matching entity names
     * @return
     */
    public ArrayList<Entity> findEntities(String name) {
        ArrayList<Entity> result = new ArrayList<Entity>();
        for (Entity e : entities) {
            if (e.getName().equals(name)) {
                result.add(e);
            }
        }
        return result;
    }
}
