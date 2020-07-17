package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity {
    
    //private int x;
    //private int y;

    public Boulder(int x, int y){
        super(x, y);
        //this.x = x;
        //this.y = y;
        setImpassible(true);
        setName("boulder");
    }

    @Override
    public void process(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();
        int futureX = getBoulderFuturePos(playerX, getX());
        
        int futureY = getBoulderFuturePos(playerY, getY());
        

        //Check if currently on a Switch
        Entity currentSwitch = checkSwitchPos(getX(), getY(), player);
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
        } else {
            System.out.println("IS THIS BRANCH MEANT TO TRIGGER");
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
        for (int direction = -1; direction <= 1; direction++){
            if(playerPos + direction == boulderPos){
                futurePos = boulderPos + direction;
                break;
            }
        }
        return futurePos;
    }

    //Checks if there is a switch at a given position
    private Entity checkSwitchPos(int x, int y, Player player){
        ArrayList<Entity> entityList = player.getEntityList(x, y);
        if (entityList == null) {
            return null;
        }
        Entity entity = checkEntityList(entityList, "floorSwitch");
        return entity;
    }

    private void processSwitch(Entity floorSwitch, Player player){
        if(floorSwitch != null){
            ((FloorSwitch) floorSwitch).processSwitch(player);
        }
    }

    private void moveBoulder(int x, int y){
        setXPos(x);
        setYPos(y);
    }

    /*
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
    */
}