package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Player player;
    private List<Key> keys;
    
    /**
     * 
     * @param player The player the inventory is tied to
     */
    public Inventory(Player player){
        this.player = player;
        this.keys = new ArrayList<>();
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
}