package unsw.dungeon;

public class Exit extends Entity {
    public Exit(int x, int y) {
        super(x, y);
        setImpassible(false);
        setName("exit");
    }

    /**
     * 
     * @param x - x coordinates of the player
     * @param y - y coordinates of the player
     * @return Boolean
     */
    public Boolean reachedExit(int x, int y) {
        int exitX = getX(); 
        int exitY = getY();
        return exitX == x && exitY == y;
    }
}