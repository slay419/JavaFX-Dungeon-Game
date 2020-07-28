package unsw.dungeon;

public class Key extends Entity {
    private int id;

    /**
     * 
     * @param x X coordinate of the key (Starting from 0 Left to right)
     * @param y Y coordinate of the key (Starting from 0 Top to bottom)
     * @param id ID of the key, opens a door with matching ID
     */
    public Key(int x, int y, int id){
        super(x, y);
        this.id = id;
        setImpassible(false);
        setName("key");
        setItem(true);
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Adds a key to the inventory if the player doesn't already have one in inventory
    @Override
    public void process(Player player) {
        Inventory inventory = player.getInventory();
        if(!inventory.hasKey()){
            inventory.add(this);
        }
        else{
            //swapId is the key in the inventories Id
            //this.Id is the key being picked up Id
            int swapId = inventory.getKeyId();
            inventory.changeKeyId(this.id);
            setId(swapId);
        }
    }
}