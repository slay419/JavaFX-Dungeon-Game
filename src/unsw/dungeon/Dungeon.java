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
    //private SubGoal goal;
    private List<Goal> goals;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.goals = new ArrayList<>();
        this.player = null;
    }

    public void addGoal(Goal goal) {
        this.goals.add(goal);
    }

    public void processExitGoal(Goal subgoal) {
        // Look for the exit and call exit.register()
        ArrayList<Entity> exits = findEntities("exit");
        Exit exit = (Exit) exits.get(0);

        ObserverExit observer = (ObserverExit) subgoal;
        exit.register(observer);
        subgoal.setName("exit");
    }

    public void processBouldersGoal(Goal subgoal) {
        ArrayList<Entity> switches = findEntities("floorSwitch");
        ObserverBoulders observer = (ObserverBoulders) subgoal;
        // attach this obs to all switches 
        for (Entity e : switches) {
            FloorSwitch floorSwitch = (FloorSwitch) e;
            floorSwitch.register(observer);
        }
        ((SubGoal) subgoal).setNumSwitches(switches.size());
        subgoal.setName("boulders");
    }

    public void processTreasureGoal(Goal subgoal) {
        ArrayList<Entity> treasureList = findEntities("treasure");
        ObserverTreasure observer = (ObserverTreasure) subgoal;

        for (Entity e : treasureList) {
            Treasure treasure = (Treasure) e;
            treasure.register(observer);
        }
        ((SubGoal) subgoal).setNumTreasure(treasureList.size());
        subgoal.setName("treasure");
    }

    public void processEnemiesGoal(Goal subgoal) {
        ArrayList<Entity> enemyList = findEntities("enemy");
        ObserverEnemy observer = (ObserverEnemy) subgoal;

        for (Entity e : enemyList) {
            Enemy enemy = (Enemy) e;
            enemy.register(observer);
        }
        ((SubGoal) subgoal).setNumEnemies(enemyList.size());
        subgoal.setName("enemy");
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
        ArrayList<Entity> eList = new ArrayList<>();
        for (Entity e : entities) {
            int entityX = e.getX();
            int entityY = e.getY();
            
            if (entityX == x && entityY == y && !(e instanceof Player)) {
                eList.add(e);
            }
            
        }
        if (eList.isEmpty()) {
            return null;
        }
        return eList;
    }

    public void displayEntities() {
        for (Entity e: entities) {
            if (e.getName().equals("wall")) {
                continue;
            }
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

    public Boolean entityExists(Entity entity) {
        return entities.contains(entity);
    }

    // have a lsit of subgoals - from loading goals
    // have a function that processes list and looks through subgoal 
    // depening on subgoal e.g. exit 
        // look for those entities and register them to observer 
        // if floor switch subgoal then look for all switches and register them 
    // All registered - works

    // make sure load goals same as load entities
}
