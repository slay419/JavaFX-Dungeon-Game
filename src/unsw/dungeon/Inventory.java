package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    
    //private ArrayList<Observer> observers;
    private Player player;
    private Key key;
    private List<Treasure> treasure;
    private Sword sword;
    private InvincibilityPotion potion;
    
    /**
     * 
     * @param player The player the inventory is tied to
     */
    public Inventory(Player player){
        this.player = player;
        this.treasure = new ArrayList<Treasure>();
        //observers = new ArrayList<Observer>();
    }


    //checkKey called when a player moves into an impassible door (closed door)
    //and checks if the ID of the door matches any of the key ID's in the inventory
    public Boolean checkKey(int id){
        if (key == null) {
            return false;
        }
        return key.getId() == id;
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
        else if (entity instanceof Sword) {
            Sword s = (Sword) entity;
            addSword(s);
            System.out.println("Added Sword to inventory!");
        } else if (entity instanceof InvincibilityPotion) {
            InvincibilityPotion potion = (InvincibilityPotion) entity;
            addPotion(potion);
        }
        player.pickUp(entity);
    }


    private void addTreasure(Treasure treasure) {
        this.treasure.add(treasure);
        //notifyObserver();
    }

    private void addKey(Key key) {
        this.key = key;
        //this.keys.add(key);
    }

    private void addSword(Sword sword){
        this.sword = sword;
    }

    private void addPotion(InvincibilityPotion potion) {
        this.potion = potion;
    }

    public void useKey(){
        this.key = null;
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
    public Boolean hasPotion() {
        return potion != null;
    }

    public Boolean hasKey(){
        return key != null;
    }

    public Boolean hasSword(){
        if (sword != null) {
            return true;
        } 
        return false;
    }

    public void removeSword() {
        sword = null;
    }

    public void useSword() {
        sword.consumeCharge();
        if (sword.getCharges() == 0) {
            removeSword();
        }
    }

    public void usePotion() {
        if (potion != null) {
            potion.useCharge(player);
        }
    }

    public void removePotion() {
        potion = null;
    }

    public int potionCharges() {
        return potion.getCharges();
    }
}