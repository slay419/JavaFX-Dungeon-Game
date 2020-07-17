package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity {
    
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

        //Check if currently on a Switch
        Entity currentSwitch = checkSwitchPos(this.x, this.y, player);
        Entity futureSwitch = checkSwitchPos(futureX, futureY, player);

        ArrayList<Entity> entityList = player.getEntityList(futureX, futureY);

        //Case 1, there's nothing
        if(entityList == null){
            moveBoulder(futureX, futureY);
            processSwitch(currentSwitch, player);
        }
        //Case 2, there's a floorswitch and nothing on it
        else if(futureSwitch != null){
            moveBoulder(futureX, futureY);
            processSwitch(currentSwitch, player);
            processSwitch(futureSwitch, player);
        }
        //Case 3, there's a floorswitch with a boulder on it
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

    //Checks if there is a switch at a given position
    private Entity checkSwitchPos(int x, int y, Player player){
        ArrayList<Entity> entityList = player.getEntityList(x, y);
        Entity entity = checkEntityList(entityList, "floorswitch");
        return entity;
    }

    private void processSwitch(Entity floorSwitch, Player player){
        if(floorSwitch != null){
            floorSwitch.process(player);
        }
    }

    private void moveBoulder(int x, int y){
        setX(x);
        setY(y);
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