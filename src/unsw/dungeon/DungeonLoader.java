package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        JSONObject jsonGoals = json.getJSONObject("goal-condition");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        loadGoal(dungeon, jsonGoals);
        return dungeon;
    }

    /**
     * Loads a goal into the dungeon from the JSON file
     * @param dungeon The dungeon that the goal is being loaded into
     * @param jsonGoal The Goal JSONObject
     * @return
     */
    private Goal loadGoal(Dungeon dungeon, JSONObject jsonGoal) {
        String goal = jsonGoal.getString("goal");       

        CompositeGoal compositeGoal = new CompositeGoal();
        Goal subgoal = null;
        
        dungeon.addGoal(subgoal);

        switch (goal) {
        case "exit":
            subgoal = new SubGoal();
            dungeon.processExitGoal(subgoal);
            break;
        case "boulders": 
            subgoal = new SubGoal();
            dungeon.processBouldersGoal(subgoal);
            break;
        case "enemies":
            subgoal = new SubGoal();
            dungeon.processEnemiesGoal(subgoal);
            break;
        case "treasure": 
            subgoal = new SubGoal();
            dungeon.processTreasureGoal(subgoal);
            break;
        case "AND":
            JSONArray listSubgoals = jsonGoal.getJSONArray("subgoals");
            for (int i = 0; i < listSubgoals.length(); i++) {
                Goal g = loadGoal(dungeon, listSubgoals.getJSONObject(i));
                ((SubGoal) g).setCompositeGoal(compositeGoal);
                compositeGoal.addGoal(g);
            }
            break;
        }
        if (subgoal != null) {
            compositeGoal.addGoal(subgoal);
            ((SubGoal) subgoal).setCompositeGoal(compositeGoal);
        }
        return subgoal;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;
        
        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "door":
            id = json.getInt("id");
            Door door = new Door(x, y, id);
            onLoad(door);
            entity = door; 
            break;
        case "key":
            id = json.getInt("id");
            Key key = new Key(x, y, id);
            onLoad(key);
            entity = key;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        case "portal":
            id = json.getInt("id");
            Portal portal = new Portal(x, y, id);
            onLoad(portal);
            entity = portal;
            break;
        case "boulder": 
            Boulder boulder = new Boulder(x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            FloorSwitch floorSwitch = new FloorSwitch(x, y);
            onLoad(floorSwitch);
            entity = floorSwitch;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
            break;
        case "invincibility":
            InvincibilityPotion potion = new InvincibilityPotion(x, y);
            onLoad(potion);
            entity = potion;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;

        // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(InvincibilityPotion invincibilityPotion);
    
    public abstract void onLoad(Sword sword);

    // TODO Create additional abstract methods for the other entities

}
