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

    public Boolean reachedExit(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        return playerX == this.getX() && playerY == this.getY();
    }

    @Override
    public void process(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        if (reachedExit(playerX, playerY)) {
            System.out.println("Reached the exit!");
        }
    }

}