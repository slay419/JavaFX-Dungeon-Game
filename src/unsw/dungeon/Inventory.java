package unsw.dungeon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Inventory {
    
    //private ArrayList<Observer> observers;
    private Player player;
    private Key key;
    private List<Treasure> treasure;
    private Sword sword;
    private InvincibilityPotion potion;
    
    private ImageView keyView;
    private ImageView treasureView;
    private ImageView swordView;
    private ImageView potionView;

    /**
     * 
     * @param player The player the inventory is tied to
     */
    public Inventory(Player player){
        Image keyImage = new Image((new File("images/key.png")).toURI().toString());
        Image treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        Image swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        Image potionImage = new Image((new File("images/bubbly.png")).toURI().toString());
        this.player = player;
        this.treasure = new ArrayList<Treasure>();
        this.keyView = new ImageView(keyImage);
        this.treasureView = new ImageView(treasureImage);
        this.swordView = new ImageView(swordImage);
        this.potionView = new ImageView(potionImage);

    }



    /**
     * 
     *checkKey called when a player moves into an impassible door (closed door)
     *and checks if the ID of the door matches any of the key ID's in the inventory
     * @param id ID of the key
     * @return
     */
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
        } else if (entity instanceof Treasure) {
            Treasure t = (Treasure) entity;
            addTreasure(t);
        }
        else if (entity instanceof Sword) {
            Sword s = (Sword) entity;
            addSword(s);
        } else if (entity instanceof InvincibilityPotion) {
            InvincibilityPotion potion = (InvincibilityPotion) entity;
            addPotion(potion);
        }
        player.pickUp(entity);
    }

    private void addTreasure(Treasure treasure) {
        if(this.treasure.isEmpty()){
            addImage(treasureView, 1);
        }
        this.treasure.add(treasure);
    }

    private void addKey(Key key) {
        this.key = key;
        addImage(keyView, 0);
    }

    private void addSword(Sword sword){
        this.sword = sword;
        addImage(swordView, 2);
    }

    private void addPotion(InvincibilityPotion potion) {
        if(!hasPotion()){
            addImage(potionView, 3);
        }
        this.potion = potion;
    }

    public void useKey(){
        this.key = null;
        removeImage(keyView, 0);
    }

    public int countTreasure() {
        return treasure.size();
    }

    public InvincibilityPotion getPotion() {
        return potion;
    }

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
        removeImage(swordView, 0);
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
        removeImage(potionView, 0);
    }

    public int potionCharges() {
        return potion.getCharges();
    }

    public int getKeyId() {
        return key.getId();
    }

    public void changeKeyId(int id){
        key.setId(id);
    
    }

    public void addImage(ImageView image, int x){
        player.addImage(image, x);
    }

    public void removeImage(ImageView image, int x){
        player.removeImage(image, x);
    }

    public void bindCharge(Label label){
        SimpleStringProperty treasureSize = new SimpleStringProperty(String.valueOf(treasure.size()));
        label.textProperty().bind(treasureSize);
    }
}