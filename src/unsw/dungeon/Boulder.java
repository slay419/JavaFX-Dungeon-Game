package unsw.dungeon;


public class Boulder extends Entity{
    
    private int x;
    private int y;

    public Boulder(int x, int y){
        super(x, y);
        this.x = x;
        this.y = y;
        setImpassible(true);
        setName("boulder");
    }

    @Override
    public void process(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int futureX = getBoulderFuturePos(playerX, this.x);
        int futureY = getBoulderFuturePos(playerY, this.y);

        if(player.getEntity(futureX, futureY) == null){
            setX(futureX);
            setY(futureY);
        }
    }

    /**
     * Calculates the future x/y position of a pushed boulder
     * @param playerPos The player's current x/y position
     * @param boulderPos The boulder's current x/y position
     * @return
     */
    private int getBoulderFuturePos(int playerPos, int boulderPos){
        int futurePos = 0;
        for (int direction = -1; direction < 1; direction++){
            if(playerPos + direction == boulderPos){
                futurePos = this.x + direction;
                break;
            }
        }
        return futurePos;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}