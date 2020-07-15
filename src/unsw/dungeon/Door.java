package unsw.dungeon;


public class Door extends Entity {
    
    private int id;
    
    /**
     * 
     * @param x X coordinate of the door (Starting from 0 Left to right)
     * @param y Y coordinate of the door (Starting from 0 Top to bottom)
     * @param id ID of the door, opened by a key with matching ID
     */
    public Door(int x, int y, int id){
        super(x, y);
        this.id = id;
        impassible = true;
        name = "door";
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getImpassible() {
        return impassible;
    }

    public void setImpassible(Boolean impassible) {
        this.impassible = impassible;
    }

    public void openDoor(){
        setImpassible(false);
    }
    

}
