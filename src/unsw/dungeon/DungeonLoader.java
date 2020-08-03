package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
        int timer = json.getInt("timer");

        Dungeon dungeon = new Dungeon(width, height);
        dungeon.setTimer(timer);

        JSONArray jsonEntities = json.getJSONArray("entities");

        JSONObject jsonGoals = json.getJSONObject("goal-condition");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        Goal goal = loadGoal(dungeon, jsonGoals);
        //System.out.println("calling from loader: found: " + goal.getName());
        dungeon.processCompositeGoal(goal);
        if (goal instanceof CompositeGoal /*|| goal instanceof CompositeOrGoal*/) {
            CompositeGoal g = (CompositeGoal) goal;
            for (Goal subgoal : g.getSubGoals()) {
                System.out.println("found goal: " + subgoal.getName());
            }
            System.out.println("Added goal: " + g.getName());
            dungeon.addCompositeGoal(g);
            
        }
        //System.out.println("Found goal: " + goal.getName());
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
        dungeon.processCompositeGoal(compositeGoal);
        JSONArray listSubgoals = null;
        Goal subgoal = null;
        
        //dungeon.addGoal(subgoal);
        /**
         * For each of the goals, create a new goal object (leaf) and process it
         * Processing it involves attaching observers in each associated entity class
         * After creating the leaves, attach it to a composite goal to handle checking later
         */
        switch (goal) {
        case "exit":
            subgoal = new SubGoalExit();
            dungeon.processExitGoal(subgoal);
            compositeGoal.addGoal(subgoal);
            subgoal.setCompositeGoal(compositeGoal);
            dungeon.addCompositeGoal(compositeGoal);
            break;
        case "boulders": 
            subgoal = new SubGoalBoulders();
            dungeon.processBouldersGoal(subgoal);
            compositeGoal.addGoal(subgoal);
            subgoal.setCompositeGoal(compositeGoal);
            dungeon.addCompositeGoal(compositeGoal);
            break;
        case "enemies":
            subgoal = new SubGoalEnemy();
            dungeon.processEnemiesGoal(subgoal);
            compositeGoal.addGoal(subgoal);
            subgoal.setCompositeGoal(compositeGoal);
            dungeon.addCompositeGoal(compositeGoal);
            break;
        case "treasure": 
            subgoal = new SubGoalTreasure();
            dungeon.processTreasureGoal(subgoal);
            compositeGoal.addGoal(subgoal);
            subgoal.setCompositeGoal(compositeGoal);
            dungeon.addCompositeGoal(compositeGoal);
            break;
        case "AND":
            /**
             * Create a new composite goal 
             * Use recursion to get the leaf goals inside the AND list
             * Those goals will be the leaf goals - attach them to the composite goal
             * Also set the composite goal inside the leaf goal to be later called in checking process
             */
            listSubgoals = jsonGoal.getJSONArray("subgoals");
            for (int i = 0; i < listSubgoals.length(); i++) {
                Goal g = loadGoal(dungeon, listSubgoals.getJSONObject(i));
                compositeGoal.addGoal(g);
                g.setCompositeGoal(compositeGoal);
            }
            subgoal = compositeGoal;
            subgoal.setName("AND");
            break;
        case "OR":
            /**
             * Create a new compositeOrGoal 
             * Use recursion to get each goal inside the list 
             * Attach these leaf goals to the composite OR goal 
             * 
             */
            listSubgoals = jsonGoal.getJSONArray("subgoals");
            CompositeOrGoal orGoal = new CompositeOrGoal();
            for (int i = 0; i < listSubgoals.length(); i++) {
                Goal g = loadGoal(dungeon, listSubgoals.getJSONObject(i));
                orGoal.addGoal(g);
                g.setCompositeGoal(orGoal);
            }
            subgoal = orGoal;
            subgoal.setName("OR");
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
        case "brokenWall":
            BrokenWall brokenWall = new BrokenWall(x, y);
            onLoad(brokenWall);
            entity = brokenWall;
            break;
        case "icyFloor":
            IcyFloor icyFloor = new IcyFloor(x, y);
            onLoad(icyFloor);
            entity = icyFloor;
            break;
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

    public abstract void onLoad(BrokenWall brokenWall);

    public abstract void onLoad(IcyFloor icyFloor);

    // TODO Create additional abstract methods for the other entities

}
