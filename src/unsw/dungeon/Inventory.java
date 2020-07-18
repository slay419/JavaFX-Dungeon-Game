package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Player player;
    private List<Key> keys;
    private List<Treasure> treasure;
    private List<Entity> weapons;
    private InvincibilityPotion potion;
    
    /**
     * 
     * @param player The player the inventory is tied to
     */
    public Inventory(Player player){
        this.player = player;
        this.keys = new ArrayList<>();
        this.treasure = new ArrayList<>();
        this.weapons = new ArrayList<>();
    }

    //checkKey called when a player moves into an impassible door (closed door)
    //and checks if the ID of the door matches any of the key ID's in the inventory
    public Boolean checkKey(int id){
        for(Key k: keys){
            if(k.checkKey(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an entity to the players inventory 
     * Only accepts entities: keys, treasure, sword and potion
     * @param entity
     */
    public void add(Entity entity) {
        if (entity instanceof Key) {
            Key k = (Key) entity;
            addKey(k);
            System.out.println("Added key to inventory!");
        } else if (entity instanceof Treasure) {
            Treasure t = (Treasure) entity;
            addTreasure(t);
            System.out.println("Added Treasure to inventory!");
        }
        player.pickUp(entity);
    }


    private void addTreasure(Treasure treasure) {
        this.treasure.add(treasure);
    }

    private void addKey(Key key) {
        this.keys.add(key);
    }

    public void useKey(){
        this.keys.clear();
    }

    public int countTreasure() {
        return treasure.size();
    }

    public InvincibilityPotion getPotion() {
        return potion;
    }

    /**
     * @return true if a sword or potion exists in the inventory 
     */
    public Boolean hasWeapon() {
        return !weapons.isEmpty();
    }

    public Boolean hasKey(){
        return !keys.isEmpty();
    }
}