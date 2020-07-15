package unsw.dungeon;

public class Key extends Entity{
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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //checkKey takes in a Door's id and returns true if it matches the keys id
    public Boolean checkKey(int id){
        if(id == this.id){
            return true;
        }
        return false;
    }
}